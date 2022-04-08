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
import org.ks.ast.en.SwitchStatement;

/**
 * switch条件节点(中文).
 *
 */
public class SwitchStatementCn extends SwitchStatement {
	public SwitchStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		String tmp = "(开关 " + key();
		for (int i = 1; i < numChildren(); i++) {
			tmp += " 门锁 " + child(i) ;
		}
		return tmp;
	}
	
}
