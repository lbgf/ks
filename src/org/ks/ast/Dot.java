/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
 * ���"."һ�㣻
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
	
	// to do:��������ʱ�����
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
    		|| env.getClass(value.toString().split("[$]")[value.toString().split("[$]").length - 1]) != null ) { // java��
    	try {
    		// ���Ի�̬����
  			Class<?> c = Class.forName(value.toString());
  			try {
  				if (member.equals("class")) {
  					return Class.forName(c.getName());
  				}
  				return c.getField(member).get(c); // ���ԣ�Ϊ��ʱ�����һ���������Ծͻ�����쳣���أ�
  			} catch(Exception e) {
  				// e.printStackTrace();
  				return new JavaKsStaticClass(c, member); // ��̬����������ʱ
  			}
    	} catch(Exception e) {
    		// e.printStackTrace();
    		throw new KsException(e.getMessage(), this);
    	}
    } else if (value instanceof Object) { // java����
    	try {
    		Class<?> c = value.getClass();
    		try {
    			if (member.equals("class")) {
  					return Class.forName(c.getName());
  				}
    			return c.getField(member).get(value); // ����
  			} catch(Exception e) {
  				// e.printStackTrace();
  				return new JavaKsObject(value, member);
  			}
	    	
    	} catch(Exception e) {
    		// e.printStackTrace();
    		throw new KsException(e.getMessage(), this);
    	}
    }

    throw new KsException("���ǳ�Ա����: " + member, this);
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
    throw new KsException("���ǳ�Ա����: " + member, this);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp, Object value) {
		// System.out.println("Dot");  // test
		
		String member = name();
		// System.out.println("value:" + value.getClass()); // test
		
		if (value instanceof VarType) { 
			
			VarType type = (VarType)value;
			
			if(type.isJavaObject()) { // java����
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
			} else if(type.isClassLib()) { // ��⣨java��̬�ࣩ
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
					// ��������ʱ��������
					return new JavaKsStaticClass(type.getJavaClass(), member);
				}
			} else if(type.isExternal()) { // �ⲿ����
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
			} else if(type.isJavaStaticClass()) { // ��java��̬���ԣ�
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
			} else if (type.isThis()) { // thisָ��
				
				String className = "Script" + env.getSubScriptName().replaceAll("[.]", "/");
				bcOp.getField(className, member, "Ljava/lang/Object;");				
				return new JavaKsObject(Object.class, member);
				
			} else {
				System.out.println("��ʱ����ʶ�������");
				return null;
			}
			
		} else if (value instanceof JavaKsStaticClass) { // ��һ���Ǿ�̬�࣬���ڷ�������
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
				// ��������ʱ��������
				/*try {
					return new JavaKsObject(jkc.getJavaClass(), member);
				} catch(Exception ee) {
					// ee.printStackTrace();
					throw new KsException(ee.getMessage(), this);
				}*/
				return new JavaKsObject(jkc.getJavaClass(), member);
			}
		} else if (value instanceof JavaKsObject) { // ��һ���������࣬������������
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
			throw new KsException("����ʶ�������", this);
		}
		
	}

}
