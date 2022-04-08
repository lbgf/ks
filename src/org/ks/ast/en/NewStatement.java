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

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.KsClassInfo;
import org.ks.ast.KsObject;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.jvm.JavaKsClass;
import org.ks.runtime.Environment;
import org.ks.runtime.KsEnvironment;

/**
 * new�ؼ���.
 *
 */
public class NewStatement extends ASTList {
	public NewStatement(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public String toString() {
		return "(new  " + name() + " " + ")";
	}
	
	public Object eval(Environment env) {
    String name = name();
    if (env.getClass(name) != null) { // java��
    	try {
    		return new JavaKsClass(Class.forName(env.getClass(name).toString()), name);//Class.forName(env.getClass(name).toString()).newInstance(); // ��Ҫ��Ĭ�Ϲ��캯��������ᱨ������������͵�Arguments���н���Object�жϷ�֧ȥ
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new KsException(e.getMessage(), this);
    	}
    } else if (env.get(name) != null) { // �ű���д����
    	KsClassInfo ci = (KsClassInfo)env.get(name);
      KsEnvironment newEnv = new KsEnvironment(1, ci.environment());
      KsObject ko = new KsObject(ci, ci.size());
      newEnv.put(0, 0, ko);
      initObject(ci, ko, newEnv);
      return ko;
    }
		return null;
	}
	
	protected void initObject(KsClassInfo ci, KsObject obj, Environment env) {
		if (ci.superClass() != null) {
			initObject(ci.superClass(), obj, env);
		}
		ci.body().eval(env);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("New");  // test
		
		String name = name();
		if (env.getClass(name) != null) { // java��
    	try {
    		return new JavaKsClass(Class.forName(env.getClass(name).toString()), name);
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new KsException(e.getMessage(), this);
    	}
    } else if (env.get(name) != null) { // �ű���д����
    	KsClassInfo ci = (KsClassInfo)env.get(name);
    	KsObject ko = new KsObject(ci, ci.size(), ci.getClassName(), ci.getJavaClass());
    	return ko;
    }
		return null;
	}
	
}
