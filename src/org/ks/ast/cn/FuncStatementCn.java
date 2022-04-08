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
import org.ks.ast.en.FuncStatement;

/**
 * 函数节点(中文).
 *
 */
public class FuncStatementCn extends FuncStatement {
	public FuncStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(函数 " + name() + " " + parameters() + " " + type() + " " + body() + ")";
	}
	
	
}
