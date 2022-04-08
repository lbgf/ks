/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.jvm;

/**
 * ���java�õ�ks��.
 *
 */
public class JavaKsClass {

	protected Class<?> clazz;
	protected String method;

	public JavaKsClass(Class<?> clazz, String method) {
		this.clazz = clazz;
		this.method = method;
	}
	
	public Class<?> getJavaClass() {
		return clazz;
	}
	
	public String getMethod() {
		return method;
	}
	
}
