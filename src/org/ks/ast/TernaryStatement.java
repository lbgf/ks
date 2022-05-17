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

import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;
import org.objectweb.asm.Label;

/**
 * 三元运算.
 *
 */
public class TernaryStatement extends ASTList {
	public TernaryStatement(List<ASTNode> c) {
		super(c);
	}

	public ASTNode condition() {
		return child(0);
	}

	public ASTNode trueBlock() {
		return child(1);
	}
	
	public ASTNode flaseBlock() {
		return child(2);
	}

	public String toString() {
		return "( " + condition() + " ? " + trueBlock() + " : " + flaseBlock() + " )";
	}

	public Object eval(Environment env) {
		Object c = condition().eval(env);
		if (c instanceof Boolean && ((Boolean) c).booleanValue() != false) {
			return trueBlock().eval(env);
		} else {
			ASTNode b = flaseBlock();
			if (b == null)
				return 0;
			else
				return b.eval(env);
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Ternary");  // test
		
		condition().compile(env, bcOp);
		Label l1 = bcOp.createLabel();
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.IFEQ, l1);
		Object obj = trueBlock().compile(env, bcOp);
    
		Label l2 = bcOp.createLabel();
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.GOTO, l2);
		bcOp.gcMethod().visitLabel(l1);
		// 处理java.lang.VerifyError问题
		if (env.getSmf().getNewSize() > env.getSmf().getOldSize()) {
			env.getSmf().syncSize();
			bcOp.gcMethod().visitFrame(BcOpcodes.F_FULL, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, new Object[]{});
		} else {
			bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME, 0, null, 0, null);
		}
		//
		
		flaseBlock().compile(env, bcOp);
		
		bcOp.gcMethod().visitLabel(l2);
		// 处理java.lang.VerifyError问题
		if (env.getSmf().getNewSize() > env.getSmf().getOldSize()) {
			env.getSmf().syncSize();
			bcOp.gcMethod().visitFrame(BcOpcodes.F_FULL, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, new Object[]{});
		} else {
			if (obj instanceof VarType) {
				bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME1, 0, null, 1, new Object[]{BcGenerator.getClassType2(((VarType)obj).getJavaClass())});
			} else {
				throw new KsException("不支持的类型", this);
			}
		}
		//
		
		return obj;
	}

}
