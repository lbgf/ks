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
 * BooleanToken.
 *
 */
public class BooleanToken extends CombToken {
	public BooleanToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	protected boolean test(Token t) {
		return t.isBoolean();
	}
}
