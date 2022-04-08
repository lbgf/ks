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
import org.ks.ast.en.ContinueStatement;

/**
 * 继续(中文).
 *
 */
public class ContinueStatementCn extends ContinueStatement {
	public ContinueStatementCn(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(继续)";
	}
	
}
