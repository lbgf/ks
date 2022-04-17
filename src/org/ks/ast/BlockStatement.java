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

import org.ks.ast.en.ReturnStatement;
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;
import org.ks.runtime.KsEnvironment;
import org.ks.runtime.Symbols;

/**
 * �����.
 *
 */
public class BlockStatement extends ASTList {

	public BlockStatement(List<ASTNode> c) {
		super(c);
	}
	
	// �¼ӵģ���������ռ�����
	public void lookup(Symbols syms) {
		Symbols newSyms = new Symbols(syms);
		for (ASTNode t : this) {
			t.lookup(newSyms);
		}
	}
	//
	
	// �¼ӵģ����BC����Ƿ���return�ؼ���
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
		// �¼ӵģ���������ռ�����
		KsEnvironment newEnv = new KsEnvironment(10, env);
		//
		
		for (ASTNode t : this) {
			if (env.getReturnList().peek().intValue() == 1) {
				// �����˲����κ���
				
			} else if (!(t instanceof NullStatement) && (!(t instanceof ReturnStatement))) {
				if(env.getBreakList().getSize() > 0 && env.getBreakList().peek().intValue() == 1) {
					// �ж��˲����κ���
				} else {
					if(env.getContinueList().getSize() > 0 && env.getContinueList().peek().intValue() == 1) {
						// �����˲����κ���
					} else {
						// result = t.eval(env);
						
						// �¼ӵģ���������ռ�����
						result = t.eval(newEnv);
						// 
					}
				}
			} else if(t instanceof ReturnStatement) {
				// return t.eval(env);
				
				// �¼ӵģ���������ռ�����
				return t.eval(newEnv);
				// 
			}
		}
		return result;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Block");  // test
		
		// �¼ӵģ���������ռ�����
		KsEnvironment newEnv = new KsEnvironment(10, env);
		//
		
		// ����java.lang.VerifyError����
		newEnv.initSmf(env.getSmf());
		// 
		
		for (ASTNode t : this) {
			// t.compile(env, bcOp);

			// �¼ӵģ���������ռ�����
			t.compile(newEnv, bcOp);
			//
		}
		return null;
	}

}
