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
import org.ks.ast.en.TryStatement;

/**
 *  异常处理(中文).
 *
 */
public class TryStatementCn extends TryStatement {
	
	public TryStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(异常监控 " + tryBlock() + " 捕获 " + catchVar() + catchBlock() + " 收尾 " + finallyBlock() + ")";
	}

}
