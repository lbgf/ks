/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast;

import java.util.List;

/**
 * ��������.
 *
 */
public class TypeStatement extends ASTList {
	public static final String UNDEFINED = "<undefined>";

	public TypeStatement(List<ASTNode> c) {
		super(c);
	}

	public String getType() {
		if (numChildren() > 0)
			return ((ASTLeaf) child(0)).token().getText();
		else
			return UNDEFINED;
	}

	public String toString() {
		return ":" + getType();
	}
}
