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
import org.ks.ast.en.VarStatement;

/**
 * ��������(����).
 *
 */
public class VarStatementCn extends VarStatement {
	
	public VarStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(���� " + name() + " " + type() + " " + initializer() + ")";
	}

}
