/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.natives;

import java.lang.reflect.Method;

import org.ks.core.KsException;
import org.ks.runtime.Environment;

/**
 * 原生方法基类.
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
			throw new KsException("找不到原生方法: " + methodName);
		}
		env.put(name, new NativeFunction(methodName, m));
	}

}
