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
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;

/**
 * �жϽڵ�.
 *
 */
public class BreakStatement extends ASTList {
	public BreakStatement(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(break)";
	}

	public Object eval(Environment env) {
		env.getBreakList().setPeek(1);
		return 0;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Break");  // test
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.GOTO, env.getBcBreakList().peek());
		return null;
	}

}
