/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.runtime;

/**
 * 类成员标记.
 *
 */
public class MemberSymbols extends Symbols {
	public static int METHOD = -1;
	public static int FIELD = -2;
	protected int type;

	public MemberSymbols(Symbols outer, int type) {
		super(outer);
		this.type = type;
	}

	@Override
	public Location get(String key, int nest) {
		Integer index = varMap.get(key);
		if (index == null) {
			if (outer == null) {
				return null;
			} else {
				return outer.get(key, nest);
			}
		} else {
			return new Location(type, index.intValue());
		}
	}

	@Override
	public Location put(String key) {
		Location loc = get(key, 0);
		if (loc == null) {
			return new Location(type, add(key));
		} else {
			return loc;
		}
	}
	
	public Location getField(String key, int nest) {
		Integer index = varMap.get(key);
		if (index == null) {
			if (outer == null) {
				return null;
			} else {
				return null;
				// return outer.get(key, nest);
			}
		} else {
			return new Location(type, index.intValue());
		}
	}
	
	public Location putField(String key) {
		Location loc = getField(key, 0);
		if (loc == null) {
			return new Location(type, add(key));
		} else {
			return loc;
		}
	}
}
