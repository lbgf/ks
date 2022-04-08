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
import org.ks.ast.en.VarStatement;

/**
 * 变量声明(中文).
 *
 */
public class VarStatementCn extends VarStatement {
	
	public VarStatementCn(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(变量 " + name() + " " + type() + " " + initializer() + ")";
	}

}
