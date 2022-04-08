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
import org.ks.ast.en.ClosureStatement;

/**
 * �հ��ڵ�(����).
 *
 */
public class ClosureStatementCn extends ClosureStatement {
	public ClosureStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(�հ� " + parameters() + " " + body() + ")";
	}

}
