/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.bc;

/**
 * ���ڱ���������
 *
 */
public class VarIndex {
	
	private int index;

	public VarIndex() {
		index = 1;
	}

	public int getIndex() {
		return index;
	}
	
	public int add(int cnt) {
		index = index + cnt;
		return index;
	}

}
