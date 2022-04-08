/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast.cn;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.ClassStatement;

/**
 * 类节点(中文).
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
		return "(类 " + name() + " " + parent + " " + body() + ")";
	}

}
