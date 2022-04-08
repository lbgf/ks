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
import org.ks.lexer.KsLexer;
import org.ks.parser.ParseException;

/**
 * OrTree.
 *
 */
public class OrTree extends Element {

	protected KsCombinator[] parsers;

	public OrTree(KsCombinator[] p) {
		parsers = p;
	}

	public void parse(KsLexer lexer, List<ASTNode> res) throws ParseException {
		KsCombinator p = choose(lexer);
		if (p == null) {
			throw new ParseException(lexer.peek(0));
		} else {
			res.add(p.parse(lexer));
		}
	}

	public boolean match(KsLexer lexer) throws ParseException {
		return choose(lexer) != null;
	}

	protected KsCombinator choose(KsLexer lexer) throws ParseException {
		for (KsCombinator p : parsers) {
			if (p.match(lexer)) {
				return p;
			}
		}
		return null;
	}

	public void insert(KsCombinator p) {
		KsCombinator[] newParsers = new KsCombinator[parsers.length + 1];
		newParsers[0] = p;
		System.arraycopy(parsers, 0, newParsers, 1, parsers.length);
		parsers = newParsers;
	}

}
