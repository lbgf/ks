/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.comb;

import java.util.HashMap;

/**
 * Operators.
 *
 */
public class Operators extends HashMap<String, Precedence> {
	public static boolean LEFT = true;
	public static boolean RIGHT = false;

	/**
	 * ��Ӳ�����.
	 * 
	 * @param name String: ����.
	 * @param prec int: ���ȼ�.
	 * @param leftAssoc boolean: trueΪ�������,falseΪ�ұ�����.
	 */
	public void add(String name, int prec, boolean leftAssoc) {
		put(name, new Precedence(prec, leftAssoc));
	}
}
