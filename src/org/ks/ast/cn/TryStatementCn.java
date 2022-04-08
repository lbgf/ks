/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast.cn;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.TryStatement;

/**
 *  �쳣����(����).
 *
 */
public class TryStatementCn extends TryStatement {
	
	public TryStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(�쳣��� " + tryBlock() + " ���� " + catchVar() + catchBlock() + " ��β " + finallyBlock() + ")";
	}

}
