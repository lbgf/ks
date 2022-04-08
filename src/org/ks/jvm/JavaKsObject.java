/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.jvm;

/**
 * 针对java用的ks对象.
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
