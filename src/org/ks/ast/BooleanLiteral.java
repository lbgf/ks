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
 * �����ͽڵ�.
 *
 */
public class BooleanLiteral extends ASTLeaf {
	public BooleanLiteral(Token t) {
		super(t);
	}

	public boolean value() {
		return token().getBoolean();
	}

	public Object eval(Environment e) {
		return value();
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		bcOp.gcMethod().visitLdcInsn(value());
		return new VarType(boolean.class); 
	}

}
