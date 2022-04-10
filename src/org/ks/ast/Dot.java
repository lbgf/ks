/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.jvm.JavaKsObject;
import org.ks.jvm.JavaKsStaticClass;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;

/**
 * 表达"."一点；
 *
 */
public class Dot extends Postfix {
	
	protected KsClassInfo classInfo = null;
	protected boolean isField;
	protected int index;

	public Dot(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public String toString() {
		return "." + name();
	}

	protected void initObject(ClassInfo ci, Environment env) {
		if (ci.superClass() != null) {
			initObject(ci.superClass(), env);
		}
		ci.body().eval(env);
	}
	
	// to do:处理属性时的情况
	public Object eval(Environment env, Object value) {
    String member = name();
    if (value instanceof KsObject) {
    	KsObject target = (KsObject)value;
        if (target.classInfo() != classInfo) {
          updateCache(target);
        }
        if (isField) {
          return target.read(index);
        } else {
          return target.method(index);
        }
    } else if (env.getClass(value.toString().split("[.]")[value.toString().split("[.]").length - 1]) != null
    		|| env.getClass(value.toString().split("[$]")[value.toString().split("[$]").length - 1]) != null ) { // java类
    	try {
    		// 属性或静态方法
  			Class<?> c = Class.forName(value.toString());
  			try {
  				if (member.equals("class")) {
  					return Class.forName(c.getName());
  				}
  				return c.getField(member).get(c); // 属性（为空时如果下一个还是属性就会出现异常返回）
  			} catch(Exception e) {
  				// e.printStackTrace();
  				return new JavaKsStaticClass(c, member); // 静态方法被调用时
  			}
    	} catch(Exception e) {
    		// e.printStackTrace();
    		throw new KsException(e.getMessage(), this);
    	}
    } else if (value instanceof Object) { // java对象
    	try {
    		Class<?> c = value.getClass();
    		try {
    			if (member.equals("class")) {
  					return Class.forName(c.getName());
  				}
    			return c.getField(member).get(value); // 属性
  			} catch(Exception e) {
  				// e.printStackTrace();
  				return new JavaKsObject(value, member);
  			}
	    	
    	} catch(Exception e) {
    		// e.printStackTrace();
    		throw new KsException(e.getMessage(), this);
    	}
    }

    throw new KsException("不是成员函数: " + member, this);
	}
	
	protected void updateCache(KsObject target) {
    String member = name();
    classInfo = target.classInfo();
    Integer i = classInfo.fieldIndex(member);
    if (i != null) {
      isField = true;
      index = i;
      return;
    }
    i = classInfo.methodIndex(member);
    if (i != null) {
      isField = false;
      index = i;
      return;
    }
    throw new KsException("不是成员函数: " + member, this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp, Object value) {
		// System.out.println("Dot");  // test
		
		String member = name();
		// System.out.println("value:" + value.getClass()); // test
		
		if (value instanceof VarType) { 
			
			VarType type = (VarType)value;
			
			if(type.isJavaObject()) { // java对象
				Class<?> c = type.getJavaClass();
				try {
					String className = c.getName().replaceAll("[.]", "/");
					Field f = c.getField(member);
					String fieldClassName =  f.getGenericType().getTypeName().replaceAll("[.]", "/");
					if(Modifier.isStatic(f.getModifiers())) {
						bcOp.getStaticField(className, member, "L"+fieldClassName+";");
					} else {
						bcOp.getField(className, member, "L"+fieldClassName+";");
					}
					return new JavaKsObject(Class.forName(c.getField(member).getGenericType().getTypeName()), member);
				} catch(Exception e) {
					// throw new KsException(e.getMessage(), this);
					return new JavaKsObject(type.getJavaClass(), member);
				}
			} else if(type.isClassLib()) { // 类库（java静态类）
				Class<?> c = type.getJavaClass();
				try {
					String className = c.getName().replaceAll("[.]", "/");
					Field f = c.getField(member);
					String fieldClassName =  f.getGenericType().getTypeName().replaceAll("[.]", "/");
					if(Modifier.isStatic(f.getModifiers())) {
						bcOp.getStaticField(className, member, "L"+fieldClassName+";");
						return new JavaKsStaticClass(Class.forName(c.getField(member).getGenericType().getTypeName()), member);
					} else {
						bcOp.getField(className, member, "L"+fieldClassName+";");
						return new JavaKsObject(Class.forName(c.getField(member).getGenericType().getTypeName()), member);
					}
					
				} catch(Exception e) {
					// throw new KsException(e.getMessage(), this);
					// 不是属性时进入这里
					return new JavaKsStaticClass(type.getJavaClass(), member);
				}
			} else if(type.isExternal()) { // 外部变量
				Class<?> c = type.getJavaClass();
				try {
					String className = c.getName().replaceAll("[.]", "/");
					Field f = c.getField(member);
					String fieldClassName =  f.getGenericType().getTypeName().replaceAll("[.]", "/");
					if(Modifier.isStatic(f.getModifiers())) {
						bcOp.getStaticField(className, member, "L"+fieldClassName+";");
					} else {
						bcOp.getField(className, member, "L"+fieldClassName+";");
					}
					return new JavaKsObject(Class.forName(c.getField(member).getGenericType().getTypeName()), member);
				} catch(Exception e) {
					// throw new KsException(e.getMessage(), this);
					return new JavaKsObject(type.getJavaClass(), member);
				}
			} else if(type.isJavaStaticClass()) { // （java静态属性）
				Class<?> c = type.getJavaClass();
				try {
					String className = c.getName().replaceAll("[.]", "/");
					Field f = c.getField(member);
					String fieldClassName =  f.getGenericType().getTypeName().replaceAll("[.]", "/");
					if(Modifier.isStatic(f.getModifiers())) {
						bcOp.getStaticField(className, member, "L"+fieldClassName+";");
					} else {
						bcOp.getField(className, member, "L"+fieldClassName+";");
					}
					return new JavaKsObject(Class.forName(c.getField(member).getGenericType().getTypeName()), member);
				} catch(Exception e) {
					// throw new KsException(e.getMessage(), this);
					return new JavaKsObject(type.getJavaClass(), member);
				}
			} else if (type.isThis()) { // this指针
				
				String className = "Script" + env.getSubScriptName().replaceAll("[.]", "/");
				bcOp.getField(className, member, "Ljava/lang/Object;");				
				return new JavaKsObject(Object.class, member);
				
			} else {
				System.out.println("暂时不能识别的类型");
				return null;
			}
			
		} else if (value instanceof JavaKsStaticClass) { // 上一级是静态类，现在访问属性
			JavaKsStaticClass jkc = (JavaKsStaticClass)value;
			Class<?> c = jkc.getJavaClass();
			try {
				String className = c.getName().replaceAll("[.]", "/");
				Field f = c.getField(member);
				String fieldClassName =  f.getGenericType().getTypeName().replaceAll("[.]", "/");
				if(Modifier.isStatic(f.getModifiers())) {
					bcOp.getStaticField(className, member, "L"+fieldClassName+";");
				} else {
					bcOp.getField(className, member, "L"+fieldClassName+";");
				}
				
				return new JavaKsObject(Class.forName(c.getField(jkc.getMethod()).getGenericType().getTypeName()), member);
			} catch(Exception e) {
				// e.printStackTrace();
				// 不是属性时进入这里
				/*try {
					return new JavaKsObject(jkc.getJavaClass(), member);
				} catch(Exception ee) {
					// ee.printStackTrace();
					throw new KsException(ee.getMessage(), this);
				}*/
				return new JavaKsObject(jkc.getJavaClass(), member);
			}
		} else if (value instanceof JavaKsObject) { // 上一级是属性类，继续访问属性
			JavaKsObject jko = (JavaKsObject)value;
			Class<?> c = jko.getJavaClass();
			try {
				
				String className = c.getName().replaceAll("[.]", "/");
				Field f = c.getField(member);
				String fieldClassName =  f.getGenericType().getTypeName().replaceAll("[.]", "/");
				if(Modifier.isStatic(f.getModifiers())) {
					bcOp.getStaticField(className, member, "L"+fieldClassName+";");
				} else {
					bcOp.getField(className, member, "L"+fieldClassName+";");
				}
				return new JavaKsObject(Class.forName(c.getField(jko.getMethod()).getGenericType().getTypeName()), member);
			} catch(Exception e) {
				// throw new KsException(e.getMessage(), this);
				/*try {
					return new JavaKsObject(Class.forName(c.getField(jko.getMethod()).getGenericType().getTypeName()), member);
				} catch(Exception ee) {
					throw new KsException(ee.getMessage(), this);
				}*/
				return new JavaKsObject(jko.getJavaClass(), member);
			}
		} else {
			throw new KsException("不能识别的类型", this);
		}
		
	}

}
