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
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * 负数节点.
 *
 */
public class NegativeExpression extends ASTList {
	public NegativeExpression(List<ASTNode> c) {
		super(c);
	}

	public ASTNode operand() {
		return child(0);
	}

	public String toString() {
		return "-" + operand();
	}

	public Object eval(Environment env) {
		Object v = (operand()).eval(env);
		if (v instanceof Integer) {
			return new Integer(-((Integer) v).intValue());
		} else if (v instanceof Long) {
			return new Long(-((Long) v).longValue());
		} else if (v instanceof Double) {
			return new Double(-((Double) v).doubleValue());
		} else {
			throw new KsException("错误的类型 -", this);
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Negative");
		
		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/bc/ArithmeticUnit;");
		
		Object obj = operand().compile(env, bcOp);
		String nType = "";
		String rType = "";
		
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!BcGenerator.isValueType(newType) && BcGenerator.isWrapperType(newType)) { // 不是值类型并且是包装型才处理
				newType = BcGenerator.toValueType((VarType)obj, bcOp);
			}
			if (BcGenerator.isValueType(newType)) {
				nType = BcGenerator.getBcType(newType);
				rType = nType;
			}
		}
		
		bcOp.invokeVirtual("org/ks/bc/ArithmeticUnit", "negative", nType, rType, false);
		
		return obj;
	}

}
