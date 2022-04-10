/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 * 
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.util.List;

import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.ArithmeticUnit;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.MemberSymbols;
import org.ks.runtime.Symbols;
import org.ks.runtime.VarType;

/**
 * 二元表达式.
 *
 */
public class BinaryExpression extends ASTList {
	
	protected KsClassInfo classInfo = null;
  protected int index;
  ArithmeticUnit au = new ArithmeticUnit();
	
	public BinaryExpression(List<ASTNode> c) {
		super(c);
	}

	public ASTNode left() {
		return child(0);
	}

	public String operator() {
		return ((ASTLeaf) child(1)).token().getText();
	}

	public ASTNode right() {
		return child(2);
	}
	
	public void lookup(Symbols syms) {
    ASTNode left = left();
    if ("=".equals(operator())) {
      if (left instanceof Name) {
        ((Name)left).lookupLeft(syms);
        right().lookup(syms);
        return;
      }
    }
    left.lookup(syms);
    right().lookup(syms);
	}

	public Object eval(Environment env) {
		String op = operator();
		if ("=".equals(op)) {
			Object right = right().eval(env);
			return arithmeticLeft(env, right);
		} else {
			Object left = left().eval(env);
			Object right = right().eval(env);
			return arithmetic(left, right, op);
		}
	}
	
	protected Object arithmetic(Object left, Object right, String op) {
		switch(op) {
			case "+":
				return au.plus(left, right);
			case "-":
				return au.minus(left, right);
			case "*":
				return au.multiply(left, right);
			case "/":
				return au.divide(left, right);
			case "%":
				return au.mod(left, right);
			case "==":
			case "!=":
			case "&&":
			case ">":
			case "<":
			case ">=":
			case "<=":
			case "<=>":
				return au.compare(left, right, op);
			default:
				throw new KsException("错误的运算符", this);
		}
	}
	
	protected Object arithmeticLeft(Environment env, Object rvalue) {
    ASTNode l = left();
    if (l instanceof Name) {
        ((Name)l).evalLeft(env, rvalue);
        return rvalue;
    } else {

    	if (l instanceof PrimaryExpression) {
  			PrimaryExpression pe = (PrimaryExpression) l;
  			if (pe.hasPostfix(0) && pe.postfix(0) instanceof Dot) { // 类
  				Object obj = pe.evalSub(env, 1);
  				// System.out.println(p.postfix(0));
  				// System.out.println(t.getClass());
  				if (obj instanceof KsObject) {
            return setField((KsObject)obj, (Dot)pe.postfix(0), rvalue);
  				} else { // java类
      			try {
      				Class<?> c = null;
      				if (obj instanceof String) { // 对字符串的判断需要改进
      					c = Class.forName(obj.toString());
      					c.getField(((Dot)pe.postfix(0)).name()).set(c, rvalue); // 属性
      				} else {
      					c = obj.getClass();
      					c.getField(((Dot)pe.postfix(0)).name()).set(obj, rvalue); // 属性
      				}
      				return c; 
      			} catch(Exception e) {
      				return null;
      			}
  				}
  			} else if (pe.hasPostfix(0) && pe.postfix(0) instanceof ArrayRef) { // 数组
  				Object obj = pe.evalSub(env, 1);
  				if (obj instanceof Object[]) {
  					ArrayRef aref = (ArrayRef) pe.postfix(0);
  					Object index = aref.index().eval(env);
  					if (index instanceof Integer) {
  						((Object[]) obj)[(Integer) index] = rvalue;
  						return rvalue;
  					}
  				}
  				throw new KsException("数组出错", this);
  			}
  			throw new KsException("错误的表达式", this);
  		} /*else if (l instanceof Name) {
  			env.put(((Name) l).name(), rvalue);
  			return rvalue;
  		} */else {
  			throw new KsException("错误的表达式", this);
  		}
    	
    }
	}
  
  protected Object setField(KsObject obj, Dot expr, Object rvalue) {
		if (obj.classInfo() != classInfo) {
			String member = expr.name();
			classInfo = obj.classInfo();
			Integer i = classInfo.fieldIndex(member);
			if (i == null) {
				throw new KsException("不能访问成员: " + member, this); 
			}
			index = i;
		}
		obj.write(index, rvalue);
		return rvalue;
	}
  
  public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("BinaryExpression");  // test
		
		String op = operator();
		// System.out.println("op:" + op);
		if ("=".equals(op)) {
			// left().compile(env, bcOp);
			if (left() instanceof Name) {
				Name n = (Name)left();
				
				if (n.nest == MemberSymbols.FIELD) { // 自定义类属性
					// bcOp.createField(bcOp.ACC_PUBLIC, "env", "Lorg/ks/runtime/Environment;", null, null);
					bcOp.getThis();
					Object obj = right().compile(env, bcOp);
					n.compileLeft(env, bcOp, obj);
				} else {
					if(env.getBcValue(n.name()) != null) { // 外部变量
						n.compileLeft(env, bcOp, right());
					} else {
						Object obj = right().compile(env, bcOp);
						n.compileLeft(env, bcOp, obj);
					}
				}
			} else if (left() instanceof PrimaryExpression) { 
				PrimaryExpression pe = (PrimaryExpression) left();
				if (pe.hasPostfix(0) && pe.postfix(0) instanceof Dot) { // 类属性
					
					Object objl = pe.compileSub(env, bcOp, 1);
					
					Object objr = right().compile(env, bcOp);
					VarType rightType = null;
					if (objr instanceof VarType) {
						rightType = (VarType)objr;
					} else {
						throw new KsException("不支持的类型", this);
					}
					
					if (objl instanceof VarType) {
						VarType leftType = (VarType)objl;
						if(leftType.isJavaObject()) { // java对象
							String name = ((Dot)pe.postfix(0)).name();
							String className = leftType.getJavaClass().getName().replaceAll("[.]", "/");
							if(BcGenerator.isValueType(rightType)) {
								leftType = BcGenerator.toWrapperType(rightType, bcOp);
							} else {
								leftType = (VarType)rightType;
							}
							bcOp.setField(className, name, "Ljava/lang/Object;"); // BcGenerator.getClassType(leftType.getJavaClass())
						}
  				}  else {
  					throw new KsException("不支持的类型", this);
  				}
				} else {
					throw new KsException("错误的表达式", this);
				}
			} else {
				right().compile(env, bcOp);
			}
			
		} else {
			switch(op) {
				case "+":
					return BcGenerator.plus(left(), right(), env, bcOp);
				case "-":
					return BcGenerator.minus(left(), right(),env, bcOp);
				case "*":
					return BcGenerator.multiply(left(), right(), env, bcOp);
				case "/":
					return BcGenerator.divide(left(), right(), env, bcOp);
				case "%":
					return BcGenerator.mod(left(), right(), env, bcOp);
				case "==":
				case "!=":
				case "&&":
				case ">":
				case "<":
				case ">=":
				case "<=":
				case "<=>":
					return BcGenerator.compare(left(), right(), op, env, bcOp);
				default:
					throw new KsException("错误的运算符", this);
			}
		}
		return null;
	}
	
}
