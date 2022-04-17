/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.bc;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于处理Stack Map Frames特性
 *
 */
public class StackMapFrames {
	
	private int oldSize = 0;
	private int newSize = 0;
	protected List<Object> frameObjs;

	public StackMapFrames() {
		oldSize = 0;
		newSize = 0;
		frameObjs = new ArrayList<Object>();
	}
	
	public void syncSize() {
		oldSize = newSize;
	}
	
	public int getOldSize() {
		return oldSize;
	}
	
	public void putOldSize(int oldSize) {
		this.oldSize = oldSize;
	}

	public int getNewSize() {
		return newSize;
	}
	
	public void putNewSize(int newSize) {
		this.newSize = newSize;
	}
	
	public void resetOldSize() {
		oldSize = 0;
	}
	
	public List<Object> getFrameObjs() {
		return frameObjs;
	}
	
	public void putFrameObjs(List<Object> objList) {
		for (int i = 0; i < objList.size(); i++) {
			this.frameObjs.add(objList.get(i));
		}
		newSize = frameObjs.size();
		oldSize = newSize;
	}
	
	public void putFrameObj(Object value) {
		frameObjs.add(value);
		newSize = frameObjs.size();
	}
	
	public void clearFrameObjs() {
		frameObjs.clear();
		newSize = 0;
		oldSize = newSize;
	}

}
