/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast.en;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.ClassBody;
import org.ks.ast.KsClassInfo;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsClassLoader;
import org.ks.runtime.Environment;
import org.ks.runtime.MemberSymbols;
import org.ks.runtime.SymbolThis;
import org.ks.runtime.Symbols;

/**
 * ��ڵ�.
 *
 */
public class ClassStatement extends ASTList {
	public ClassStatement(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public String superClass() {
		if (numChildren() < 3)
			return null;
		else
			return ((ASTLeaf) child(1)).token().getText();
	}

	public ClassBody body() {
		return (ClassBody) child(numChildren() - 1);
	}

	public String toString() {
		String parent = superClass();
		if (parent == null)
			parent = "*";
		return "(class " + name() + " " + parent + " " + body() + ")";
	}

	public void lookup(Symbols syms) {
		
	}

	public Object eval(Environment env) {
		Symbols methodNames = new MemberSymbols(env.symbols(), MemberSymbols.METHOD);
		Symbols fieldNames = new MemberSymbols(methodNames, MemberSymbols.FIELD);
		KsClassInfo ci = new KsClassInfo(this, env, methodNames, fieldNames);
		env.put(name(), ci);
		ArrayList<FuncStatement> methods = new ArrayList<FuncStatement>();
		if (ci.superClass() != null)
			ci.superClass().copyTo(fieldNames, methodNames, methods);
		Symbols newSyms = new SymbolThis(fieldNames);
		body().lookup(newSyms, methodNames, fieldNames, methods);
		ci.setMethods(methods);
		return name();
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Class"); // test

		String className = "Script" + env.getScriptName() + name();
		
		bcOp.createClass(className);
		
  	// run����
  	bcOp.createMethod(BcOpcodes.ACC_PUBLIC, "run", null, "Ljava/lang/Object;");
  	bcOp.gcMethod().visitCode();
  	bcOp.gcMethod().visitParameter("evn", 0);
  	bcOp.gcMethod().visitTypeInsn(BcOpcodes.NEW, "java/lang/Object");
  	bcOp.gcMethod().visitInsn(BcOpcodes.DUP);
  	bcOp.invokeSpecial("java/lang/Object", "<init>", null, "V", false);
   	bcOp.returnObject();
   	bcOp.gcMethod().visitLocalVariable("this", "L"+className+";", null, bcOp.createLabel(), bcOp.createLabel(), 0);
  	bcOp.endMethod();
  	
  	// ���캯��
   	bcOp.createConstructor(BcOpcodes.ACC_PUBLIC, "Lorg/ks/runtime/Environment;");
   	bcOp.gcMethod().visitParameter("env", 0);
   	bcOp.gcMethod().visitCode();
   	bcOp.getThis();
   	bcOp.invokeSpecial("org/ks/bc/ScriptBase", "<init>", null, "V", false);
   	bcOp.getThis();
   	bcOp.gcMethod().visitVarInsn(BcOpcodes.ALOAD, 1);
   	bcOp.setField("org/ks/bc/ScriptBase", "env", "Lorg/ks/runtime/Environment;");
   	//--------
   	// ��������
   	Symbols methodNames = new MemberSymbols(env.symbols(), MemberSymbols.METHOD);
   	Symbols fieldNames = new MemberSymbols(methodNames, MemberSymbols.FIELD);
   	fieldNames.initVarIndex(); // ����BCģʽ
 		KsClassInfo ci = new KsClassInfo(this, env, methodNames, fieldNames);
 		env.put(name(), ci);
 		ArrayList<FuncStatement> methods = new ArrayList<FuncStatement>();
 		Symbols newSyms = new SymbolThis(fieldNames);
 		body().lookup(newSyms, methodNames, fieldNames, methods);
 		ci.setMethods(methods);
 		env.setSubScriptName(env.getScriptName() + name()); // ���ýű�������
 		ci.body().compile(env, bcOp);
 		env.setSubScriptName(null); // ����ű�������
   	//--------
   	bcOp.returnVoid();
   	bcOp.gcMethod().visitLocalVariable("this", "L"+className+";", null, bcOp.createLabel(), bcOp.createLabel(), 0);
   	bcOp.gcMethod().visitLocalVariable("env", "Lorg/ks/runtime/Environment;", null, bcOp.createLabel(), bcOp.createLabel(), 1);
   	bcOp.endConstructor();
		
  	bcOp.endClass();
  			
		// ��ȡ�ֽ���
    byte[] code = bcOp.getByteCode();
    
    try {
	    if (env.getSaveClassPath() != null) {
	      FileOutputStream fos = new FileOutputStream(env.getSaveClassPath()+className+".class");
	      //д�ļ�
	      fos.write(code);
	      //�ر������
	      fos.close();
	    }
    } catch(IOException e) {
    	e.printStackTrace();
    }
    
    KsClassLoader loader = env.getKsClassLoader();
    Class<?> clazz = loader.createClass(className, code, 0, code.length);
    ci.setClassName(className);
    ci.setJavaClass(clazz);
		return null;
	}

}
