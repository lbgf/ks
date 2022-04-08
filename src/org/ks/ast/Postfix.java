/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast;

import java.util.List;

import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.runtime.Environment;

/**
 * ��׺.
 *
 */
public abstract class Postfix extends ASTList {
	public Postfix(List<ASTNode> c) {
		super(c);
	}

	public abstract Object eval(Environment env, Object value);
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		throw new KsException("����ִ��: " + toString(), this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp, Object value) {
		throw new KsException("����ִ��: " + toString(), this);
	}

}
