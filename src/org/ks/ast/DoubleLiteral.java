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
 * �߾���С���ڵ�.
 *
 */
public class DoubleLiteral extends ASTLeaf {
	public DoubleLiteral(Token t) {
		super(t);
	}

	public double value() {
		return token().getDouble();
	}

	public Object eval(Environment e) {
		return value();
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		bcOp.gcMethod().visitLdcInsn(new Double(value()));
		return new VarType(double.class); 
	}

}
