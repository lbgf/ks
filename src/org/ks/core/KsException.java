/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.core;

import org.ks.ast.ASTNode;

/**
 * 运行时异常类.
 *
 */
@SuppressWarnings("serial")
public class KsException extends RuntimeException {
	
	public KsException(Exception m) {
		super(m);
	}
	
	public KsException(Exception m, ASTNode t) {
		super(m + " " + t.location());
	}
	
	public KsException(String m) {
		super(m);
	}

	public KsException(String m, ASTNode t) {
		super(m + " " + t.location());
	}
}
