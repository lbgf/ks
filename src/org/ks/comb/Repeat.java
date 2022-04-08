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

import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.lexer.KsLexer;
import org.ks.parser.ParseException;

/**
 * Repeat.
 *
 */
public class Repeat extends Element {
	
	protected KsCombinator parser;
	protected boolean onlyOnce;

	public Repeat(KsCombinator p, boolean once) {
		parser = p;
		onlyOnce = once;
	}

	public void parse(KsLexer lexer, List<ASTNode> res) throws ParseException {
		while (parser.match(lexer)) {
			ASTNode t = parser.parse(lexer);
			if (t.getClass() != ASTList.class || t.numChildren() > 0)
				res.add(t);
			if (onlyOnce)
				break;
		}
	}

	public boolean match(KsLexer lexer) throws ParseException {
		return parser.match(lexer);
	}
	
}
