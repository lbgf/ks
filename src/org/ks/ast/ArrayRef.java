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

import org.ks.core.KsException;
import org.ks.runtime.Environment;

/**
 * 数组引用.
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

		throw new KsException("错语的数组访问", this);
	}

}
