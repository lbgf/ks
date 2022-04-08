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
import org.ks.ast.en.WhileStatement;

/**
 * ����(����).
 *
 */
public class WhileStatementCn extends WhileStatement {
	public WhileStatementCn(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(���� " + condition() + " " + body() + ")";
	}
	
}
