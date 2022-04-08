/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.runtime;

import org.ks.core.KsException;

/**
 * this标记.
 *
 */
public class SymbolThis extends Symbols {
	public static final String NAME = "this";

	public SymbolThis(Symbols outer) {
		super(outer);
		add(NAME);
	}

	@Override
	public int putNew(String key) {
		throw new KsException("没有实现的方法");
	}

	@Override
	public Location put(String key) {
		Location loc = outer.put(key);
		if (loc.nest >= 0) {
			loc.nest++;
		}
		return loc;
	}
	
	public Location putField(String key) {
		if(outer instanceof MemberSymbols) {
			Location loc = ((MemberSymbols)outer).putField(key);
			if (loc.nest >= 0) {
				loc.nest++;
			}
			return loc;
		} else {
			/*Location loc = outer.put(key);
			if (loc.nest >= 0) {
				loc.nest++;
			}
			return loc;*/
			return null;
		}
	}

}
