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

import org.ks.ast.ASTNode;
import org.ks.lexer.KsLexer;
import org.ks.parser.ParseException;

/**
 * Tree.
 *
 */
public class Tree extends Element {
	
	protected KsCombinator parser;

	public Tree(KsCombinator p) {
		parser = p;
	}

	public void parse(KsLexer lexer, List<ASTNode> res) throws ParseException {
		res.add(parser.parse(lexer));
	}

	public boolean match(KsLexer lexer) throws ParseException {
		return parser.match(lexer);
	}
	
}
