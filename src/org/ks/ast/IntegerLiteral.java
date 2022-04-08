/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast;

import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.lexer.Token;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * �����ڵ�.
 *
 */
public class IntegerLiteral extends ASTLeaf {
	public IntegerLiteral(Token t) {
		super(t);
	}

	public int value() {
		return token().getInteger();
	}

	public Object eval(Environment e) {
		return value();
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// bcOp.gcMethod().visitLdcInsn(new Integer(value()));
		BcGenerator.pushConstant(bcOp, value());
		return new VarType(int.class);
	}

}
