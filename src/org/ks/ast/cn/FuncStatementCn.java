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
import org.ks.ast.en.FuncStatement;

/**
 * �����ڵ�(����).
 *
 */
public class FuncStatementCn extends FuncStatement {
	public FuncStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(���� " + name() + " " + parameters() + " " + type() + " " + body() + ")";
	}
	
	
}
