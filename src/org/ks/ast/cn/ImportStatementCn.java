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
import org.ks.ast.en.ImportStatement;

/**
 * ����ڵ�(����).
 *
 */
public class ImportStatementCn extends ImportStatement {

	public ImportStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(���� " + name() + ")";
	}

}
