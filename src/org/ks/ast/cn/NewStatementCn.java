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
import org.ks.ast.en.NewStatement;

/**
 * 对象声明(中文).
 *
 */
public class NewStatementCn extends NewStatement {
	
	public NewStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(创建 " + name() + " " + ")";
	}

}
