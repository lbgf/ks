/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.util.List;

import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.runtime.Environment;

/**
 * 后缀.
 *
 */
public abstract class Postfix extends ASTList {
	public Postfix(List<ASTNode> c) {
		super(c);
	}

	public abstract Object eval(Environment env, Object value);
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		throw new KsException("不能执行: " + toString(), this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp, Object value) {
		throw new KsException("不能执行: " + toString(), this);
	}

}
