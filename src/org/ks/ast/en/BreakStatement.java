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
 * 中断节点.
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
