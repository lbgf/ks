/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import org.ks.ast.en.ClassStatement;
import org.ks.core.KsException;
import org.ks.runtime.Environment;

/**
 * 类信息.
 *
 */
public class ClassInfo {
	protected ClassStatement definition;
	protected Environment env;
	protected ClassInfo superClass;

	public ClassInfo(ClassStatement cs, Environment env) {
		definition = cs;
		this.env = env;
		Object obj = env.get(cs.superClass());
		if (obj == null) {
			superClass = null;
		} else if (obj instanceof ClassInfo) {
			superClass = (ClassInfo) obj;
		} else {
			throw new KsException("没有找到父类: " + cs.superClass(), cs);
		}
	}

	public String name() {
		return definition.name();
	}

	public ClassInfo superClass() {
		return superClass;
	}

	public ClassBody body() {
		return definition.body();
	}

	public Environment environment() {
		return env;
	}

	@Override
	public String toString() {
		return "<class " + name() + ">";
	}
}
