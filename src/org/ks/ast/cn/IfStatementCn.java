/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast.cn;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.IfStatement;

/**
 * if�����ڵ�(����).
 *
 */
public class IfStatementCn extends IfStatement {
	public IfStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		/*String tmp = "(��� " + condition() + " " + thenBlock();
		for (int i = 2; i < numChildren(); i++) {
			if (child(i).numChildren() == 1) {
				tmp += " ���� " + elseBlock() + ")";
			} else if (child(i).numChildren() == 2) {
				tmp += " ���� " + child(i);
			}
		}
		return tmp;*/
		return "(��� " + condition() + " " + thenBlock() + " ���� " + elseBlock() + ")";
	}
	
}
