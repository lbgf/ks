/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.util.List;

/**
 * 变量类型.
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
