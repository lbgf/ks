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

import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;

/**
 * 基础的节点.
 *
 */
public class PrimaryExpression extends ASTList {
	public PrimaryExpression(List<ASTNode> c) {
		super(c);
	}

	public static ASTNode create(List<ASTNode> c) {
		return c.size() == 1 ? c.get(0) : new PrimaryExpression(c);
	}

	public ASTNode operand() {
		return child(0);
	}

	public Postfix postfix(int nest) {
		return (Postfix) child(numChildren() - nest - 1);
	}

	public boolean hasPostfix(int nest) {
		return numChildren() - nest > 1;
	}

	public Object eval(Environment env) {
		return evalSub(env, 0);
	}

	public Object evalSub(Environment env, int nest) {
		if (hasPostfix(nest)) {
			// System.out.println("1:" + postfix(nest).getClass());
			Object target = evalSub(env, nest + 1);
			return postfix(nest).eval(env, target);
		} else {
			// System.out.println("2:" + operand().getClass());
			return operand().eval(env);
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("PrimaryExpression");  // test
		return compileSub(env, bcOp, 0);
	}
	
	public Object compileSub(Environment env, BcOpcodes bcOp, int nest) {
		if (hasPostfix(nest)) {
			Object target = compileSub(env, bcOp, nest + 1);
			// System.out.println("1:" + postfix(nest).getClass());
			return postfix(nest).compile(env, bcOp, target);
		} else {
			//System.out.println("2:" + operand().getClass());
			return operand().compile(env, bcOp);
		}
	}

}
