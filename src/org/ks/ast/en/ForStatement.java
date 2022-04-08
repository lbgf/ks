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
import org.objectweb.asm.Label;

/**
 * 循环节点.
 *
 */
public class ForStatement extends ASTList {
	public ForStatement(List<ASTNode> c) {
		super(c);
	}
	
	public ASTNode initializer() {
		return child(0);
	}

	public ASTNode condition() {
		return child(1);
	}
	
	public ASTNode incremental() {
		return child(2);
	}

	public ASTNode body() {
		return child(3);
	}

	public String toString() {
		return "(for " + initializer() + " " + condition() + " " + incremental() + " " + body() + ")";
	}

	public Object eval(Environment env) {
		Object result = 0;
		env.getBreakList().push(0);
		initializer().eval(env);
		for (;;) {
			Object c = condition().eval(env);
			if ((c instanceof Boolean && ((Boolean) c).booleanValue() == false) 
					|| env.getBreakList().peek().intValue() == 1) {
				env.getBreakList().pop();
				return result;
			} else {
				env.getContinueList().push(0);
				result = body().eval(env);
				env.getContinueList().pop();
			}
			if (env.getBreakList().peek().intValue() == 0) {
				incremental().eval(env);
			}
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("For"); // test
		
		Label l1 = bcOp.createLabel();
		env.getBcBreakList().push(l1);
		Label l2 = bcOp.createLabel();
		Label l3 = bcOp.createLabel();
		env.getBcContinueList().push(l3);
		initializer().compile(env, bcOp);
		bcOp.gcMethod().visitLabel(l2);
		condition().compile(env, bcOp);
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.IFEQ, l1);
    body().compile(env, bcOp);
    bcOp.gcMethod().visitLabel(l3);
    incremental().compile(env, bcOp);
    bcOp.gcMethod().visitJumpInsn(BcOpcodes.GOTO, l2);
    bcOp.gcMethod().visitLabel(l1);
    env.getBcContinueList().pop();
    env.getBcBreakList().pop();
    
		return null;
	}

}
