/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.core;

import org.ks.ast.ASTNode;

/**
 * ����ʱ�쳣��.
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
