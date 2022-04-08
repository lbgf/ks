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

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTNode;
import org.ks.lexer.KsLexer;
import org.ks.parser.ParseException;
import org.ks.lexer.Token;

/**
 * CombToken.
 *
 */
public abstract class CombToken extends Element {
	
	protected Factory factory;

	protected CombToken(Class<? extends ASTLeaf> type) {
		if (type == null) {
			type = ASTLeaf.class;
		}
		factory = Factory.get(type, Token.class);
	}

	public void parse(KsLexer lexer, List<ASTNode> res) throws ParseException {
		Token t = lexer.read();
		if (test(t)) {
			ASTNode leaf = factory.make(t);
			res.add(leaf);
		} else {
			throw new ParseException(t);
		}
	}

	public boolean match(KsLexer lexer) throws ParseException {
		return test(lexer.peek(0));
	}

	protected abstract boolean test(Token t);
	
}
