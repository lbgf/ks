/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.bc;

import java.util.Stack;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 字节码操作类.
 *
 */
public class BcOpcodes implements Opcodes {
 	
	private byte[] code;
	
	private Stack<BcClassWriter> cws = new Stack<BcClassWriter>();
	
	public BcOpcodes() {
		
	}
	
	public BcClassWriter gcClass() {
		return cws.peek();
	}
	
	public MethodVisitor gcMethod() {
		return gcClass().getCurrMethod();
	}
	
	public void createClass(String name) {
		cws.push(new BcClassWriter(BcClassWriter.COMPUTE_MAXS));
		gcClass().visit(V1_8, ACC_PUBLIC + ACC_SUPER, name, null, "org/ks/bc/ScriptBase", null);
	}
	
	public void createInnerScriptClass(String name) {
		cws.push(new BcClassWriter(BcClassWriter.COMPUTE_MAXS));
		gcClass().visit(V1_8, ACC_PUBLIC + ACC_SUPER, name, null, "org/ks/bc/ScriptBase", null);
	}
	
	public void endClass() {
		code = gcClass().toByteArray();
		cws.pop();
	}
	
	public void createConstructor(int access, String pTypes) {
		if (pTypes == null) {
			pTypes = "";
		}
		gcClass().createMethod(access, "<init>", "("+pTypes+")V", null, null);
	}
	
	public void endConstructor() {
		gcMethod().visitMaxs(0, 0);
		gcMethod().visitEnd();
		gcClass().endMethod();
	}
	
	public void createMethod(int access, String name, String pTypes, String rType) {
		if (pTypes == null) {
			pTypes = "";
		}
		if (rType == null) {
			rType = "V";
		}
		gcClass().createMethod(access, name, "("+pTypes+")" + rType, null, null);
	}
	
	public void endMethod() {
		gcMethod().visitMaxs(0, 0);
		gcMethod().visitEnd();
		gcClass().endMethod();
	}
	
	public void getThis() {
		gcMethod().visitVarInsn(ALOAD, 0); // this
	}
	
	public void invokeSpecial(String className, String method, String pTypes, String rType, boolean paramBoolean) {
		if (pTypes == null) {
			pTypes = "";
		}
		if (rType == null) {
			rType = "V";
		}
		gcMethod().visitMethodInsn(INVOKESPECIAL, className, method, "("+pTypes+")" + rType, paramBoolean);
	}

	public void invokeVirtual(String className, String method, String pTypes, String rType, boolean paramBoolean) {
		if (pTypes == null) {
			pTypes = "";
		}
		if (rType == null) {
			rType = "V";
		}
		gcMethod().visitMethodInsn(INVOKEVIRTUAL, className, method, "("+pTypes+")" + rType, paramBoolean);
	}
	
	public void invokeStatic(String className, String method, String pTypes, String rType, boolean paramBoolean) {
		if (pTypes == null) {
			pTypes = "";
		}
		if (rType == null) {
			rType = "V";
		}
		gcMethod().visitMethodInsn(INVOKESTATIC, className, method, "("+pTypes+")" + rType, paramBoolean);
	}
	
	public void getField(String className, String field, String rType) {
		gcMethod().visitFieldInsn(GETFIELD, className, field, rType);
	}
	
	public void setField(String className, String field, String rType) {
		gcMethod().visitFieldInsn(PUTFIELD, className, field, rType);
	}
	
	public void getStaticField(String className, String field, String rType) {
		gcMethod().visitFieldInsn(GETSTATIC, className, field, rType);
	}
	
	public void createField(int access, String name, String type, String signature, Object value) {
		FieldVisitor fv = gcClass().visitField(access, name, type, signature, value);
    fv.visitEnd();
	}
	
	public void returnVoid() {
		gcMethod().visitInsn(RETURN);
	}
	
	public void returnObject() {
		gcMethod().visitInsn(ARETURN);
	}
	
	public Label createLabel() {
		return new Label();
	}
	
	public byte[] getByteCode() {
		return code;
	}

}
