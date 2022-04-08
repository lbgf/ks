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
 * 针对java用的ks类.
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
