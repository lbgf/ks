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
import org.ks.ast.en.ForStatement;

/**
 * ѭ���ڵ�(����).
 *
 */
public class ForStatementCn extends ForStatement {
	public ForStatementCn(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(ѭ�� " + initializer() + " " + condition() + " " + incremental() + " " + body() + ")";
	}
	
}
