/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.natives;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.ks.ast.ASTNode;
import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * 调用内置函数.
 *
 */
public class NativeFunction {
	protected Method method;
	protected String name;
	protected int numParams;

	public NativeFunction(String n, Method m) {
		name = n;
		method = m;
		numParams = m.getParameterTypes().length;
	}

	@Override
	public String toString() {
		return "<innerFunction:" + hashCode() + ">";
	}

	public int numOfParameters() {
		return numParams;
	}
	
	public Method getMethod() {
		return method;
	}

	public Object invoke(Object[] args, ASTNode tree) {
		try {
			return method.invoke(null, args);
		} catch (Exception e) {
			throw new KsException("内置函数错误: " + name + "，原因：" + e.getMessage(), tree);
		}
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		String className = method.getDeclaringClass().getName().replaceAll("[.]", "/");
		String methodName = name;
		String params = "";
		Parameter[] paramArr = method.getParameters();
		
		for(int i = 0; i < paramArr.length; i++) {
			params += BcGenerator.getClassType(paramArr[i].getType());
		}
		
		String returnValue = BcGenerator.getClassType(method.getReturnType());
		VarType returnType = BcGenerator.getReturnType2(returnValue);
		
		bcOp.invokeStatic(className, methodName, params, returnValue, false);
		//mv.visitInsn(Opcodes.POP);
		return returnType;
	}
}
