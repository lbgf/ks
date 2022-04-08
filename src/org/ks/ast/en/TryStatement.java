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
 * 异常处理.
 *
 */
public class TryStatement extends ASTList {
	public TryStatement(List<ASTNode> c) {
		super(c);
	}
	
	public ASTNode tryBlock() {
		return child(0);
	}
	
	public ASTNode catchVar() {
		return child(1);
	}

	public ASTNode catchBlock() {
		return child(2);
	}
	
	public ASTNode finallyBlock() {
		return child(3);
	}

	public String toString() {
		return "(try " + tryBlock() + " catch " + catchVar() + catchBlock() + " finally " + finallyBlock() + ")";
	}

	public Object eval(Environment env) {
		Object result = 0;
		
		try {
			tryBlock().eval(env);
		} catch(Exception e) {
			env.put(catchVar().child(0).child(0).toString(), e);
			catchBlock().eval(env);
		} finally {
			finallyBlock().eval(env);
		}
				
		return result;
	}

}
