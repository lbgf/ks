/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.comb;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;

/**
 * Factory.
 *
 */
public abstract class Factory {
	
	protected abstract ASTNode make0(Object arg) throws Exception;

	public ASTNode make(Object arg) {
		try {
			return make0(arg);
		} catch (IllegalArgumentException e1) {
			throw e1;
		} catch (Exception e2) {
			throw new RuntimeException(e2); 
		}
	}

	public static Factory getForASTList(Class<? extends ASTNode> clazz) {
		Factory f = get(clazz, List.class);
		if (f == null)
			f = new Factory() {
				protected ASTNode make0(Object arg) throws Exception {
					List<ASTNode> results = (List<ASTNode>) arg;
					if (results.size() == 1) {
						return results.get(0);
					} else {
						return new ASTList(results);
					}
				}
			};
		return f;
	}

	public static Factory get(Class<? extends ASTNode> clazz, Class<?> argType) {
		if (clazz == null)
			return null;
		try {
			final Method m = clazz.getMethod("create", new Class<?>[] { argType });
			return new Factory() {
				protected ASTNode make0(Object arg) throws Exception {
					return (ASTNode) m.invoke(null, arg);
				}
			};
		} catch (NoSuchMethodException e) {
		}
		try {
			final Constructor<? extends ASTNode> c = clazz.getConstructor(argType);
			return new Factory() {
				protected ASTNode make0(Object arg) throws Exception {
					return c.newInstance(arg);
				}
			};
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
}
