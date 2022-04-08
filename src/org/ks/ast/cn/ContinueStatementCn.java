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
import org.ks.ast.en.ContinueStatement;

/**
 * ����(����).
 *
 */
public class ContinueStatementCn extends ContinueStatement {
	public ContinueStatementCn(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(����)";
	}
	
}
