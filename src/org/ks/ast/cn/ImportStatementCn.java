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
import org.ks.ast.en.ImportStatement;

/**
 * 导入节点(中文).
 *
 */
public class ImportStatementCn extends ImportStatement {

	public ImportStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(导入 " + name() + ")";
	}

}
