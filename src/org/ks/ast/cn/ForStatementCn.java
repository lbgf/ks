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
import org.ks.ast.en.ForStatement;

/**
 * 循环节点(中文).
 *
 */
public class ForStatementCn extends ForStatement {
	public ForStatementCn(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(循环 " + initializer() + " " + condition() + " " + incremental() + " " + body() + ")";
	}
	
}
