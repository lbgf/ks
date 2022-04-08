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

import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;

/**
 * 继续.
 *
 */
public class ContinueStatement extends ASTList {
	public ContinueStatement(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(continue)";
	}

	public Object eval(Environment env) {
		env.getContinueList().setPeek(1);
		return 0;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Continue");  // test
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.GOTO, env.getBcContinueList().peek());
		return null;
	}

}
