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
import org.ks.ast.en.IfStatement;

/**
 * if条件节点(中文).
 *
 */
public class IfStatementCn extends IfStatement {
	public IfStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		/*String tmp = "(如果 " + condition() + " " + thenBlock();
		for (int i = 2; i < numChildren(); i++) {
			if (child(i).numChildren() == 1) {
				tmp += " 否则 " + elseBlock() + ")";
			} else if (child(i).numChildren() == 2) {
				tmp += " 否则 " + child(i);
			}
		}
		return tmp;*/
		return "(如果 " + condition() + " " + thenBlock() + " 否则 " + elseBlock() + ")";
	}
	
}
