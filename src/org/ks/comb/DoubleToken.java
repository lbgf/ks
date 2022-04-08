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
 * DoubleToken.
 *
 */
public class DoubleToken extends CombToken {
	public DoubleToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	protected boolean test(Token t) {
		return t.isDouble();
	}
}
