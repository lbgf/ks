/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.comb;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.lexer.Token;

/**
 * Skip.
 *
 */
public class Skip extends Leaf {
	public Skip(String[] t) {
		super(t);
	}

	protected void find(List<ASTNode> res, Token t) {
	}
}
