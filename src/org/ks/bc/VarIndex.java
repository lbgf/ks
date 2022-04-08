/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.bc;

/**
 * 用于变量索引号
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
