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

import org.ks.runtime.Environment;

/**
 * ����ڵ�.
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
