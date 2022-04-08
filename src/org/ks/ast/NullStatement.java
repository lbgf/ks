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
 * 空值节点.
 *
 */
public class NullStatement extends ASTList {
	public NullStatement(List<ASTNode> c) {
		super(c);
	}
}
