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
 * ���java�õ�ks��(��̬).
 *
 */
public class JavaKsStaticClass {

	protected Class<?> clazz;
	protected String method;

	public JavaKsStaticClass(Class<?> clazz, String method) {
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
