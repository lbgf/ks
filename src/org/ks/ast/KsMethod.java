/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import org.ks.runtime.Environment;
import org.ks.runtime.KsEnvironment;

/**
 * 类成员函数.
 *
 */
public class KsMethod extends KsFunction {
	KsObject self;

	public KsMethod(ParameterList parameters, BlockStatement body, Environment env, int memorySize, KsObject self) {
		super(parameters, body, env, memorySize);
		this.self = self;
	}

	@Override
	public Environment makeEnv() {
		KsEnvironment e = new KsEnvironment(size, env);
		e.put(0, 0, self);
		return e;
	}
}
