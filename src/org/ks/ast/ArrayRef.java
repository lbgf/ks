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

import org.ks.core.KsException;
import org.ks.runtime.Environment;

/**
 * ��������.
 *
 */
public class ArrayRef extends Postfix {
	public ArrayRef(List<ASTNode> c) {
		super(c);
	}

	public ASTNode index() {
		return child(0);
	}

	public String toString() {
		return "[" + index() + "]";
	}

	public Object eval(Environment env, Object value) {
		if (value instanceof Object[]) {
			Object index = index().eval(env);
			if (index instanceof Integer) {
				return ((Object[]) value)[(Integer) index];
			}
		}

		throw new KsException("������������", this);
	}

}
