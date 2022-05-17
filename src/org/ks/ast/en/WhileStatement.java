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
import org.ks.ast.Name;
import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;
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
		
		// ����java.lang.VerifyError����
		// ��������VerifyError�쳣����Ҫ����frame���ã�����ʱ�Ӳ���-noverify����jvm��֤
		// System.out.println("while,," + env.getFrameObjs() + "," + env.getSmf().getNewSize() + "," + env.getSmf().getOldSize());
		if (env.getSmf().getNewSize() > env.getSmf().getOldSize()) {
			env.getSmf().syncSize();
			bcOp.gcMethod().visitFrame(BcOpcodes.F_FULL, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, new Object[]{});
		} else {
			bcOp.gcMethod().visitFrame(BcOpcodes.F_SAME, 0, null, 0, null);
		}
		//
		
		bcOp.gcMethod().visitLabel(l2);
		if (condition() instanceof Name) {
			Object obj = condition().compile(env, bcOp);
			if (obj instanceof VarType) {
				VarType type = (VarType)obj;
				if(BcGenerator.isWrapperType(type)) {
					type = BcGenerator.toValueType((VarType)type, bcOp);
				}
			}
			// to do���ⲿ����null��err�����
		} else {
			condition().compile(env, bcOp);
		}
		bcOp.gcMethod().visitJumpInsn(BcOpcodes.IFEQ, l1);
    body().compile(env, bcOp);
    bcOp.gcMethod().visitJumpInsn(BcOpcodes.GOTO, l2);
    bcOp.gcMethod().visitLabel(l1);
    
    // ����java.lang.VerifyError����
    bcOp.gcMethod().visitFrame(BcOpcodes.F_FULL, env.getFrameObjs().size(), env.getFrameObjs().toArray(), 0, new Object[]{});
    // ��һ��ʲô�������ķ�������ʱ�ܿ�frame����ͬһ�еĳ�ͻ
    bcOp.getThis();
		bcOp.invokeVirtual("org/ks/bc/ScriptBase", "doNothing", "", "V", false);
    //
		//
    
    env.getBcContinueList().pop();
    env.getBcBreakList().pop();
		return null;
	}

}
