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
 * ����.
 *
 */
public final class Constant {
	public static final byte DV = 0; // Ĭ��
	public static final byte BC = 1; // �����ֽ���,������...
	// public static final byte VM = 2; // ��������������^_^!,�о���Cȥд��ʵ��...
	
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
