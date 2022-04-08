/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.bc;

import org.ks.core.ArithmeticUnit;
import org.ks.runtime.Environment;

/**
 * Script基础类.
 *
 */
public abstract class ScriptBase {
	
	protected Environment env;
	
	protected ArithmeticUnit au = new ArithmeticUnit();
	
	public abstract Object run();
	
	public Object getVariable(String name) {
		return env.getBcValue(name);
	}
	
	public void setVariable(String name, Object value) {
		env.putBcValue(name, value);
	}
	
}
