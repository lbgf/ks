/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast;

import org.ks.bc.BcOpcodes;
import org.ks.lexer.Token;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * �������ڵ�.
 *
 */
public class LongLiteral extends ASTLeaf {
	public LongLiteral(Token t) {
		super(t);
	}

	public long value() {
		return token().getLong();
	}

	public Object eval(Environment e) {
		return value();
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		bcOp.gcMethod().visitLdcInsn(new Long(value()));
		return new VarType(long.class); 
	}

}
