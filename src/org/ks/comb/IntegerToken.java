/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.comb;


import org.ks.ast.ASTLeaf;
import org.ks.lexer.Token;

/**
 * IntegerToken.
 *
 */
public class IntegerToken extends CombToken {
	public IntegerToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	protected boolean test(Token t) {
		return t.isInteger();
	}
}