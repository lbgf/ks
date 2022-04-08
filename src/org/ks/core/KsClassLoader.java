/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.core;

/**
 * 类加载器.
 *
 */
public final class KsClassLoader extends ClassLoader {
	
	public Class<?> createClass(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
		return super.defineClass(paramString, paramArrayOfByte, paramInt1, paramInt2);
	}

}
