/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.comb;

import org.ks.ast.ASTLeaf;
import org.ks.lexer.Token;

/**
 * FloatToken.
 *
 */
public class FloatToken extends CombToken {
	public FloatToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	protected boolean test(Token t) {
		return t.isFloat();
	}
}
