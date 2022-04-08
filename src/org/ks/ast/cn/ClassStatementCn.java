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
import org.ks.ast.en.ClassStatement;

/**
 * ��ڵ�(����).
 *
 */
public class ClassStatementCn extends ClassStatement {
	public ClassStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		String parent = superClass();
		if (parent == null)
			parent = "*";
		return "(�� " + name() + " " + parent + " " + body() + ")";
	}

}
