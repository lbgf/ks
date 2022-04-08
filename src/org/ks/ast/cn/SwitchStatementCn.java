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
import org.ks.ast.en.SwitchStatement;

/**
 * switch�����ڵ�(����).
 *
 */
public class SwitchStatementCn extends SwitchStatement {
	public SwitchStatementCn(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		String tmp = "(���� " + key();
		for (int i = 1; i < numChildren(); i++) {
			tmp += " ���� " + child(i) ;
		}
		return tmp;
	}
	
}
