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
import org.ks.ast.en.ReturnStatement;

/**
 * 返回(中文).
 *
 */
public class ReturnStatementCn extends ReturnStatement {
	public ReturnStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(返回 " + result() + ")";
	}
	
}
