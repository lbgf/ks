/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
 * Ҷ��.
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
		return "�ڵ�" + token.getLineNumber() + "�� ��" + token.getColumnNumber() + "��";
	}
	
	public void lookup(Symbols syms) {
		
	}

	public Token token() {
		return token;
	}
	
	public Object eval(Environment env) {
		throw new KsException("����ִ��: " + toString(), this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		throw new KsException("����ִ��: " + toString(), this);
	}

}
