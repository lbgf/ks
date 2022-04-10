/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast.en;

import java.util.List;

import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.jvm.JavaKsObject;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * ����.
 *
 */
public class ReturnStatement extends ASTList {
	public ReturnStatement(List<ASTNode> c) {
		super(c);
	}

	public ASTNode result() {
		return child(0);
	}

	public String toString() {
		return "(return " + result() + ")";
	}

	public Object eval(Environment env) {
		ASTNode b = result();
		if (b == null) {
			env.getReturnList().setPeek(1);
			return null;
		} else {
			Object r = b.eval(env);
			env.getReturnList().setPeek(1);
			return r;
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Return");  // test
		
		Object obj = result().compile(env, bcOp);
		if (obj instanceof VarType) { 
			bcOp.gcMethod().visitInsn(BcGenerator.getOprType((VarType)obj));
		} else if (obj instanceof JavaKsObject) {
			VarType type = new VarType(((JavaKsObject)obj).getJavaClass());
			bcOp.gcMethod().visitInsn(BcGenerator.getOprType(type));
		} else { // to do ����ƥ��
			throw new KsException("����ʶ��Ĳ�������", this);
		}
		
		return obj;
	}

}
