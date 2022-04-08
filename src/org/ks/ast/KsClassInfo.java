/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.util.ArrayList;

import org.ks.ast.en.ClassStatement;
import org.ks.ast.en.FuncStatement;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;

/**
 * 扩展类信息.
 *
 */
public class KsClassInfo extends ClassInfo {
	
	protected String className;
	protected Class<?> javaClass;
	protected Symbols methods;
	protected Symbols fields;
	protected FuncStatement[] methodDefs;

	public KsClassInfo(ClassStatement cs, Environment env, Symbols methods, Symbols fields) {
		super(cs, env);
		this.methods = methods;
		this.fields = fields;
		this.methodDefs = null;
	}
	
	// 用于BC模式
	public KsClassInfo(ClassStatement cs, Environment env, Symbols methods, Symbols fields, 
			String className, Class<?> javaClass) {
		this(cs, env, methods, fields);
		this.className = className;
		this.javaClass = javaClass;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public int size() {
		return fields.size();
	}

	@Override
	public KsClassInfo superClass() {
		return (KsClassInfo) superClass;
	}

	public void copyTo(Symbols f, Symbols m, ArrayList<FuncStatement> mlist) {
		f.append(fields);
		m.append(methods);
		for (FuncStatement def : methodDefs)
			mlist.add(def);
	}

	public Integer fieldIndex(String name) {
		return fields.find(name);
	}

	public Integer methodIndex(String name) {
		return methods.find(name);
	}

	public Object method(KsObject self, int index) {
		FuncStatement def = methodDefs[index];
		return new KsMethod(def.parameters(), def.body(), environment(), ((FuncStatement) def).locals(), self);
	}

	public void setMethods(ArrayList<FuncStatement> methods) {
		methodDefs = methods.toArray(new FuncStatement[methods.size()]);
	}
	
}
