/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.core;

/**
 * �������.
 *
 */
public final class KsClassLoader extends ClassLoader {
	
	public Class<?> createClass(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
		return super.defineClass(paramString, paramArrayOfByte, paramInt1, paramInt2);
	}

}
