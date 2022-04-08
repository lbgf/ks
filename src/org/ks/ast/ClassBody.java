/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.util.ArrayList;
import java.util.List;

import org.ks.ast.en.FuncStatement;
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;

/**
 * 类体.
 *
 */
public class ClassBody extends ASTList {
	public ClassBody(List<ASTNode> c) {
		super(c);
	}
	
	public void lookup(Symbols syms, Symbols methodNames, Symbols fieldNames, ArrayList<FuncStatement> methods) {
		for (ASTNode t : this) {
			if (t instanceof FuncStatement) {
				FuncStatement func = (FuncStatement) t;
				int oldSize = methodNames.size();
				int i = methodNames.putNew(func.name());
				if (i >= oldSize) {
					methods.add(func);
				} else {
					methods.set(i, func);
				}
				((FuncStatement) func).lookupAsMethod(fieldNames);
			} else {
				t.lookup(syms);
			}
		}
	}

	public Object eval(Environment env) {
		for (ASTNode t : this) {
			if (!(t instanceof FuncStatement)) {
				t.eval(env);
			}
		}
		return null;
	}

	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Class Body"); // test

		for (ASTNode t : this) {
			t.compile(env, bcOp);
		}
		return null;
	}
}