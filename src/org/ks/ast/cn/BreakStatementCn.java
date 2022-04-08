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
import org.ks.ast.en.BreakStatement;

/**
 * 中断(中文).
 *
 */
public class BreakStatementCn extends BreakStatement {
	public BreakStatementCn(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(中断)";
	}
	
}
