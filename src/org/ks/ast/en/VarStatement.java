/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast.en;

import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.TypeStatement;
import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.jvm.JavaKsObject;
import org.ks.jvm.JavaKsStaticClass;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;
import org.ks.runtime.VarType;

/**
 * 变量声明节点.
 *
 */
public class VarStatement extends ASTList {

	protected int nest;
	protected int index;
	protected int bcIndex;

	public VarStatement(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public TypeStatement type() {
		return (TypeStatement) child(1);
	}

	public ASTNode initializer() {
		return child(2);
	}

	public String toString() {
		return "(var " + name() + " " + type() + " " + initializer() + ")";
	}

	public void lookup(Symbols syms) {
		// 新加的，处理变量空间问题
		if (syms.get(name()) != null)
			throw new KsException("重复的变量: " + name(), this);
		//
		
		// 新加的，处理变量空间问题
		//Location loc = syms.put(name());
    //nest = loc.nest;
    //index = loc.index;
    //
    
    index = syms.putNew(name());
    if (!type().getType().equals(TypeStatement.UNDEFINED)) {
    	if (type().getType().equals("long") || type().getType().equals("double")) {
    		bcIndex = syms.addVarIndex(name(), 2);
    	} else {
    		bcIndex = syms.addVarIndex(name(), 1); //syms.getVarIndex(name());
    	}
    } else {
    	bcIndex = syms.addVarIndex(name(), 1); //syms.getVarIndex(name());
    }
    initializer().lookup(syms);
	}

	public Object eval(Environment env) {
		Object value = initializer().eval(env);
		env.put(0, index, value);
		return value;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Var"); // test
		
		Object value = initializer().compile(env, bcOp); // value
		
		// System.out.println("value:" + value); // test
		
		if (value instanceof VarType) {
			VarType type = (VarType)value;
			if (type.isExternal()) { // 如果是外部变量
				
			} else if (BcGenerator.isValueType(type)) { // 返回值类型的情况
				if(type().getType().equals(TypeStatement.UNDEFINED)) {
					// 当没有定义类型时，修改为包装类
					type = BcGenerator.toWrapperType(type, bcOp);
				} else {
					// 如果定义了基础类型就转为基础类型
					if(BcGenerator.isValueType(type().getType())) {
						type = BcGenerator.toValueType(type().getType()); // 这句有点多余，暂时先这样吧
					}
				}
			} else {
				// 返回的是类，不处理
			}
			
			/*if (env.get(0, index) != null)
				throw new KsException("重复的变量: " + name(), this);*/
			
			env.put(0, index, type);
			env.putType(0, index, type); // 保存变量的类型
			
			bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(type), bcIndex);
			// {}区间内不能需要生成局部变量表（nest > 0 or env.getOuter() == null）
			if (env.getOuter() == null) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			} else if (env.getOuter() != null && env.getOuter().isMethodEnvironment()) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			}
			
			// env.putFrameObjs(type.getJavaClass().getName().replaceAll("[.]", "/"));
		} else if (value instanceof JavaKsStaticClass) {
			JavaKsStaticClass jksc = (JavaKsStaticClass)value;
						
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
		} else if (value instanceof JavaKsObject) {
			JavaKsObject jko = (JavaKsObject)value;
						
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
		} else {
			throw new KsException("不支持的类型", this);
		}
		
		//
		return value;
	}

}
