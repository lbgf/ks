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
 * �ַ��ڵ�.
 *
 */
public class StringLiteral extends ASTLeaf {

	public StringLiteral(Token t) {
		super(t);
	}

	public String value() {
		return token().getText();
	}

	public Object eval(Environment e) {
		return value();
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		bcOp.gcMethod().visitLdcInsn(value());
		return new VarType(String.class); 
	}

}
