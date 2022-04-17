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

import org.ks.ast.en.ReturnStatement;
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;
import org.ks.runtime.KsEnvironment;
import org.ks.runtime.Symbols;

/**
 * 代码块.
 *
 */
public class BlockStatement extends ASTList {

	public BlockStatement(List<ASTNode> c) {
		super(c);
	}
	
	// 新加的，处理变量空间问题
	public void lookup(Symbols syms) {
		Symbols newSyms = new Symbols(syms);
		for (ASTNode t : this) {
			t.lookup(newSyms);
		}
	}
	//
	
	// 新加的，针对BC检查是否有return关键字
	public boolean checkReturnKeyword() {
		for (ASTNode t : this) {
			if (t instanceof ReturnStatement) {
				return true;
			}
		}
		return false;
	}
	
	public Object eval(Environment env) {
		Object result = 0;
		// 新加的，处理变量空间问题
		KsEnvironment newEnv = new KsEnvironment(10, env);
		//
		
		for (ASTNode t : this) {
			if (env.getReturnList().peek().intValue() == 1) {
				// 返回了不做任何事
				
			} else if (!(t instanceof NullStatement) && (!(t instanceof ReturnStatement))) {
				if(env.getBreakList().getSize() > 0 && env.getBreakList().peek().intValue() == 1) {
					// 中断了不做任何事
				} else {
					if(env.getContinueList().getSize() > 0 && env.getContinueList().peek().intValue() == 1) {
						// 跳过了不做任何事
					} else {
						// result = t.eval(env);
						
						// 新加的，处理变量空间问题
						result = t.eval(newEnv);
						// 
					}
				}
			} else if(t instanceof ReturnStatement) {
				// return t.eval(env);
				
				// 新加的，处理变量空间问题
				return t.eval(newEnv);
				// 
			}
		}
		return result;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Block");  // test
		
		// 新加的，处理变量空间问题
		KsEnvironment newEnv = new KsEnvironment(10, env);
		//
		
		// 处理java.lang.VerifyError问题
		newEnv.initSmf(env.getSmf());
		// 
		
		for (ASTNode t : this) {
			// t.compile(env, bcOp);

			// 新加的，处理变量空间问题
			t.compile(newEnv, bcOp);
			//
		}
		return null;
	}

}
