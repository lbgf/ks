/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
 * �߼��ǽڵ�.
 *
 */
public class NotExpression extends ASTList {
	public NotExpression(List<ASTNode> c) {
		super(c);
	}

	public ASTNode operand() {
		return child(0);
	}

	public String toString() {
		return "!" + operand();
	}

	public Object eval(Environment env) {
		Object v = (operand()).eval(env);
		if (v instanceof Boolean) {
			return new Boolean(!((Boolean)v));
		} else {
			throw new KsException("��������� -", this);
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Not");
		
		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/core/ArithmeticUnit;");
		
		Object obj = operand().compile(env, bcOp);
		String nType = "Ljava/lang/Object;";
		String rType = "Z";
		
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!BcGenerator.isValueType(newType) && BcGenerator.isWrapperType(newType)) { // ����ֵ���Ͳ����ǰ�װ�ͲŴ���
				newType = BcGenerator.toValueType((VarType)obj, bcOp);
			}
			if (BcGenerator.isValueType(newType)) {
				nType = BcGenerator.getBcType(newType);
				rType = nType;
			}
		}
		bcOp.invokeVirtual("org/ks/core/ArithmeticUnit", "not", nType, rType, false);
		
		return obj;
	}

}
