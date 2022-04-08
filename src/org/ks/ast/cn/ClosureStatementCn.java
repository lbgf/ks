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
import org.ks.ast.en.ClosureStatement;

/**
 * 闭包节点(中文).
 *
 */
public class ClosureStatementCn extends ClosureStatement {
	public ClosureStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(闭包 " + parameters() + " " + body() + ")";
	}

}
