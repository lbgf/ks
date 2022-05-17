/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.runtime;

/**
 * 类型.
 *
 */
public class VarType {

	private Class<?> c;
	private boolean isJavaObject = false; // java对象
	private boolean isJavaStaticClass = false; // java静态类
	private boolean isClassLib = false; // 类库
	private boolean isExternal = false; // 外部变量
	private boolean isThis = false; // this指针
	private String scriptName; // 脚本名称，主要用于记录类名
	private Object[] arrType = null; // 数组类型

	public VarType() {
	}
	
	public VarType(Class<?> c) {
		this.c = c;
	}
	
	public VarType(String scriptName) {
		this.scriptName = scriptName;
	}

	public void setJavaClass(Class<?> c) {
		this.c = c;
	}

	public Class<?> getJavaClass() {
		return c;
	}

	public boolean isJavaObject() {
		return isJavaObject;
	}

	public void setJavaObject(boolean isJavaObject) {
		this.isJavaObject = isJavaObject;
	}

	public boolean isJavaStaticClass() {
		return isJavaStaticClass;
	}

	public void setJavaStaticClass(boolean isJavaStaticClass) {
		this.isJavaStaticClass = isJavaStaticClass;
	}
	
	public boolean isClassLib() {
		return isClassLib;
	}

	public void setClassLib(boolean isClassLib) {
		this.isClassLib = isClassLib;
	}
	
	public boolean isExternal() {
		return isExternal;
	}

	public void setExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}
	
	public boolean isThis() {
		return isThis;
	}

	public void setThis(boolean isThis) {
		this.isThis = isThis;
	}
	
	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public Object[] getArrType() {
		return arrType;
	}

	public void setArrType(Object[] arrType) {
		this.arrType = arrType;
	}

}
