/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import org.ks.bc.BcOpcodes;
import org.ks.lexer.Token;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * 字符节点.
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
