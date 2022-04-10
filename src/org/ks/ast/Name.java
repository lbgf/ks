/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.jvm.JavaKsObject;
import org.ks.jvm.JavaKsStaticClass;
import org.ks.natives.NativeFunction;
import org.ks.lexer.Token;
import org.ks.runtime.Environment;
import org.ks.runtime.MemberSymbols;
import org.ks.runtime.SymbolThis;
import org.ks.runtime.Symbols;
import org.ks.runtime.Location;
import org.ks.runtime.VarType;

/**
 * 关键字节点.
 *
 */
public class Name extends ASTLeaf {
	
  protected int nest;
  protected int index;
  protected int bcIndex;
	
	public Name(Token t) {
		super(t);
		index = -1;
	}

	public String name() {
		return token().getText();
	}
	
	public void lookup(Symbols syms) {
    Location loc = syms.get(name());
    
    if (loc == null) {
      throw new KsException("没有定义名: " + name(), this);
    } else {
      nest = loc.nest;
      index = loc.index;
      bcIndex = syms.getVarIndex(name());
      
    }
	}
	
	public void lookupLeft(Symbols syms) {
		// to do 这里要区分一下SymbolThis 和 Symbols，否则外部同名变量会与类属性名冲突
		 Location loc = null;
		if (syms instanceof SymbolThis) { // 设置属性
			loc = ((SymbolThis)syms).putField(name());
		} else {
			loc = syms.put(name());
			bcIndex = syms.addVarIndex(name(), 1);// syms.getVarIndex(name());
		}
    
    nest = loc.nest;
    index = loc.index;
    
	}
	
	public Object eval(Environment env) {
		/*System.out.println("关键字：" + name() + " 所属空间：" + nest + " 索引号：" + index 
				 + " 变量：" + env.get(nest, index));  // test*/
		
    if (index == -1) {
      return env.get(name());
    } else if (nest == MemberSymbols.FIELD) {
      return getThis(env).read(index);
    } else if (nest == MemberSymbols.METHOD) {
      return getThis(env).method(index);
    } else {
      return env.get(nest, index);
    }
	}
	
	public void evalLeft(Environment env, Object value) {
		/*System.out.println("关键字：" + name() + " 所属空间：" + nest + " 索引号：" + index 
		 + " 变量：" + env.get(nest, index));  // test*/
		
    if (index == -1) {
      env.put(name(), value);
    } else if (nest == MemberSymbols.FIELD) {
      getThis(env).write(index, value);
    } else if (nest == MemberSymbols.METHOD) {
      throw new KsException("方法不能赋值: " + name(),this);
    } else {
      env.put(nest, index, value);
    }
	}
	
	protected KsObject getThis(Environment env) {
    return (KsObject)env.get(0, 0);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Name"); // test
		
		// showMsg(env);  // test
		
		
		if (env.get(nest, index) != null) {
			
			if(env.get(nest, index) instanceof KsFunction) { // 自建函数
				
				//  不处理
				
			} else if(!(env.get(nest, index) instanceof NativeFunction)) {
				
				VarType type = env.getType(nest, index);
												
				if (type != null) { 
					if (type.isClassLib()) { // 是类库
						return type;
					} else {
						bcOp.gcMethod().visitVarInsn(BcGenerator.getOplType((VarType)env.getType(nest, index)), bcIndex);
					}
					
				} else { // 不是NativeFunction，又没有类型的，是外部传入来的变量
					bcOp.getThis();
					bcOp.gcMethod().visitLdcInsn(name());
					bcOp.invokeVirtual("org/ks/bc/ScriptBase", "getVariable", "Ljava/lang/String;", "Ljava/lang/Object;", false);
					
					type = new VarType(env.getBcValue(name()).getClass());
					type.setExternal(true);
					return type;
				}
				
			}
			return env.get(nest, index);
		} else if (nest == MemberSymbols.FIELD) {
			
		} else if (nest == MemberSymbols.METHOD) {
			
		} else if (name().equals("this")) { // this指针
			bcOp.getThis();
			VarType type = new VarType(env.getSubScriptName());
			type.setThis(true);
			return type;
		} else {
			
			if (bcIndex >= 1) { // 为空但有索引号，是null和err变量
				bcOp.getThis();
				bcOp.gcMethod().visitLdcInsn(name());
				bcOp.invokeVirtual("org/ks/bc/ScriptBase", "getVariable", "Ljava/lang/String;", "Ljava/lang/Object;", false);
								
				VarType type = new VarType();
				type.setExternal(true);
				return type;
			} else {
				throw new KsException("不能识别的变量", this);
			}
		}
		
		return null;
	}
	
	public Object compileLeft(Environment env, BcOpcodes bcOp, Object right) {
		// System.out.println("Name Left"); // test
		
		VarType leftType = null;
				
		if(env.get(nest, index) == null) {
			
			if (nest == MemberSymbols.FIELD) { // 字段
				// showMsg(env);  // test
				
				bcOp.createField(BcOpcodes.ACC_PUBLIC, name(), "Ljava/lang/Object;", null, null);
				String className = "Script" +env.getSubScriptName().replaceAll("[.]", "/");
				if (right instanceof VarType) {
					if(BcGenerator.isValueType((VarType)right)) {
						leftType = BcGenerator.toWrapperType((VarType)right, bcOp);
					} else {
						leftType = (VarType)right;
					}
					bcOp.setField(className, name(), "Ljava/lang/Object;"); // BcGenerator.getClassType(leftType.getJavaClass())
				}
				
			} else if (nest == MemberSymbols.METHOD) {
				
			} else if (right instanceof VarType) {
				if(BcGenerator.isValueType((VarType)right)) {
					leftType = BcGenerator.toWrapperType((VarType)right, bcOp);
				} else {
					leftType = (VarType)right;
				}
				env.put(nest, index, leftType);
				env.putType(nest, index, leftType);
				
				// showMsg(env);  // test
				
				bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(leftType), bcIndex);
				// 如果是新变量生成局部变量表，但貌似没有这个也没问题...!^_^
				// {}区间内不能需要生成局部变量表，方法体除外
				if (env.getOuter() == null) {
					bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(leftType), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
				} else if (env.getOuter() != null && env.getOuter().isMethodEnvironment()) {
					bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(leftType), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
				}
				
			} else if (right instanceof JavaKsStaticClass) {
				JavaKsStaticClass jksc = (JavaKsStaticClass)right;
				
				VarType type = new VarType(jksc.getJavaClass());
				type.setJavaStaticClass(true);
				
				env.put(0, index, type);
				env.putType(0, index, type); // 保存变量的类型
				
				bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(type), bcIndex);

				if (env.getOuter() == null) {
					bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
				} else if (env.getOuter() != null && env.getOuter().isMethodEnvironment()) {
					bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
				}
				
				return type;
			} else if (right instanceof JavaKsObject) {
				JavaKsObject jko = (JavaKsObject)right;
				
				VarType type = new VarType(jko.getJavaClass());
				type.setJavaObject(true);
				
				env.put(0, index, type);
				env.putType(0, index, type); // 保存变量的类型
				
				bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(type), bcIndex);

				if (env.getOuter() == null) {
					bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
				} else if (env.getOuter() != null && env.getOuter().isMethodEnvironment()) {
					bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
				}
				
				return type;
			}
		} else {
			// showMsg(env);  // test
			
			if (env.get(nest, index) instanceof VarType) {
				leftType = (VarType)env.get(nest, index);

				if (right instanceof VarType) {
					VarType rightType = (VarType)right;
					// 如果左边不是值类型，右边是，要转换成左边的类型
					if(!BcGenerator.isValueType(leftType) && BcGenerator.isValueType(rightType)) { 
						BcGenerator.toWrapperType(rightType, bcOp);
					}
				}
				bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(leftType), bcIndex);
			} else if(right instanceof ASTNode) { // 是外部传入来的变量
				bcOp.getThis();
				bcOp.gcMethod().visitLdcInsn(name());
				((ASTNode) right).compile(env, bcOp);
				bcOp.invokeVirtual("org/ks/bc/ScriptBase", "setVariable", "Ljava/lang/String;Ljava/lang/Object;", "V", false);
				
				VarType type = new VarType(env.getBcValue(name()).getClass());
				type.setExternal(true);
				return type;
			}
			
		}
		
		return leftType;
		
	}
	
	// 只用于调试
	protected void showMsg(Environment env) {
		System.out.println("------------------------" );
		System.out.println("关键字：" + name());
		System.out.println("所属空间：" + nest);
		System.out.println("索引号：" + index);
		System.out.println("类型：" + env.getType(nest, index));
		System.out.println("变量：" + env.get(nest, index));
		System.out.println("bc索引号:" + bcIndex);
		System.out.println("------------------------" );
	}
	
}
