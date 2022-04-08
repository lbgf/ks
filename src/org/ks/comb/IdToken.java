/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.comb;

import java.util.HashSet;

import org.ks.ast.ASTLeaf;
import org.ks.lexer.Token;

/**
 * IdToken.
 *
 */
public class IdToken extends CombToken {
	HashSet<String> reserved;

	public IdToken(Class<? extends ASTLeaf> type, HashSet<String> r) {
		super(type);
		reserved = r != null ? r : new HashSet<String>();
	}

	protected boolean test(Token t) {
		return t.isIdentifier() && !reserved.contains(t.getText());
	}
}
