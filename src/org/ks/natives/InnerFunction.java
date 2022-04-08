/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.natives;

/**
 * ��չ����.
 *
 */
public class InnerFunction {

	public static void print(Object obj) {
		System.out.println(obj);
	}

	public static int length(String s) {
		return s.length();
	}

	public static Object toObject(Object value) {
		return Object.class.cast(value);
	}

	public static long currentTime() {
		return System.currentTimeMillis();
	}

	/**
	 * �ȴ�.
	 * 
	 * @param time
	 * @return
	 */
	public static int delay(int time) {
		try {
			Thread.currentThread().sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
