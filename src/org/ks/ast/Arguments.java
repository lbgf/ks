/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.jvm.JavaKsClass;
import org.ks.jvm.JavaKsObject;
import org.ks.jvm.JavaKsStaticClass;
import org.ks.natives.NativeFunction;
import org.ks.runtime.Environment;
import org.ks.runtime.VarType;
import org.ks.util.JavaUtil;

/**
 * 参数类.
 *
 */
public class Arguments extends Postfix {
	
	public Arguments(List<ASTNode> c) {
		super(c);
	}

	public int size() {
		return numChildren();
	}

	public Object eval(Environment callerEnv, Object value) {
		// 引用java原生函数
		if (value instanceof NativeFunction) {
			NativeFunction func = (NativeFunction) value;
			int nparams = func.numOfParameters();
			Object[] args = new Object[nparams];
			
			int num = 0;
			if (size() == nparams) {
				num = 0;
			} else if ((size() + 1) == nparams) { // 多一个env
				num = 1;
				args[0] = callerEnv;
			} else {
				throw new KsException("函数参数的个数不对", this);
			}

			for (ASTNode a : this) {
				args[num++] = a.eval(callerEnv);
			}
			return func.invoke(args, this);
		} else if (value instanceof Function) {
			callerEnv.getReturnList().push(0);
			Function func = (Function) value;
			ParameterList params = func.parameters();
			if (size() != params.size())
				throw new KsException("函数参数的个数不对", this);
			Environment newEnv = func.makeEnv();
			int num = 0;
			for (ASTNode a : this) {
				params.eval(newEnv, num++, a.eval(callerEnv));
			}
			Object r = func.body().eval(newEnv);
			callerEnv.getReturnList().pop();
			return r;
		} else if (value instanceof JavaKsObject) { // 执行java函数
			JavaKsObject jko = (JavaKsObject)value;
			Class<?> realClass = null;
			Object realObj = null;
			// to do处理属性的情况
			/*if (jko.getObject() instanceof JavaKsClass) {
				System.out.println("OK1");
				realClass = ((JavaKsClass)jko.getObject()).getJavaClass();
				realObj = jko.getObject();
				System.out.println(realClass);
			} else {*/
				realClass = jko.getJavaClass();
				realObj = jko.getObject();
			//}

			// 初始化参数值
			Object[] valuesIn = new Object[numChildren()];
			int num = 0;
			for (ASTNode a : this) {
				valuesIn[num++] = a.eval(callerEnv);
			}
			// 输入参数类型设定
			Class<?> argIn[] = new Class[valuesIn.length];
					
			try {
				// 找出对应的函数
				List<Method> mList = new ArrayList<Method>();
				for (Method m: realClass.getMethods()) {
					if (jko.getMethod().equals(m.getName()) && m.getParameterCount() == argIn.length) {
						mList.add(m);
					}
				}
				for (Method m: mList) {
					int i = 0;
					for (Class<?> p: m.getParameterTypes()) {
						if (valuesIn[i] == null) {
							argIn[i] = Object.class;
						} else if (valuesIn[i].getClass().getName().equals(p.getName())) {
							argIn[i] = valuesIn[i].getClass();
						} else if (valuesIn[i].getClass().getSuperclass().getName().equals(p.getName())) {
							argIn[i] = valuesIn[i].getClass().getSuperclass();
						} else if (JavaUtil.getParamType(valuesIn[i]).getName().equals(p.getName())) {
							argIn[i] = JavaUtil.getParamType(valuesIn[i]);
						} 
						i++;
					}
					// 验证argIn
					if(checkArgInNotNull(argIn)) {
						break;
					}
				}
				
				Method method = realClass.getMethod(jko.getMethod(), argIn);
				method.setAccessible(true);	
				return method.invoke(realObj, valuesIn);
			} catch (Exception e) {
				// e.printStackTrace();
				throw new KsException(e, this);
			}
			
		} else if (value instanceof JavaKsStaticClass) { // 执行java的静态函数
			JavaKsStaticClass jkc = (JavaKsStaticClass)value;
			// 初始化参数值
			Object[] valuesIn = new Object[numChildren()];
			int num = 0;
			for (ASTNode a : this) {
				valuesIn[num++] = a.eval(callerEnv); // to do：处理返回带有.的情况（类库返回时）
			}
			// 输入参数类型设定
			Class<?> argIn[] = new Class[valuesIn.length];
			
			try {
				// 找出对应的函数
				List<Method> mList = new ArrayList<Method>();
				for (Method m: jkc.getJavaClass().getMethods()) {
					if (jkc.getMethod().equals(m.getName()) && m.getParameterCount() == argIn.length) {
						mList.add(m);
					}
				}
				for (Method m: mList) {
					int i = 0;
					for (Class<?> p: m.getParameterTypes()) {
						if (valuesIn[i] == null) {
							argIn[i] = Object.class;
						} else if (valuesIn[i].getClass().getName().equals(p.getName())) {
							argIn[i] = valuesIn[i].getClass();
						} else if (valuesIn[i].getClass().getSuperclass().getName().equals(p.getName())) {
							argIn[i] = valuesIn[i].getClass().getSuperclass();
						} else if (JavaUtil.getParamType(valuesIn[i]).getName().equals(p.getName())) {
							argIn[i] = JavaUtil.getParamType(valuesIn[i]);
						} 
						i++;
					}
					// 验证argIn
					if(checkArgInNotNull(argIn)) {
						break;
					}
				}
				
				Method method = method = jkc.getJavaClass().getMethod(jkc.getMethod(), argIn);
				method.setAccessible(true);	
				return method.invoke(null, valuesIn);
			} catch (Exception e) {
				// e.printStackTrace();
				throw new KsException(e, this);
			}
		} else if (value instanceof KsObject) { // 脚本类构造函数
			return value;
		} else if (value instanceof JavaKsClass) { // java构造函数
			
			/*if (numChildren() < 1) { // 没有参数的情况
				return value;
			}*/
			JavaKsClass jkc = (JavaKsClass)value;
			
			// 初始化参数值
			Object[] valuesIn = new Object[numChildren()];
			int num = 0;
			for (ASTNode a : this) {
				valuesIn[num++] = a.eval(callerEnv);
			}
			// 输入参数类型设定
			Class<?> argIn[] = new Class[valuesIn.length];
			Class<?> c = jkc.getJavaClass();//value.getClass();
			Object newValue = null;
			try {
				// 找出对应的构造函数
				List<Constructor<?>> mList = new ArrayList<Constructor<?>>();
				for (Constructor<?> m: c.getConstructors()) {
					if (m.getParameterCount() == argIn.length) {
						mList.add(m);
					}
				}
				
				for (Constructor<?> m: mList) {
					int i = 0;
					for (Class<?> p: m.getParameterTypes()) {
						if (valuesIn[i].getClass().getName().equals(p.getName())) {
							argIn[i] = valuesIn[i].getClass();
						} else if (valuesIn[i].getClass().getSuperclass().getName().equals(p.getName())) {
							argIn[i] = valuesIn[i].getClass().getSuperclass();
						} else if (JavaUtil.getParamType(valuesIn[i]).getName().equals(p.getName())) {
							argIn[i] = JavaUtil.getParamType(valuesIn[i]);
						} 
						i++;
					}
					// 验证argIn
					if(checkArgInNotNull(argIn)) {
						break;
					}
				}
				
				Constructor<?> constructor = c.getConstructor(argIn);
				newValue = constructor.newInstance(valuesIn);
				
				return newValue;
			} catch (Exception e) {
				// e.printStackTrace();
				throw new KsException(e, this);
			}
			
		} else if (value instanceof Object) {
			System.out.println("暂不支持的类型");
			return null;
		}
		
		throw new KsException("错误的函数", this);
		
	}
	
	public Object compile(Environment env, BcOpcodes bcOp, Object value) {
		// System.out.println("Arguments");  // test
		
		// System.out.println("value:" + value); // test
		
		if (value instanceof NativeFunction) {
			NativeFunction func = (NativeFunction) value;

			for (ASTNode a : this) {
				Object obj = a.compile(env, bcOp);
				if (obj instanceof VarType) { // to do 参数匹配
					if(BcGenerator.isValueType((VarType)obj)) {
						BcGenerator.toWrapperType((VarType)obj, bcOp);
					}
				}

			}
			
			return func.compile(env, bcOp);

		} else if (value instanceof Function) {
			
			Function func = (Function) value;
			
			ParameterList paramList = func.parameters();
			if (size() != paramList.size())
				throw new KsException("函数参数的个数不对", this);
			
			bcOp.getThis(); // this
			
			// 初始化参数值
			Object[] valuesIn = new Object[numChildren()];
			int num = 0;
			String params = "";
			for (ASTNode a : this) {
				Object obj = a.compile(env, bcOp);
				valuesIn[num++] = obj;
				if (obj == null) {
					throw new KsException("不能识别的参数类型", this);
				} else if (obj instanceof VarType) {
					if(BcGenerator.isValueType((VarType)obj)) {
						BcGenerator.toWrapperType((VarType)obj, bcOp);
					}
					params = params + "Ljava/lang/Object;";//BytecodeUtil.getBcType((VarType)obj);
				} else { // to do 有可能是个class
					throw new KsException("不能识别的参数类型", this);
				}
			}
			
			String returnValue = "L"+Object.class.getName().replaceAll("[.]", "/")+";";
			VarType returnType = new VarType(Object.class);
			returnType.setJavaObject(true);
			
			bcOp.invokeVirtual("Script"+(env.getSubScriptName()==null?env.getScriptName():env.getSubScriptName()), func.getName(), params, returnValue, false); 
			
			return returnType;
		} else if (value instanceof KsObject) { // 脚本内对象
						
			KsObject ko = (KsObject)value;
						
			try {
				String className = ko.getClassName().replaceAll("[.]", "/");
				bcOp.gcMethod().visitTypeInsn(BcOpcodes.NEW, className);
				bcOp.gcMethod().visitInsn(BcOpcodes.DUP);
				
				String params = BcGenerator.getClassType(Environment.class);
				/*for (ASTNode a : this) {
					Object obj = a.compile(env, bcOp);
					if (obj == null) {
						throw new KsException("不能识别的参数类型", this);
					} else if (obj instanceof VarType) {
						params = params + BcGenerator.getBcType((VarType)obj);
					} else {
						throw new KsException("不能识别的参数类型", this);
					}
				}*/
				bcOp.getThis();
				bcOp.getField("org/ks/bc/ScriptBase", "env", BcGenerator.getClassType(Environment.class));
				bcOp.invokeSpecial(className, "<init>", params, "V", false);
				
				VarType type = new VarType(ko.getJavaClass());
				type.setJavaObject(true);
				return type;
				
			} catch (Exception e) {
				// e.printStackTrace();
				throw new KsException(e, this);
			}
			
			
		} else if (value instanceof JavaKsObject) { // 执行java对象函数
			JavaKsObject jko = (JavaKsObject)value;
									
			Class<?> realClass = (Class<?>)jko.getObject();
			String className = realClass.getName().replaceAll("[.]", "/");
			
			// 初始化参数值
			Object[] valuesIn = new Object[numChildren()];
			int num = 0;
		
			String params = "";
			for (ASTNode a : this) {
				Object obj = a.compile(env, bcOp);
				valuesIn[num++] = obj;
				if (obj == null) {
					throw new KsException("不能识别的参数类型", this);
				} else if (obj instanceof VarType) {
					// params = params + BytecodeUtil.getBcType((VarType)obj);
				} else if (obj instanceof JavaKsObject) { 
					
				} else { // to do 有可能是其他类型
					// throw new KsException("不能识别的参数类型", this);
				}
			}
			
			// 输入参数类型设定
			Class<?> argIn[] = new Class[valuesIn.length];
					
			try {
				// 找出对应的函数
				List<Method> mList = new ArrayList<Method>();
				for (Method m: realClass.getMethods()) {
					if (jko.getMethod().equals(m.getName()) && m.getParameterCount() == argIn.length) {
						mList.add(m);
					}
				}
				for (Method m: mList) {
					int i = 0;
					for (Class<?> p: m.getParameterTypes()) {
						if(valuesIn[i] instanceof VarType) {
							// 找出匹配参数的函数
							if(p.isAssignableFrom(((VarType)valuesIn[i]).getJavaClass())) {
								argIn[i] = p; //((VarType)valuesIn[i]).getJavaClass();
								params = params + BcGenerator.getBcType(new VarType(p));
							}
						} else if (valuesIn[i] instanceof JavaKsObject) {
							// 找出匹配参数的函数
							if(p.isAssignableFrom(((JavaKsObject)valuesIn[i]).getJavaClass())) {
								argIn[i] = p; //((VarType)valuesIn[i]).getJavaClass();
								params = params + BcGenerator.getBcType(new VarType(p));
							}
						} else { // to do 有可能是其他类型
							throw new KsException("不能识别的类型", this);
						}
						i++;
					}
					// 验证argIn
					if(checkArgInNotNull(argIn)) {
						break;
					}
				}
				
				Method method = realClass.getMethod(jko.getMethod(), argIn);
				String returnValue = "";
				VarType returnType = null;
				if(method.getGenericReturnType().getTypeName().equals("int")) {
					returnValue = "I";
					returnType = new VarType(int.class);
				} else if(method.getGenericReturnType().getTypeName().equals("long")) {
					returnValue = "J";
					returnType = new VarType(long.class);
				} else if(method.getGenericReturnType().getTypeName().equals("double")) {
					returnValue = "D";
					returnType = new VarType(double.class);
				} else if(method.getGenericReturnType().getTypeName().equals("boolean")) {
					returnValue = "Z";
					returnType = new VarType(boolean.class);
				} else {
					if (method.getGenericReturnType().getTypeName().equals("void")) {
						returnValue = "V";
					} else {
						returnValue = "L"+method.getGenericReturnType().getTypeName().replaceAll("[.]", "/")+";";
						returnType = new VarType(method.getReturnType());
						returnType.setJavaObject(true);
					}
				}
				
				bcOp.invokeVirtual(className, jko.getMethod(), params, returnValue, false); 
				
				return returnType;
			} catch (Exception e) {
				// e.printStackTrace();
				throw new KsException(e, this);
			}
			
		} else if (value instanceof JavaKsClass) { // java的构造函数
			
			JavaKsClass jkc = (JavaKsClass)value;
			try {
				String className = jkc.getJavaClass().getName().replaceAll("[.]", "/");
				bcOp.gcMethod().visitTypeInsn(BcOpcodes.NEW, className);
				bcOp.gcMethod().visitInsn(BcOpcodes.DUP);
				String params = "";
				for (ASTNode a : this) {
					Object obj = a.compile(env, bcOp);
					if (obj == null) {
						throw new KsException("不能识别的参数类型", this);
					} else if (obj instanceof VarType) {
						params = params + BcGenerator.getBcType((VarType)obj);
					} else {
						throw new KsException("不能识别的参数类型", this);
					}
				}
				bcOp.invokeSpecial(className, "<init>", params, "V", false);
				
				VarType type = new VarType(jkc.getJavaClass());
				type.setJavaObject(true);
				return type;
				
			} catch (Exception e) {
				// e.printStackTrace();
				throw new KsException(e, this);
			}
			
		}else if (value instanceof JavaKsStaticClass) { // 执行java的静态函数
			JavaKsStaticClass jkc = (JavaKsStaticClass)value;
			
			String className = jkc.getJavaClass().getName().replaceAll("[.]", "/");
			
			// 初始化参数值
			Object[] valuesIn = new Object[numChildren()];
			int num = 0;
			
			String params = "";
			for (ASTNode a : this) {
				Object obj = a.compile(env, bcOp);
				valuesIn[num++] = obj;
				if (obj == null) {
					throw new KsException("不能识别的参数类型", this);
				} else if (obj instanceof VarType) {
					// params = params + BytecodeUtil.getBcType((VarType)obj);
				} else if (obj instanceof JavaKsObject) { 
					
				} else { // to do 有可能其他类型
					throw new KsException("不能识别的参数类型", this);
				}
			}
			
			// 输入参数类型设定
			Class<?> argIn[] = new Class[valuesIn.length];
			
			try {
				// 找出对应的函数
				List<Method> mList = new ArrayList<Method>();
				for (Method m: jkc.getJavaClass().getMethods()) {
					if (jkc.getMethod().equals(m.getName()) && m.getParameterCount() == argIn.length) {
						mList.add(m);
					}
				}
				for (Method m: mList) {
					int i = 0;
					for (Class<?> p: m.getParameterTypes()) {
						if(valuesIn[i] instanceof VarType) {
							// 找出匹配参数的函数
							if(p.isAssignableFrom(((VarType)valuesIn[i]).getJavaClass())) {
								argIn[i] = p; 
								params = params + BcGenerator.getBcType(new VarType(p));
							}
						} else if (valuesIn[i] instanceof JavaKsObject) {
							// 找出匹配参数的函数
							if(p.isAssignableFrom(((JavaKsObject)valuesIn[i]).getJavaClass())) {
								argIn[i] = p; //((VarType)valuesIn[i]).getJavaClass();
								params = params + BcGenerator.getBcType(new VarType(p));
							}
						} else { // to do 有可能其他类型
							throw new KsException("不能识别的类型", this);
						}
						i++;
					}
					// 验证argIn
					if(checkArgInNotNull(argIn)) {
						break;
					}
				}
				
				Method method = jkc.getJavaClass().getMethod(jkc.getMethod(), argIn);
				String returnValue = "";
				VarType returnType = null;
				if(method.getGenericReturnType().getTypeName().equals("int")) {
					returnValue = "I";
					returnType = new VarType(int.class);
				} else if(method.getGenericReturnType().getTypeName().equals("long")) {
					returnValue = "J";
					returnType = new VarType(long.class);
				} else if(method.getGenericReturnType().getTypeName().equals("double")) {
					returnValue = "D";
					returnType = new VarType(double.class);
				} else if(method.getGenericReturnType().getTypeName().equals("boolean")) {
					returnValue = "Z";
					returnType = new VarType(boolean.class);
				} else {
					if (method.getGenericReturnType().getTypeName().equals("void")) {
						returnValue = "V";
					} else {
						returnValue = "L"+method.getGenericReturnType().getTypeName().replaceAll("[.]", "/")+";";
						returnType = new VarType(method.getReturnType());
						returnType.setJavaObject(true);
					}
				}
				
				bcOp.invokeStatic(className, jkc.getMethod(), params, returnValue, false); 
				 
				return returnType;
			} catch (Exception e) {
				// e.printStackTrace();
				throw new KsException(e, this);
			}
			
		}
		return null;
	}
	
	private boolean checkArgInNotNull(Class<?> argIn[]) {
		int cnt = 0;
		for(int j = 0; j < argIn.length; j++) {
			if (argIn[j] != null) {
				cnt++;
			}
		}
		if (cnt == argIn.length) {
			return true;
		} else {
			return false;
		}
	}
	
}
