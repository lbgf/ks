/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.natives;

import java.util.List;

import org.ks.plugin.language.LanguagePkg;
import org.ks.runtime.Environment;

/**
 * 内置方法加载.
 *
 */
public class KsNatives extends Natives {
	protected List<LanguagePkg> lps;

	public KsNatives(List<LanguagePkg> lps) {
		this.lps = lps;
	}

	public void appendNatives(Environment env) {
		lps.stream().forEach(e -> {
			append(env, e.l_m_print, org.ks.natives.InnerFunction.class, "print", new Class[] {Object.class});
			append(env, e.l_m_delay, org.ks.natives.InnerFunction.class, "delay", new Class[] {int.class});
		});
		
		append(env, "length", org.ks.natives.InnerFunction.class, "length", new Class[] {String.class});
		append(env, "currentTime", org.ks.natives.InnerFunction.class, "currentTime", null);
		
	}
	
}
