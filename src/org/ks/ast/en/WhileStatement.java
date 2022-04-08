/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
 * ����.
 *
 */
public class WhileStatement extends ASTList {
	public WhileStatement(List<ASTNode> c) {
		super(c);
	}

	public ASTNode condition() {
		return child(0);
	}

	public ASTNode body() {
		return child(1);
	}

	public String toString() {
		return "(while " + condition() + " " + body() + ")";
	}

	public Object eval(Environment env) {
		Object result = 0;
		env.getBreakList().push(0);
		for (;;) {
			Object c = condition().eval(env);
			if ((c instanceof Boolean && ((Boolean) c).booleanValue() == false) 
					|| env.getBreakList().peek().intValue() == 1) {
				env.getBreakList().pop();
				return result;
			} else {
				env.getContinueList().push(0);
				result = body().eval(env);
				env.getContinueList().pop();
			}
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("While"); // test
		
		Label l1 = new Label();
		env.getBcBreakList().push(l1);
		Label l2 = new Label();
		env.getBcContinueList().push(l2);
		/*
		��������VerifyError�쳣����Ҫ����frame���ã����ǻ�û�д���ñ�����index���⣬Ŀǰ��Ծ̫������Ȳ������������ʱ�Ӳ���-noverify����jvm��֤
		if (env.getFrameObjs().size() > 0) {
			bcOp.gcMethod().visitFrame(BcOpcodes.F_APPEND, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, null);
			env.clearFrameObjs();
		} else {
			bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME, 0, null, 0, null);
		}
		*/
		bcOp.gcMethod().visitLabel(l2);
		condition().compile(env, bcOp);
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.IFEQ, l1);
    body().compile(env, bcOp);
    bcOp.gcMethod().visitJumpInsn(BcOpcodes.GOTO, l2);
    bcOp.gcMethod().visitLabel(l1);
    
    // bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME, 0, null, 0, null);
    
    env.getBcContinueList().pop();
    env.getBcBreakList().pop();
		return null;
	}

}
