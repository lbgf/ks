/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.runtime;

import org.ks.core.KsException;

/**
 * this���.
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
		throw new KsException("û��ʵ�ֵķ���");
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
