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
import java.util.List;

import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;

/**
 * ����.
 *
 */
public class ASTList extends ASTNode {
	
	protected List<ASTNode> children;

	public ASTList(List<ASTNode> list) {
		children = list;
	}

	public ASTNode child(int i) {
		return children.get(i);
	}

	public int numChildren() {
		return children.size();
	}

	public Iterator<ASTNode> children() {
		return children.iterator();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('(');
		String sep = "";
		for (ASTNode t : children) {
			builder.append(sep);
			sep = " ";
			builder.append(t.toString());
		}
		return builder.append(')').toString();
	}

	public String location() {
		for (ASTNode t : children) {
			String s = t.location();
			if (s != null) {
				return s;
			}
		}
		return null;
	}
	
	public void lookup(Symbols syms) {
		for (ASTNode t : this) {
			t.lookup(syms);
		}
	}

	public Object eval(Environment env) {
		throw new KsException("����ִ��: " + toString(), this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		throw new KsException("����ִ��: " + toString(), this);
	}
	
}
