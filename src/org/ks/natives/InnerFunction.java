/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.natives;

/**
 * 扩展函数.
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
	
	public static String[] toStringArray(Object[] args) {
		String[] tmp = new String[args.length];
		for(int i = 0; i < args.length; i++) {
			tmp[i] = args[i].toString();
		}
		return tmp;
	}

	public static long currentTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 等待.
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
