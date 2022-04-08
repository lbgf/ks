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
import org.ks.ast.en.WhileStatement;

/**
 * 迭代(中文).
 *
 */
public class WhileStatementCn extends WhileStatement {
	public WhileStatementCn(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(迭代 " + condition() + " " + body() + ")";
	}
	
}
