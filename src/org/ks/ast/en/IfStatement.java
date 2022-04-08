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
		/*if (numChildren() > 2 && child(numChildren() - 1).numChildren() == 1) {
			return child(numChildren() - 1);
		} else {
			return null;
		}*/
		return numChildren() > 2 ? child(2) : null;
	}

	public String toString() {
		/*String tmp = "(if " + condition() + " " + thenBlock();
		for (int i = 2; i < numChildren(); i++) {
			System.out.println(i + ":" + child(i).numChildren());
			if (child(i).numChildren() == 1) {
				tmp += " else " + elseBlock() + ")";
			} else if (child(i).numChildren() == 2) {
				tmp += " else " + child(i) ;
			}
		}*/
		return "(if " + condition() + " " + thenBlock() + " else " + elseBlock() + ")";
		//return tmp;
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
			/*
			for (int i = 2; i < numChildren(); i++) {
				if (child(i).numChildren() == 1) {
					ASTNode b = elseBlock();
					if (b == null)
						return 0;
					else
						return b.eval(env);
				} else if (child(i).numChildren() == 2) {
					Object b = child(i).child(0).eval(env);
					if (b instanceof Integer && ((Integer) b).intValue() != FALSE) {
						return child(i).child(1).eval(env);
					} 
				}
			}
			return 0;*/
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
			b.compile(env, bcOp);
			bcOp.gcMethod().visitLabel(l2);
		} else {
			bcOp.gcMethod().visitLabel(l1);
		}
		return null;
	}

}
