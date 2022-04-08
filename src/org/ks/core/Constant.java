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
 * 常量.
 *
 */
public final class Constant {
	public static final byte DV = 0; // 默认
	public static final byte BC = 1; // 生成字节码,开发中...
	// public static final byte VM = 2; // 虚拟机，我想放弃^_^!,感觉用C去写更实际...
	
	public static final Object nullObject = null;

	public static Object getNullObject() {
		return nullObject;
	}
	
	public static boolean getTrue() {
		return Boolean.parseBoolean("true");
	}
	
	public static boolean getFalse() {
		return Boolean.parseBoolean("fase");
	}

}
