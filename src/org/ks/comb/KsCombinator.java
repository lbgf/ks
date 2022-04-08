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
import java.util.HashSet;
import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTNode;
import org.ks.lexer.KsLexer;
import org.ks.parser.ParseException;

/**
 * 组合器.
 *
 */
public class KsCombinator {

	protected List<Element> elements;
	protected Factory factory;

	public KsCombinator(Class<? extends ASTNode> clazz) {
		reset(clazz);
	}

	protected KsCombinator(KsCombinator p) {
		elements = p.elements;
		factory = p.factory;
	}

	/**
	 * 执行语法分析.
	 * 
	 * @param lexer
	 * @return
	 * @throws ParseException
	 */
	public ASTNode parse(KsLexer lexer) throws ParseException {
		ArrayList<ASTNode> results = new ArrayList<ASTNode>();
		for (Element e : elements) {
			e.parse(lexer, results);
		}

		return factory.make(results);
	}

	public boolean match(KsLexer lexer) throws ParseException {
		if (elements.size() == 0) {
			return true;
		} else {
			Element e = elements.get(0);
			return e.match(lexer);
		}
	}

	public static KsCombinator rule() {
		return rule(null);
	}

	public static KsCombinator rule(Class<? extends ASTNode> clazz) {
		return new KsCombinator(clazz);
	}

	/**
	 * 重置.
	 * 
	 * @return
	 */
	public KsCombinator reset() {
		elements = new ArrayList<Element>();
		return this;
	}

	public KsCombinator reset(Class<? extends ASTNode> clazz) {
		elements = new ArrayList<Element>();
		factory = Factory.getForASTList(clazz);
		return this;
	}

	/**
	 * 整数.
	 * 
	 * @return
	 */
	public KsCombinator ksInteger() {
		return ksInteger(null);
	}

	public KsCombinator ksInteger(Class<? extends ASTLeaf> clazz) {
		elements.add(new IntegerToken(clazz));
		return this;
	}

	/**
	 * 长整数.
	 * 
	 * @return
	 */
	public KsCombinator ksLong() {
		return ksLong(null);
	}

	public KsCombinator ksLong(Class<? extends ASTLeaf> clazz) {
		elements.add(new LongToken(clazz));
		return this;
	}
	
	/**
	 * 小数.
	 * 
	 * @return
	 */
	public KsCombinator ksFloat() {
		return ksFloat(null);
	}

	public KsCombinator ksFloat(Class<? extends ASTLeaf> clazz) {
		elements.add(new FloatToken(clazz));
		return this;
	}

	/**
	 * 高精小数.
	 * 
	 * @return
	 */
	public KsCombinator ksDouble() {
		return ksDouble(null);
	}

	public KsCombinator ksDouble(Class<? extends ASTLeaf> clazz) {
		elements.add(new DoubleToken(clazz));
		return this;
	}

	/**
	 * 布尔型.
	 * 
	 * @return
	 */
	public KsCombinator ksBoolean() {
		return ksBoolean(null);
	}

	public KsCombinator ksBoolean(Class<? extends ASTLeaf> clazz) {
		elements.add(new BooleanToken(clazz));
		return this;
	}

	/**
	 * 添加终结符(用于标记关键字).
	 * 
	 * @param reserved
	 * @return
	 */
	public KsCombinator identifier(HashSet<String> reserved) {
		return identifier(null, reserved);
	}

	public KsCombinator identifier(Class<? extends ASTLeaf> clazz, HashSet<String> reserved) {
		elements.add(new IdToken(clazz, reserved));
		return this;
	}

	/**
	 * 字符串.
	 * 
	 * @return
	 */
	public KsCombinator ksString() {
		return ksString(null);
	}

	public KsCombinator ksString(Class<? extends ASTLeaf> clazz) {
		elements.add(new StringToken(clazz));
		return this;
	}

	/**
	 * 添加终结符.
	 * 
	 * @param pat
	 * @return
	 */
	public KsCombinator token(String... pat) {
		elements.add(new Leaf(pat));
		return this;
	}

	/**
	 * 未包含抽象语法树的终结符(一般用于不需要出现在语法树上的符号).
	 * 
	 * @param pat
	 * @return
	 */
	public KsCombinator sep(String... pat) {
		elements.add(new Skip(pat));
		return this;
	}

	/**
	 * 添加非终结符.
	 * 
	 * @param p
	 * @return
	 */
	public KsCombinator ast(KsCombinator p) {
		elements.add(new Tree(p));
		return this;
	}

	/**
	 * 添加若干个由or关联连接的非终结符.
	 * 
	 * @param p
	 * @return
	 */
	public KsCombinator or(KsCombinator... p) {
		elements.add(new OrTree(p));
		return this;
	}

	/**
	 * 添加可省略的非终结符.
	 * 
	 * @param p
	 * @return
	 */
	public KsCombinator maybe(KsCombinator p) {
		KsCombinator p2 = new KsCombinator(p);
		p2.reset();
		elements.add(new OrTree(new KsCombinator[] { p, p2 }));
		return this;
	}

	/**
	 * 添加可省略的非终结符.
	 * 
	 * @param p
	 * @return
	 */
	public KsCombinator option(KsCombinator p) {
		elements.add(new Repeat(p, true));
		return this;
	}

	/**
	 * 添加至少重复出现0次的非终结符.
	 * 
	 * @param p
	 * @return
	 */
	public KsCombinator repeat(KsCombinator p) {
		elements.add(new Repeat(p, false));
		return this;
	}

	/**
	 * 添加二元运算表达式.
	 * 
	 * @param subexp
	 * @param operators
	 * @return
	 */
	public KsCombinator expression(KsCombinator subexp, Operators operators) {
		elements.add(new Expression(null, subexp, operators));
		return this;
	}

	/**
	 * 添加二元运算表达式.
	 * 
	 * @param subexp
	 * @param operators
	 * @return
	 */
	public KsCombinator expression(Class<? extends ASTNode> clazz, KsCombinator subexp, Operators operators) {
		elements.add(new Expression(clazz, subexp, operators));
		return this;
	}

	/**
	 * 为语法规则起始处的or添加新的分支选项.
	 * 
	 * @param p
	 * @return
	 */
	public KsCombinator insertChoice(KsCombinator p) {
		Element e = elements.get(0);
		if (e instanceof OrTree) {
			((OrTree) e).insert(p);
		} else {
			KsCombinator otherwise = new KsCombinator(this);
			reset(null);
			or(p, otherwise);
		}
		return this;
	}
}
