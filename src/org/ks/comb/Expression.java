/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.comb;

import java.util.ArrayList;
import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTNode;
import org.ks.lexer.KsLexer;
import org.ks.parser.ParseException;
import org.ks.lexer.Token;

/**
 * Expression.
 *
 */
public class Expression extends Element {
	protected Factory factory;
	protected Operators ops;
	protected KsCombinator factor;

	public Expression(Class<? extends ASTNode> clazz, KsCombinator exp, Operators map) {
		factory = Factory.getForASTList(clazz);
		ops = map;
		factor = exp;
	}

	public void parse(KsLexer lexer, List<ASTNode> res) throws ParseException {
		ASTNode right = factor.parse(lexer);
		Precedence prec;
		while ((prec = nextOperator(lexer)) != null) {
			right = doShift(lexer, right, prec.value);
		}

		res.add(right);
	}

	private ASTNode doShift(KsLexer lexer, ASTNode left, int prec) throws ParseException {
		ArrayList<ASTNode> list = new ArrayList<ASTNode>();
		list.add(left);
		list.add(new ASTLeaf(lexer.read()));
		ASTNode right = factor.parse(lexer);
		Precedence next;
		while ((next = nextOperator(lexer)) != null && rightIsExpr(prec, next)) {
			right = doShift(lexer, right, next.value);
		}

		list.add(right);
		return factory.make(list);
	}

	private Precedence nextOperator(KsLexer lexer) throws ParseException {
		Token t = lexer.peek(0);
		if (t.isIdentifier()) {
			return ops.get(t.getText());
		} else {
			return null;
		}
	}

	private static boolean rightIsExpr(int prec, Precedence nextPrec) {
		if (nextPrec.leftAssoc) {
			return prec < nextPrec.value;
		} else {
			return prec <= nextPrec.value;
		}
	}

	public boolean match(KsLexer lexer) throws ParseException {
		return factor.match(lexer);
	}
}

