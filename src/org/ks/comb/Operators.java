/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
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
	 * 添加操作符.
	 * 
	 * @param name String: 名称.
	 * @param prec int: 优先级.
	 * @param leftAssoc boolean: true为左边优先,false为右边优先.
	 */
	public void add(String name, int prec, boolean leftAssoc) {
		put(name, new Precedence(prec, leftAssoc));
	}
}
