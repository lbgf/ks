/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
