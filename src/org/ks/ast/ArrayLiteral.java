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

/**
 * 数组节点.
 *
 */
public class ArrayLiteral extends ASTList {
	public ArrayLiteral(List<ASTNode> list) {
		super(list);
	}

	public int size() {
		return numChildren();
	}
	
	public Object eval(Environment env) {
    int num = numChildren();
    Object[] res = new Object[num];
    int i = 0;
    for (ASTNode t: this) {
      res[i++] = t.eval(env);
    }
    return res;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("ArrayLiteral"); // test
		
		int num = numChildren();
		
		BcGenerator.pushConstant(bcOp, num);
		bcOp.gcMethod().visitTypeInsn(BcOpcodes.ANEWARRAY, "java/lang/Object");
		
		int cnt = 0;
		VarType rType = new VarType(Object[].class);
		rType.setArrType(new Object[num]);
		
		for (ASTNode t: this) {
			bcOp.gcMethod().visitInsn(BcOpcodes.DUP);
			BcGenerator.pushConstant(bcOp, cnt);
			Object obj = t.compile(env, bcOp);
			if (obj instanceof VarType) {
				VarType type = (VarType)obj;
				if(BcGenerator.isValueType(type)) {
					type = BcGenerator.toWrapperType((VarType)type, bcOp);
				}
				rType.getArrType()[cnt] = type;
				bcOp.gcMethod().visitInsn(BcOpcodes.AASTORE); 
			} else {
				throw new KsException("不能识别的类型", this);
			}
			cnt++;
			     
    }
		return rType; 
	} 
}
