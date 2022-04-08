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
 * ���java�õ�ks����.
 *
 */
public class JavaKsObject {

	protected Object obj;
	protected String method;

	public JavaKsObject(Object obj, String method) {
		this.obj = obj;
		this.method = method;
	}
	
	public Object getObject() {
		return obj;
	}
	
	public String getMethod() {
		return method;
	}
	
	public Class<?> getJavaClass() {
		return obj.getClass();
	}
	
}
