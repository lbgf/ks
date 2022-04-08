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
 * 针对java用的ks类(静态).
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
