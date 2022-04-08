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

import org.ks.runtime.Environment;

/**
 * 数组节点.
 *
 */
public class ArrayLiteral extends ASTList {
	public ArrayLiteral(List<ASTNode> list) {
		super(list);
	}

	public int size() {
		return numChildren();
	}
	
	public Object eval(Environment env) {
    int s = numChildren();
    Object[] res = new Object[s];
    int i = 0;
    for (ASTNode t: this) {
      res[i++] = t.eval(env);
    }
    return res;
}
}
