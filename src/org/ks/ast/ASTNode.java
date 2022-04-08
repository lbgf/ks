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

import org.ks.runtime.Symbols;
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;

/**
 * 语法树节点.
 *
 */
public abstract class ASTNode implements Iterable<ASTNode> {

	public abstract ASTNode child(int i);

	public abstract int numChildren();

	public abstract Iterator<ASTNode> children();

	public abstract String location();

	public Iterator<ASTNode> iterator() {
		return children();
	}
	
	public abstract void lookup(Symbols syms);

	public abstract Object eval(Environment env);
	
	public abstract Object compile(Environment env, BcOpcodes bcOp);

}
