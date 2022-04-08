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
 * 长整数节点.
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
