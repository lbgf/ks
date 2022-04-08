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
import org.ks.ast.en.IncludeStatement;

/**
 * 引用其他脚本节点（中文）.
 *
 */
public class IncludeStatementCn extends IncludeStatement {

	public IncludeStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(包含 " + name() + ")";
	}

}
