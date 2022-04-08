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

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * BcClassWriter.
 *
 */
public class BcClassWriter extends ClassWriter {
	
	private Stack<MethodVisitor> mvs = new Stack<MethodVisitor>();

	public BcClassWriter(ClassReader paramClassReader, int paramInt) {
		super(paramClassReader, paramInt);
	}

	public BcClassWriter(int paramInt) {
		super(paramInt);
	}
	
	public void createMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
		mvs.push(this.visitMethod(paramInt, paramString1, paramString2, paramString3, paramArrayOfString));
	}
	
	public void endMethod() {
		mvs.pop();
	}
	
	public MethodVisitor getCurrMethod() {
		return mvs.peek();
	}
 
}
