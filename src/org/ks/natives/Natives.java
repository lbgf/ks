/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.natives;

import java.lang.reflect.Method;

import org.ks.core.KsException;
import org.ks.runtime.Environment;

/**
 * ԭ����������.
 *
 */
public abstract class Natives {
	
	public Environment environment(Environment env) {
		appendNatives(env);
		return env;
	}
	
	public abstract void appendNatives(Environment env);

	public void append(Environment env, String name, Class<?> clazz, String methodName, Class<?>[] inParams) {
		Method m;
		try {
			m = clazz.getMethod(methodName, inParams);
		} catch (Exception e) {
			throw new KsException("�Ҳ���ԭ������: " + methodName);
		}
		env.put(name, new NativeFunction(methodName, m));
	}

}
