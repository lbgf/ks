/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.util.Iterator;

import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.lexer.Token;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;

import java.util.ArrayList;

/**
 * 叶子.
 *
 */
public class ASTLeaf extends ASTNode {
	
	private static ArrayList<ASTNode> empty = new ArrayList<ASTNode>();
	protected Token token;

	public ASTLeaf(Token t) {
		token = t;
	}

	public ASTNode child(int i) {
		throw new IndexOutOfBoundsException();
	}

	public int numChildren() {
		return 0;
	}

	public Iterator<ASTNode> children() {
		return empty.iterator();
	}

	public String toString() {
		return token.getText();
	}

	public String location() {
		return "在第" + token.getLineNumber() + "行 第" + token.getColumnNumber() + "列";
	}
	
	public void lookup(Symbols syms) {
		
	}

	public Token token() {
		return token;
	}
	
	public Object eval(Environment env) {
		throw new KsException("不能执行: " + toString(), this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		throw new KsException("不能执行: " + toString(), this);
	}

}
