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
import org.ks.ast.en.IncludeStatement;

/**
 * ���������ű��ڵ㣨���ģ�.
 *
 */
public class IncludeStatementCn extends IncludeStatement {

	public IncludeStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(���� " + name() + ")";
	}

}
