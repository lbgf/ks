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
 * Element.
 *
 */
public abstract class Element {
	
	public abstract void parse(KsLexer lexer, List<ASTNode> res) throws ParseException;

	public abstract boolean match(KsLexer lexer) throws ParseException;
	
}
