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
 * 小数节点.
 *
 */
public class FloatLiteral extends ASTLeaf {
	public FloatLiteral(Token t) {
		super(t);
	}

	public double value() {
		return token().getFloat();
	}

	public Object eval(Environment e) {
		return value();
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		bcOp.gcMethod().visitLdcInsn(new Float(value()));
		return new VarType(float.class); 
	}

}
