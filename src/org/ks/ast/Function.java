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

/**
 * ����.
 *
 */
public class Function {
	protected ParameterList parameters;
	protected BlockStatement body;
	protected Environment env;
	protected String name;

	public Function(ParameterList parameters, BlockStatement body, Environment env) {
		this.parameters = parameters;
		this.body = body;
		this.env = env;
	}
	
	public Function(ParameterList parameters, BlockStatement body, Environment env, String name) {
		this.parameters = parameters;
		this.body = body;
		this.env = env;
		this.name = name;
	}

	public ParameterList parameters() {
		return parameters;
	}

	public BlockStatement body() {
		return body;
	}

	public Environment makeEnv() {
		return env;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "<function:" + hashCode() + ">";
	}
}
