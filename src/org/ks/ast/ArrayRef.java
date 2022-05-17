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
 * 数组引用.
 *
 */
public class ArrayRef extends Postfix {
	public ArrayRef(List<ASTNode> c) {
		super(c);
	}

	public ASTNode index() {
		return child(0);
	}

	public String toString() {
		return "[" + index() + "]";
	}

	public Object eval(Environment env, Object value) {
		if (value instanceof Object[]) {
			Object index = index().eval(env);
			if (index instanceof Integer) {
				return ((Object[]) value)[(Integer) index];
			}
		}

		throw new KsException("错误的数组访问", this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp, Object value) {
		// System.out.println("ArrayRef");  // test
		
		if (value instanceof VarType) {
			// VarType type = (VarType)value;
			Object obj = index().compile(env, bcOp);
			if (obj instanceof VarType) {
				VarType iType = (VarType)obj;
				if(BcGenerator.isWrapperType(iType)) {
					iType = BcGenerator.toValueType((VarType)iType, bcOp);
				}
			} else {
				throw new KsException("不能识别的类型", this);
			}
			bcOp.gcMethod().visitInsn(BcOpcodes.AALOAD);
		} else {
			throw new KsException("不能识别的类型", this);
		}
		
		return new VarType(Object.class);
	}

}
