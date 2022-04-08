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

import org.ks.runtime.Environment;

/**
 * 三元运算.
 *
 */
public class TernaryStatement extends ASTList {
	public TernaryStatement(List<ASTNode> c) {
		super(c);
	}

	public ASTNode condition() {
		return child(0);
	}

	public ASTNode trueBlock() {
		return child(1);
	}
	
	public ASTNode flaseBlock() {
		return child(2);
	}

	public String toString() {
		return "( " + condition() + " ? " + trueBlock() + " : " + flaseBlock() + " )";
	}

	public Object eval(Environment env) {
		Object c = condition().eval(env);
		if (c instanceof Boolean && ((Boolean) c).booleanValue() != false) {
			return trueBlock().eval(env);
		} else {
			ASTNode b = flaseBlock();
			if (b == null)
				return 0;
			else
				return b.eval(env);
		}
	}

}
