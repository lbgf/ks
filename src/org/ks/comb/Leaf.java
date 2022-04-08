/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.comb;

import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTNode;
import org.ks.lexer.KsLexer;
import org.ks.parser.ParseException;
import org.ks.lexer.Token;

/**
 * Leaf.
 *
 */
public class Leaf extends Element {
	protected String[] tokens;

	public Leaf(String[] pat) {
		tokens = pat;
	}

	public void parse(KsLexer lexer, List<ASTNode> res) throws ParseException {
		Token t = lexer.read();
		if (t.isIdentifier()) {
			for (String token : tokens) {
				if (token.equals(t.getText())) {
					find(res, t);
					return;
				}
			}
		}
		if (tokens.length > 0) {
			throw new ParseException("��" + tokens[0] + "����.", t);
		} else {
			throw new ParseException(t);
		}
	}

	protected void find(List<ASTNode> res, Token t) {
		res.add(new ASTLeaf(t));
	}

	public boolean match(KsLexer lexer) throws ParseException {
		Token t = lexer.peek(0);
		if (t.isIdentifier()) {
			for (String token : tokens) {
				if (token.equals(t.getText())) {
					return true;
				}
			}
		}
		return false;
	}
}
