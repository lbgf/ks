/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.runtime;

import java.util.HashMap;

import org.ks.bc.VarIndex;

/**
 * 标记.
 *
 */
public class Symbols {

	protected Symbols outer;
	protected HashMap<String, Integer> varMap;
	
	protected HashMap<String, Integer> varIndexMap;
	protected VarIndex varIndex;

	public Symbols() {
		this(null);
	}

	public Symbols(Symbols outer) {
		this.outer = outer;
		this.varMap = new HashMap<String, Integer>();
	}
	
	public HashMap<String, Integer> getVarMap() {
		return varMap;
	}

	public int size() {
		return varMap.size();
	}

	public void append(Symbols s) {
		varMap.putAll(s.varMap);
	}

	public Integer find(String key) {
		return varMap.get(key);
	}

	public Location get(String key) {
		return get(key, 0);
	}

	public Location get(String key, int nest) {
		Integer index = varMap.get(key);
		if (index == null)
			if (outer == null)
				return null;
			else
				return outer.get(key, nest + 1);
		else
			return new Location(nest, index.intValue());
	}

	public int putNew(String key) {
		Integer i = find(key);
		if (i == null)
			return add(key);
		else
			return i;
	}

	public Location put(String key) {
		Location loc = get(key, 0);
		if (loc == null)
			return new Location(0, add(key));
		else
			return loc;
	}

	protected int add(String key) {
		// int i = table.size();
		// 为了生成字节码方便而特殊处理，因为Long这种类型要移动2位，如ldc2_w
		int i = varMap.size();//*2;
		varMap.put(key, i);
		
		//
		// addVarIndex(key, 2);
		//
		
		return i;
	}
	
	public void initVarIndex() {
		varIndex = new VarIndex();
		varIndexMap = new HashMap<String, Integer>();
	}
	
	public int addVarIndex(String key, int offsets) {
		if (varIndex == null && outer != null) {
			outer.addVarIndex(key, offsets);
		} else if (varIndex != null) {
			if(varIndexMap.containsKey(key)) {
				return getVarIndex(key);
			} else {
				varIndexMap.put(key, varIndex.getIndex());
				varIndex.add(offsets);
			}
		}
		return getVarIndex(key);
	}
	
	public void updateVarIndex(String key, int offsets) {
		if (varIndex == null && outer != null) {
			outer.updateVarIndex(key, offsets);
		} else if (varIndex != null) {
			varIndexMap.put(key, offsets);
		}
	}
	
	public int getVarIndex(String key) {
		if (varIndex == null && outer != null) {
			return outer.getVarIndex(key);
		} else if (varIndex != null) {
			if (varIndexMap.get(key) != null) {
				return varIndexMap.get(key).intValue();
			} else {
				if (outer != null) {
					return outer.getVarIndex(key);
				} else {
					return varIndex.getIndex();
				}
			}
		} else {
			return -1;
		}
	}
	
}
