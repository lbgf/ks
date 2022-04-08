/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast;

import org.ks.runtime.Environment;
import org.ks.runtime.KsEnvironment;

/**
 * ��չ����.
 *
 */
public class KsFunction extends Function {
	protected int size;

	public KsFunction(ParameterList parameters, BlockStatement body, Environment env, int memorySize) {
		super(parameters, body, env);
		size = memorySize;
	}
	
	public KsFunction(ParameterList parameters, BlockStatement body, Environment env, int memorySize, String name) {
		super(parameters, body, env, name);
		size = memorySize;
	}

	@Override
	public Environment makeEnv() {
		KsEnvironment ke = new KsEnvironment(size, env);
		ke.setMethodEnvironment(true);
		return ke;
	}
}
