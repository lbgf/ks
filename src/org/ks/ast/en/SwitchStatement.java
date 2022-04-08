/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast.en;

import java.util.List;

import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.runtime.Environment;

/**
 * switch条件节点.
 *
 */
public class SwitchStatement extends ASTList {
	public SwitchStatement(List<ASTNode> c) {
		super(c);
	}

	public ASTNode key() {
		return child(0);
	}

	public String toString() {
		String tmp = "(switch " + key();
		for (int i = 1; i < numChildren(); i++) {
			tmp += " case " + child(i) ;
		}
		return tmp;
	}

	public Object eval(Environment env) {
		Object c = key().eval(env);
		Object r = null;
		for (int i = 1; i < numChildren(); i++) {
			Object b = child(i).child(0).eval(env);
			if (c.equals(b)) {
				r = child(i).child(1).eval(env);
			} else if(r == null && i == numChildren()-1 && b.toString().equals("-1")) {
				r = child(i).child(1).eval(env);
			}
		}
		return r;
	}

}
