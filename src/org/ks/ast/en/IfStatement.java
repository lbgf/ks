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
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;
import org.objectweb.asm.Label;

/**
 * if条件节点.
 *
 */
public class IfStatement extends ASTList {
	public IfStatement(List<ASTNode> c) {
		super(c);
	}

	public ASTNode condition() {
		return child(0);
	}

	public ASTNode thenBlock() {
		return child(1);
	}

	public ASTNode elseBlock() {
		return numChildren() > 2 ? child(2) : null;
	}

	public String toString() {
		return "(if " + condition() + " " + thenBlock() + " else " + elseBlock() + ")";
	}

	public Object eval(Environment env) {
		Object c = condition().eval(env);
		if (c instanceof Boolean && ((Boolean) c).booleanValue() != false) {
			return thenBlock().eval(env);
		} else {
			ASTNode b = elseBlock();
			if (b == null)
				return 0;
			else
				return b.eval(env);
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("If");  // test
		condition().compile(env, bcOp);
		Label l1 = bcOp.createLabel();
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.IFEQ, l1);
    thenBlock().compile(env, bcOp);
    ASTNode b = elseBlock();
		if (b != null) {
			Label l2 = bcOp.createLabel();
			bcOp.gcMethod().visitJumpInsn(BcOpcodes.GOTO, l2);
			bcOp.gcMethod().visitLabel(l1);
			// 处理java.lang.VerifyError问题
			if (env.getSmf().getNewSize() > env.getSmf().getOldSize()) {
				env.getSmf().syncSize();
				try {
					bcOp.gcMethod().visitFrame(BcOpcodes.F_FULL, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, new Object[]{});
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME, 0, null, 0, null);
			}
			//
			b.compile(env, bcOp);
			
			bcOp.gcMethod().visitLabel(l2);
			// 处理java.lang.VerifyError问题
			if (env.getSmf().getNewSize() > env.getSmf().getOldSize()) {
				env.getSmf().syncSize();
				try {
					bcOp.gcMethod().visitFrame(BcOpcodes.F_FULL, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, new Object[]{});
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME, 0, null, 0, null);
			}
			//
		} else {
			bcOp.gcMethod().visitLabel(l1);
			// 处理java.lang.VerifyError问题
			// System.out.println(l1.getOffset());
			// System.out.println("if,," + env.getFrameObjs() + "," + env.getSmf().getNewSize() + "," + env.getSmf().getOldSize());
			if (env.getSmf().getNewSize() > env.getSmf().getOldSize()) {
				env.getSmf().syncSize();
				try {
					bcOp.gcMethod().visitFrame(BcOpcodes.F_FULL, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, new Object[]{});
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME, 0, null, 0, null);
			}
			//
		}
		
		return null;
	}

}
