/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.bc;

import java.util.LinkedHashMap;
import java.util.Map;

import org.ks.ast.ASTNode;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * 字节码生成器.
 *
 */
public class BcGenerator  {
 
	private static final Map<String, String> ARITHMETIC_TYPE_MAP  = new LinkedHashMap<String, String>(); 
	
	static {
		
		//
		ARITHMETIC_TYPE_MAP.put("II", "I");
		ARITHMETIC_TYPE_MAP.put("IJ", "J");
		ARITHMETIC_TYPE_MAP.put("IF", "F");
		ARITHMETIC_TYPE_MAP.put("ID", "D");
		ARITHMETIC_TYPE_MAP.put("IZ", "I");
		ARITHMETIC_TYPE_MAP.put("JI", "J");
		ARITHMETIC_TYPE_MAP.put("JJ", "J");
		ARITHMETIC_TYPE_MAP.put("JF", "F");
		ARITHMETIC_TYPE_MAP.put("JD", "D");
		ARITHMETIC_TYPE_MAP.put("JZ", "J");
		ARITHMETIC_TYPE_MAP.put("FI", "F");
		ARITHMETIC_TYPE_MAP.put("FJ", "F");
		ARITHMETIC_TYPE_MAP.put("FF", "F");
		ARITHMETIC_TYPE_MAP.put("FD", "D");
		ARITHMETIC_TYPE_MAP.put("FZ", "F");
		ARITHMETIC_TYPE_MAP.put("DI", "D");
		ARITHMETIC_TYPE_MAP.put("DJ", "D");
		ARITHMETIC_TYPE_MAP.put("DF", "D");
		ARITHMETIC_TYPE_MAP.put("DD", "D");
		ARITHMETIC_TYPE_MAP.put("DZ", "D");
		ARITHMETIC_TYPE_MAP.put("ZI", "I");
		ARITHMETIC_TYPE_MAP.put("ZJ", "J");
		ARITHMETIC_TYPE_MAP.put("ZF", "F");
		ARITHMETIC_TYPE_MAP.put("ZD", "D");
		ARITHMETIC_TYPE_MAP.put("ZZ", "Z");
		// 
	}
	
	public static String getArithmeticType(String obj) {
    return ARITHMETIC_TYPE_MAP.get(obj);
	}
	
	public static VarType plus(ASTNode n1, ASTNode n2, Environment env, BcOpcodes bcOp) {
		
		String n1Type = "Ljava/lang/Object;";
		String n2Type = "Ljava/lang/Object;";
		String rType = "Ljava/lang/Object;";

		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/core/ArithmeticUnit;");
		
		Object obj = n1.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) { // 不是值类型并且是包装型才处理
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n1Type = getBcType(newType);
			}
		}

		obj = n2.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) {
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n2Type = getBcType(newType);
			}
		}
		
		rType = getArithmeticType(n1Type+n2Type);
		if (rType == null) {
			rType = "Ljava/lang/Object;";
		}
		bcOp.invokeVirtual("org/ks/core/ArithmeticUnit", "plus", n1Type+n2Type, rType, false);
		return getReturnType(rType); // 需要改良
	}
	
	public static VarType minus(ASTNode n1, ASTNode n2, Environment env, BcOpcodes bcOp) {
		
		String n1Type = "Ljava/lang/Object;";
		String n2Type = "Ljava/lang/Object;";
		String rType = "Ljava/lang/Object;";

		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/core/ArithmeticUnit;");
		
		Object obj = n1.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) { // 不是值类型并且是包装型才处理
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n1Type = getBcType(newType);
			}
		}

		obj = n2.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) {
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n2Type = getBcType(newType);
			}
		}
		
		if (n1Type == null || n2Type == null) {
			throw new KsException("运算中没有对应的类型");
		}
		
		rType = getArithmeticType(n1Type+n2Type);
		if (rType == null) {
			rType = "Ljava/lang/Object;";
		}
		
		bcOp.invokeVirtual("org/ks/core/ArithmeticUnit", "minus", n1Type+n2Type, rType, false);
		return getReturnType(rType); // 需要改良
		
	}

	public static VarType multiply(ASTNode n1, ASTNode n2, Environment env, BcOpcodes bcOp) {
		
		String n1Type = "Ljava/lang/Object;";
		String n2Type = "Ljava/lang/Object;";
		String rType = "Ljava/lang/Object;";

		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/core/ArithmeticUnit;");
		
		Object obj = n1.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) { // 不是值类型并且是包装型才处理
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n1Type = getBcType(newType);
			}
		}

		obj = n2.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) {
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n2Type = getBcType(newType);
			}
		}
		
		if (n1Type == null || n2Type == null) {
			throw new KsException("运算中没有对应的类型");
		}
		
		rType = getArithmeticType(n1Type+n2Type);
		if (rType == null) {
			rType = "Ljava/lang/Object;";
		}
		
		bcOp.invokeVirtual("org/ks/core/ArithmeticUnit", "multiply", n1Type+n2Type, rType, false);
		return getReturnType(rType); // 需要改良
		
	}
	
	public static VarType divide(ASTNode n1, ASTNode n2, Environment env, BcOpcodes bcOp) {
		
		String n1Type = "Ljava/lang/Object;";
		String n2Type = "Ljava/lang/Object;";
		String rType = "Ljava/lang/Object;";

		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/core/ArithmeticUnit;");
		
		Object obj = n1.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) { // 不是值类型并且是包装型才处理
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n1Type = getBcType(newType);
			}
		}

		obj = n2.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) {
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n2Type = getBcType(newType);
			}
		}
		
		if (n1Type == null || n2Type == null) {
			throw new KsException("运算中没有对应的类型");
		}
		
		rType = getArithmeticType(n1Type+n2Type);
		if (rType == null) {
			rType = "Ljava/lang/Object;";
		}
		
		bcOp.invokeVirtual("org/ks/core/ArithmeticUnit", "divide", n1Type+n2Type, rType, false);
		return getReturnType(rType); // 需要改良
	}
	
	public static VarType mod(ASTNode n1, ASTNode n2, Environment env, BcOpcodes bcOp) {
		
		String n1Type = "Ljava/lang/Object;";
		String n2Type = "Ljava/lang/Object;";
		String rType = "Ljava/lang/Object;";

		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/core/ArithmeticUnit;");
		
		Object obj = n1.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) { // 不是值类型并且是包装型才处理
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n1Type = getBcType(newType);
			}
		}

		obj = n2.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) {
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n2Type = getBcType(newType);
			}
		}
		
		if (n1Type == null || n2Type == null) {
			throw new KsException("运算中没有对应的类型");
		}
		
		rType = getArithmeticType(n1Type+n2Type);
		if (rType == null) {
			rType = "Ljava/lang/Object;";
		}
		
		bcOp.invokeVirtual("org/ks/core/ArithmeticUnit", "mod", n1Type+n2Type, rType, false);
		return getReturnType(rType); // 需要改良
	}
	
	public static VarType compare(ASTNode n1, ASTNode n2, String op, Environment env, BcOpcodes bcOp) {
		
		String n1Type = "Ljava/lang/Object;";
		String n2Type = "Ljava/lang/Object;";
		
		bcOp.getThis();
		bcOp.getField("org/ks/bc/ScriptBase", "au", "Lorg/ks/core/ArithmeticUnit;");
		
		Object obj = n1.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) { // 不是值类型并且是包装型才处理
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n1Type = getBcType(newType);
			}
		}

		obj = n2.compile(env, bcOp);
		if (obj != null && obj instanceof VarType) {
			VarType newType = new VarType(((VarType)obj).getJavaClass());
			if(!isValueType(newType) && isWrapperType(newType)) {
				newType = toValueType((VarType)obj, bcOp);
			}
			if (isValueType(newType)) {
				n2Type = getBcType(newType);
			}
		}
		
		bcOp.gcMethod().visitLdcInsn(op);
		bcOp.invokeVirtual("org/ks/core/ArithmeticUnit", "compare", n1Type+n2Type+"Ljava/lang/String;", "Z", false);
		return new VarType(boolean.class);
	}

	public static void pushConstant(BcOpcodes bcOp, int value) {
		switch (value) {
		case -1:
			bcOp.gcMethod().visitInsn(BcOpcodes.ICONST_M1);
			break;
		case 0:
			bcOp.gcMethod().visitInsn(BcOpcodes.ICONST_0);
			break;
		case 1:
			bcOp.gcMethod().visitInsn(BcOpcodes.ICONST_1);
			break;
		case 2:
			bcOp.gcMethod().visitInsn(BcOpcodes.ICONST_2);
			break;
		case 3:
			bcOp.gcMethod().visitInsn(BcOpcodes.ICONST_3);
			break;
		case 4:
			bcOp.gcMethod().visitInsn(BcOpcodes.ICONST_4);
			break;
		case 5:
			bcOp.gcMethod().visitInsn(BcOpcodes.ICONST_5);
			break;
		default:
			if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
				bcOp.gcMethod().visitIntInsn(BcOpcodes.BIPUSH, value);
			} else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
				bcOp.gcMethod().visitIntInsn(BcOpcodes.SIPUSH, value);
			} else {
				bcOp.gcMethod().visitLdcInsn(value);
			}
		}
	}
	
	public static String getBcType(VarType type) {
		if (type == null || type.getJavaClass() == null) {
			return "V";
		}

		if (type.getJavaClass().isAssignableFrom(int.class)) {
			return "I";
		} else if (type.getJavaClass().isAssignableFrom(long.class)) {
			return "J";
		} else if (type.getJavaClass().isAssignableFrom(boolean.class)) {
			return "Z";
		} else if (type.getJavaClass().isAssignableFrom(float.class)) {
			return "F";
		} else if (type.getJavaClass().isAssignableFrom(double.class)) {
			return "D";
		} else { // to do: 继续加
			return "L" + type.getJavaClass().getName().replaceAll("[.]", "/")+";";
		} 
	}
	
	public static int getOpsType(VarType type) {
		if (type == null) {
			throw new KsException("类型为空");
		}
		if (type.getJavaClass() == null) {
			return BcOpcodes.ASTORE;
		}

		if (type.getJavaClass().isAssignableFrom(int.class)) {
			return BcOpcodes.ISTORE;
		} else if (type.getJavaClass().isAssignableFrom(long.class)) {
			return BcOpcodes.LSTORE;
		} else if (type.getJavaClass().isAssignableFrom(boolean.class)) {
			return BcOpcodes.ISTORE;
		} else if (type.getJavaClass().isAssignableFrom(float.class)) {
			return BcOpcodes.FSTORE;
		} else if (type.getJavaClass().isAssignableFrom(double.class)) {
			return BcOpcodes.DSTORE;
		} else { // to do: 继续加
			return BcOpcodes.ASTORE;
		} 
	}
	
	public static boolean isWrapperType(VarType type) {
		if (type == null || type.getJavaClass() == null) {
			return false;
		}
		return type.getJavaClass().getName().equals(Integer.class.getName()) 
				|| type.getJavaClass().getName().equals(Long.class.getName())
				|| type.getJavaClass().getName().equals(Double.class.getName()) 
				|| type.getJavaClass().getName().equals(Float.class.getName()) 
				|| type.getJavaClass().getName().equals(Boolean.class.getName());
	}
	
	public static VarType toWrapperType(VarType type, BcOpcodes bcOp) {
		if (type == null || type.getJavaClass() == null) {
			throw new KsException("类型为空");
		}

		if (type.getJavaClass().isAssignableFrom(int.class)) {
			bcOp.invokeStatic("java/lang/Integer", "valueOf", "I", "Ljava/lang/Integer;", false);
			type.setJavaClass(Integer.class);
		} else if (type.getJavaClass().isAssignableFrom(long.class)) {
			bcOp.invokeStatic("java/lang/Long", "valueOf", "J", "Ljava/lang/Long;", false);
			type.setJavaClass(Long.class);
		} else if (type.getJavaClass().isAssignableFrom(boolean.class)) {
			bcOp.invokeStatic("java/lang/Boolean", "valueOf", "Z", "Ljava/lang/Boolean;", false);
			type.setJavaClass(Boolean.class);
		} else if (type.getJavaClass().isAssignableFrom(double.class)) {
			bcOp.invokeStatic("java/lang/Double", "valueOf", "D", "Ljava/lang/Double;", false);
			type.setJavaClass(Double.class);
		} else if (type.getJavaClass().isAssignableFrom(float.class)) {
			bcOp.invokeStatic("java/lang/Float", "valueOf", "F", "Ljava/lang/Float;", false);
			type.setJavaClass(Float.class);
		} else { // to do: 继续加
			// System.out.println(type.getJavaClass()); // test
			throw new KsException("暂时不支持的类型");
		} 
		return type;
	}
	
	public static boolean isValueType(VarType type) {
		if (type == null || type.getJavaClass() == null) {
			return false;
		}
		return type.getJavaClass().isAssignableFrom(int.class) || type.getJavaClass().isAssignableFrom(long.class)
				|| type.getJavaClass().isAssignableFrom(float.class)
				|| type.getJavaClass().isAssignableFrom(double.class) || type.getJavaClass().isAssignableFrom(boolean.class);
	}
	
	public static boolean isValueType(String type) {
		return type.equals("int") || type.equals("long") || type.equals("float")
				|| type.equals("double") || type.equals("boolean");
	}
	
	public static VarType toValueType(String type) {
		if (type == null) {
			throw new KsException("类型为空");
		}
		
		switch(type) {
			case "int": 
				return new VarType(int.class);
			case "long": 
				return new VarType(long.class);
			case "float": 
				return new VarType(float.class);
			case "double": 
				return new VarType(double.class);
			case "boolean": 
				return new VarType(boolean.class);
			// to do: 继续加
		}
		
		throw new KsException("没有适配的类型");
	}
	
	public static VarType toValueType(VarType type, BcOpcodes bcOp) {
		if (type == null || type.getJavaClass() == null) {
			throw new KsException("类型为空");
		}
		VarType newType = null;
		if (type.getJavaClass().isAssignableFrom(Integer.class)) {
			bcOp.invokeVirtual("java/lang/Integer", "intValue", null, "I", false);
			// type.setJavaClass(int.class);
			newType = new VarType(int.class);
		} else if (type.getJavaClass().isAssignableFrom(Long.class)) {
			bcOp.invokeVirtual("java/lang/Long", "longValue", null, "J", false);
			// type.setJavaClass(long.class);
			newType = new VarType(long.class);
		} else if (type.getJavaClass().isAssignableFrom(Boolean.class)) {
			bcOp.invokeVirtual("java/lang/Boolean", "booleanValue", null, "Z", false);
			//type.setJavaClass(boolean.class);
			newType = new VarType(boolean.class);
		} else if (type.getJavaClass().isAssignableFrom(Float.class)) {
			bcOp.invokeVirtual("java/lang/Float", "floatValue", null, "F", false);
			//type.setJavaClass(float.class);
			newType = new VarType(float.class);
		} else if (type.getJavaClass().isAssignableFrom(Double.class)) {
			bcOp.invokeVirtual("java/lang/Double", "doubleValue", null, "D", false);
			//type.setJavaClass(double.class);
			newType = new VarType(double.class);
		} else { // to do: 继续加
			// System.out.println(type.getJavaClass()); // test
			throw new KsException("暂时不支持的类型");
		} 
		return newType;
	}
	
	public static int getOplType(VarType type) {
		if (type == null) {
			throw new KsException("类型为空");
		}
		if (type.getJavaClass() == null) {
			return BcOpcodes.ALOAD;
		}
		if (type.getJavaClass().isAssignableFrom(int.class)) {
			return BcOpcodes.ILOAD;
		} else if (type.getJavaClass().isAssignableFrom(long.class)) {
			return BcOpcodes.LLOAD;
		} else if (type.getJavaClass().isAssignableFrom(boolean.class)) {
			return BcOpcodes.ILOAD;
		} else if (type.getJavaClass().isAssignableFrom(float.class)) {
			return BcOpcodes.FLOAD;
		} else if (type.getJavaClass().isAssignableFrom(double.class)) {
			return BcOpcodes.DLOAD;
		} else { // to do: 继续加
			return BcOpcodes.ALOAD;
		} 
	}
	
	public static VarType getReturnType(String type) {
		if (type == null) {
			throw new KsException("类型为空");
		}
		
		switch(type) {
			case "I": 
				return new VarType(int.class);
			case "J": 
				return new VarType(long.class);
			case "F": 
				return new VarType(float.class);
			case "D": 
				return new VarType(double.class);
			case "Z": 
				return new VarType(boolean.class);
			// to do: 继续加
			default:
				return new VarType(Object.class);
		}
	}
	
	public static VarType getReturnType2(String type) {
		if (type == null) {
			throw new KsException("类型为空");
		}
		
		switch(type) {
			case "I": 
				return new VarType(int.class);
			case "J": 
				return new VarType(long.class);
			case "F": 
				return new VarType(float.class);
			case "D": 
				return new VarType(double.class);
			case "Z": 
				return new VarType(boolean.class);
			case "V": 
				return new VarType(void.class);
			// to do: 继续加
			default:
				String typeNew = type.substring(1, type.length() - 1).replaceAll("/", ".");
				try {
					VarType varType = new VarType(Class.forName(typeNew));
					varType.setJavaObject(true);
					return varType;
  			} catch(Exception e) {
  				// e.printStackTrace();
  				throw new KsException(e.getMessage());
  			}
				// return new VarType(Object.class);
		}
	}
	
	public static int getOprType(VarType type) {
		if (type == null || type.getJavaClass() == null) {
			throw new KsException("类型为空");
		}

		if (type.getJavaClass().isAssignableFrom(int.class)) {
			return BcOpcodes.IRETURN;
		} else if (type.getJavaClass().isAssignableFrom(long.class)) {
			return BcOpcodes.LRETURN;
		} else if (type.getJavaClass().isAssignableFrom(boolean.class)) {
			return BcOpcodes.IRETURN;
		} else if (type.getJavaClass().isAssignableFrom(float.class)) {
			return BcOpcodes.FRETURN;
		} else if (type.getJavaClass().isAssignableFrom(double.class)) {
			return BcOpcodes.DRETURN;
		} else if (type.getJavaClass().isAssignableFrom(void.class)) {
			return BcOpcodes.RETURN;
		} else { // to do: 继续加
			return BcOpcodes.ARETURN;
		} 
	} 
	
	public static String getClassType(Class<?> c) {
		String type = null;
		if (c.isAssignableFrom(int.class)) {
			type = "I";
		} else if (c.isAssignableFrom(long.class)) {
			type = "J";
		} else if (c.isAssignableFrom(boolean.class)) {
			type = "Z";
		} else if (c.isAssignableFrom(float.class)) {
			type = "F";
		} else if (c.isAssignableFrom(double.class)) {
			type = "D";
		} else if (c.isAssignableFrom(void.class)) {
			type = "V";
		} else {
			type = "L" + c.getName().replaceAll("[.]", "/")+";";
		} // to do: 继续加
		return type;

	}

}
