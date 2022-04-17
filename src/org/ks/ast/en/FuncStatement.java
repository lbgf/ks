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

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.BlockStatement;
import org.ks.ast.KsFunction;
import org.ks.ast.ParameterList;
import org.ks.ast.TypeStatement;
import org.ks.bc.BcOpcodes;
import org.ks.runtime.Environment;
import org.ks.runtime.SymbolThis;
import org.ks.runtime.Symbols;

/**
 * 函数节点.
 *
 */
public class FuncStatement extends ASTList {
	
	protected int index;
	protected int size;
	
	public FuncStatement(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public ParameterList parameters() {
		return (ParameterList) child(1);
	}

	public BlockStatement body() {
		return (BlockStatement)child(3);
	}

	public String toString() {
    return "(function " + name() + " " + parameters() + " " + type() + " " + body() + ")";
	}
	
	public void lookup(Symbols syms) {
    index = syms.putNew(name());
    size = ClosureStatement.lookup(syms, parameters(), body());
	}
	
	public Object eval(Environment env) {
		env.put(0, index, new KsFunction(parameters(), body(), env, size));
		return name();
	}
	
	public int locals() { 
		return size; 
	}
	
  public void lookupAsMethod(Symbols syms) {
    Symbols newSyms = new Symbols(syms);
    newSyms.putNew(SymbolThis.NAME);
    parameters().lookup(newSyms);
    body().lookup(newSyms);
    size = newSyms.size();
  }
  
  public TypeStatement type() { 
  	return (TypeStatement)child(2); 
  }
  
  public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Function");  // test
		
		KsFunction kf = new KsFunction(parameters(), body(), env, size, name());
		env.put(0, index, kf);
		Environment newEnv = kf.makeEnv();
		
		// 处理java.lang.VerifyError问题
		newEnv.initSmf();
		newEnv.putFrameObj(newEnv.getSubScriptName() == null?"Script" + newEnv.getScriptName():"Script"+newEnv.getSubScriptName());
  	//
		
		bcOp.createMethod(BcOpcodes.ACC_PUBLIC, name(), parameters().getParamsType(), parameters().getReturnType());
		parameters().compile(newEnv, bcOp);
  	bcOp.gcMethod().visitCode();
  	body().compile(newEnv, bcOp);
  	
  	if (!body().checkReturnKeyword()) {
	  	bcOp.gcMethod().visitTypeInsn(BcOpcodes.NEW, "java/lang/Object");
	  	bcOp.gcMethod().visitInsn(BcOpcodes.DUP);
	  	bcOp.invokeSpecial("java/lang/Object", "<init>", null, "V", false);
	  	bcOp.returnObject();
  	}
  	
  	bcOp.gcMethod().visitLocalVariable("this", "L"+"Script"+(env.getSubScriptName()==null?env.getScriptName():env.getSubScriptName())+";", null, bcOp.createLabel(), bcOp.createLabel(), 0);
  	bcOp.endMethod();
		return null;
  }
  
}
