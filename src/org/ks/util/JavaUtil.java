/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.util;

/**
 * jvm辅助类.
 *
 */
public class JavaUtil {

	public static Class<?> getParamType(Object type) {
		Class<?> obj = null;
		if (type instanceof Integer) {
			obj = int.class;
		} else if (type instanceof Long) {
			obj = long.class;
		} else if (type instanceof Double) {
			obj = double.class;
		} else if (type instanceof Float) {
			obj = float.class;
		} else if (type instanceof Boolean) {
			obj = boolean.class;
		} else if (type instanceof Short) {
			obj = short.class;
		} else if (type instanceof Character) {
			obj = char.class;
		} else if (type instanceof Byte) {
			obj = byte.class;
		} else if (type instanceof Void) {
			obj = void.class;
		} else {
			obj = type.getClass();
		} 
		return obj;

	}
	
	public static boolean isWrapperType(Object type) {
		if (type instanceof Integer || type instanceof Long
				|| type instanceof Double || type instanceof Float
				|| type instanceof Boolean || type instanceof Byte
				|| type instanceof Short || type instanceof Character
				|| type instanceof Void) {
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean checkClassExist(String className) {
		try {
			Class.forName(className, false, JavaUtil.class.getClassLoader());
			return true;
		} catch(ClassNotFoundException e) {
			return false;
		}
	}
	
	public static String getRealClassName(String name) {
		if (checkClassExist(name)) {
			return name;
		} else {
			String[] names = name.split("[.]");
			StringBuffer sb = new StringBuffer(name);
			
			for (int i = 0; i < names.length-1; i++) {
				try {
					sb.replace(sb.toString().lastIndexOf("."), sb.toString().lastIndexOf(".")+1, "$");
					Class.forName(sb.toString(), false, JavaUtil.class.getClassLoader());
					return sb.toString();
				} catch(ClassNotFoundException e) {
					// e.printStackTrace();
				}
			}
		}
		return null;
	}

}
