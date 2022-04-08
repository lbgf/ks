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
 * 布尔型节点.
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
