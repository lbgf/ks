/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.comb;

/**
 * Precedence.
 *
 */
public class Precedence {
	public int value;
	public boolean leftAssoc;

	public Precedence(int v, boolean a) {
		this.value = v;
		this.leftAssoc = a;
	}
}
