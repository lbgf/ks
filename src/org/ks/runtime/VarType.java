/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.runtime;

/**
 * ����.
 *
 */
public class VarType {

	private Class<?> c;
	private boolean isJavaObject = false; // java����
	private boolean isJavaStaticClass = false; // java��̬��
	private boolean isClassLib = false; // ���
	private boolean isExternal = false; // �ⲿ����
	private boolean isThis = false; // thisָ��
	private String scriptName; // �ű����ƣ���Ҫ���ڼ�¼����
	private Object[] arrType = null; // ��������

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
