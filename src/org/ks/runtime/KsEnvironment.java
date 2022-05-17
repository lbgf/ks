/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.runtime;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.ks.bc.StackMapFrames;
import org.ks.core.KsClassLoader;
import org.ks.core.KsException;
import org.ks.core.ListStack;
import org.objectweb.asm.Label;

/**
 * KS环境.
 *
 */
public class KsEnvironment implements Environment, Cloneable {

	protected Object[] values;
	protected Environment outer;
	protected Symbols names;
	protected HashMap<String, Object> classValues;
	
	protected VarType[] types;
	
	protected ListStack<Integer> breakList;
	protected ListStack<Integer> continueList;
	protected ListStack<Integer> returnList;
	
	protected ListStack<Label> bcBreakList;
	protected ListStack<Label> bcContinueList;
	
	protected HashMap<String, Object> bcValues;
	
	protected String scriptName; // 脚本类名
	protected String subScriptName; // 脚本中的类名
	protected boolean isMethodEnvironment = false;
	
	protected String scriptRootPath; // 脚本根目录
	
	protected String saveClassPath;  // 保存类的目录
	
	protected KsClassLoader loader; // 类加载器
		
	protected StackMapFrames smf; // 处理堆栈验证
	
	protected byte runWay = -1; // 运行模式

	public KsEnvironment() {
		values = new Object[10]; 
		
		types = new VarType[10];
		
		outer = null;
		names = new Symbols();
		classValues = new HashMap<String, Object>();
		
		bcValues = new HashMap<String, Object>();
		
	}
	
	public KsEnvironment(int size, Environment out) { // 创建新环境
		values = new Object[size];
		
		types = new VarType[size];
		
		outer = out;
		
		// 把上级的类库放入下级，以便下级能查到类库
		classValues = out.getClassValues();// new HashMap<String, Object>();
	}

	public Symbols symbols() {
		return names;
	}

	public Object get(int nest, int index) {
		
		// 新加的，处理变量空间问题 to do nest index的空间还是有点问题，虽然
		// 隔离了变量，但nest index的值还是会生成，需要处理
		// System.out.println(outer + "," + index + "," + nest + "," + values.length); // test
		/*if (index >= values.length && outer != null) {
			if (nest > 0) {
				return outer.get(nest - 1, index); 
			}
			return outer.get(nest, index);
		}*/
		//
		
		if (nest == 0) {
			return values[index];
			
			// 新加的，处理变量空间问题
			/*if(values[index] != null) {
				return values[index];
			} else if (outer != null) {
				return outer.get(nest, index);
			} else {
				return null;
			}*/
			//
			
		} else if (outer == null) {
			return null;
		} else {
			return outer.get(nest - 1, index);
		}
	}

	public void put(int nest, int index, Object value) {
		if (nest == 0) {
			assign(index, value);
		} else {
			if (nest == 0) {
				values[index] = value;
			} else if (outer == null) {
				throw new KsException("上层环境为空");
			} else {
				outer.put(nest - 1, index, value);
			}
		}
	}

	public Object get(String name) {

		if (names == null) {
			return null;
		}
		
		Integer i = names.find(name);
		if (i == null) {
			if (outer == null) {
				return null;
			} else {
				return outer.get(name);
			}
		} else {
			return values[i];
		}
	}

	public void put(String name, Object value) {
		Environment e = where(name);
		if (e == null) {
			e = this;
		}
		e.putNew(name, value);
	}

	public void putNew(String name, Object value) {
		assign(names.putNew(name), value);
	}

	public Environment where(String name) {
		if (names.find(name) != null) {
			return this;
		} else if (outer == null) {
			return null;
		} else {
			return outer.where(name);
		}
	}

	public void setOuter(Environment e) {
		outer = e;
	}
	
	public Environment getOuter() {
		return outer;
	}

	protected void assign(int index, Object value) {
		if (index >= values.length) {
			int newLen = values.length * 3; // 这个数字要处理，变更大小时可能有问题， 目前改变3倍增长
			if (index >= newLen) {
				newLen = index + 1;
			}
			values = Arrays.copyOf(values, newLen);
		}
		values[index] = value;
	}

	public void putClass(String name, Object value) { // 针对java类库
		classValues.put(name, value);
	}

	public Object getClass(String name) { // 针对java类库
		/* // 开头处理了，不要递归找
		if(outer != null) {
			return this.outer.getClass(name);
		}
		*/
		return classValues.get(name);
	}
	
	public void setClassValues(HashMap<String, Object> classValues) {
		this.classValues = classValues;
	}
	
	public HashMap<String, Object> getClassValues() {
		/* // 开头处理了，不要递归找
		if(outer != null) {
			return this.outer.getClassValues();
		}
		*/
		return classValues;
	}
	
	public void setValues(Object[] values) {
		this.values = values;
	}
	
	public Object[] getValues() {
		return values;
	}
	
	public void setNames(Symbols names) {
		this.names = names;
	}
	
	public Symbols getNames() {
		return names;
	}
	
	public void putType(int nest, int index, VarType type) {
		if (nest == 0) {
			assignType(index, type);
		} else {
			if (nest == 0) {
				types[index] = type;
			} else if (outer == null) {
				throw new KsException("no outer environment");
			} else {
				outer.putType(nest - 1, index, type);
			}
		}
	}
	
	public VarType getType(int nest, int index) {
		if (nest == 0) {
			return types[index];
		} else if (outer == null) {
			return null;
		} else {
			return outer.getType(nest - 1, index);
		}
	}
	
	protected void assignType(int index, VarType type) {
		if (index >= types.length) {
			int newLen = types.length * 3;
			if (index >= newLen) {
				newLen = index + 1;
			}
			types = Arrays.copyOf(types, newLen);
		}
		types[index] = type;
	}
	
	public ListStack<Integer> getBreakList() {
		if(outer != null) {
			return this.outer.getBreakList();
		}
		return breakList;
	}
	
	public ListStack<Integer> getContinueList() {
		if(outer != null) {
			return this.outer.getContinueList();
		}
		return continueList;
	}
	
	public ListStack<Integer> getReturnList() {
		if(outer != null) {
			return this.outer.getReturnList();
		}
		return returnList;
	}
	
	public void initVarIndex() {
		names.initVarIndex(); // 用于BC模式
	}
	
	public void initFristEnv() {
		breakList = new ListStack<Integer>();
		continueList = new ListStack<Integer>();
		returnList = new ListStack<Integer>();
		returnList.push(0); // 最外层的return
	}
	
	public ListStack<Label> getBcBreakList() {
		if(outer != null) {
			return this.outer.getBcBreakList();
		}
		return bcBreakList;
	}
	
	public ListStack<Label> getBcContinueList() {
		if(outer != null) {
			return this.outer.getBcContinueList();
		}
		return bcContinueList;
	}
	
	public void initBcFristEnv() { // 用于BC模式
		bcBreakList = new ListStack<Label>();
		bcContinueList = new ListStack<Label>();
	}
	
	public void putBcValue(String name, Object value) { // 针对java字节码的环境变量
		if(outer != null) {
			this.outer.putBcValue(name, value);
		} else {
			bcValues.put(name, value);
		}
	}
	
	public Object getBcValue(String name) { // 针对java字节码的环境变量
		if(outer != null) {
			return this.outer.getBcValue(name);
		}
		return bcValues.get(name);
	}
	
	public String getScriptName() {
		if(outer != null) {
			return this.outer.getScriptName();
		}
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		if(outer != null) {
			this.outer.setScriptName(scriptName);
		} else {
			this.scriptName = scriptName;
		}
	}
	
	public boolean isMethodEnvironment() {
		return isMethodEnvironment;
	}

	public void setMethodEnvironment(boolean isMethodEnvironment) {
		this.isMethodEnvironment = isMethodEnvironment;
	}
	
	public String getScriptRootPath() {
		if(outer != null) {
			return this.outer.getScriptRootPath();
		}
		return scriptRootPath;
	}
	
	public void setScriptRootPath(String scriptRootPath) {
		if(outer != null) {
			this.outer.setScriptRootPath(scriptRootPath);
		} else {
			this.scriptRootPath =  scriptRootPath;
		}
	}
	
	public String getSaveClassPath() {
		if(outer != null) {
			return this.outer.getSaveClassPath();
		}
		return saveClassPath;
	}
	
	public void setSaveClassPath(String saveClassPath) {
		if(outer != null) {
			this.outer.setSaveClassPath(saveClassPath);
		} else {
			this.saveClassPath =  saveClassPath;
		}
	}
	
	public String getSubScriptName() {
		if(subScriptName != null) {
			return subScriptName;
		} else if(outer != null) {
			return this.outer.getSubScriptName();
		} else {
			return null;
		}
	}

	public void setSubScriptName(String subScriptName) {
		this.subScriptName = subScriptName;
	}

	public void setKsClassLoader(KsClassLoader loader) {
		if(outer != null) {
			this.outer.setKsClassLoader(loader);
		} else {
			this.loader = loader;
		}
	}

	public KsClassLoader getKsClassLoader() {
		if(outer != null) {
			return this.outer.getKsClassLoader();
		}
		return loader;
	}
	
	public void initSmf() {
		smf = new StackMapFrames();
	}
	
	public void initSmf(StackMapFrames smfP) {
		smf = new StackMapFrames();
		smf.putFrameObjs(smfP.getFrameObjs());
		smf.putNewSize(smfP.getNewSize());
		smf.putOldSize(smfP.getOldSize());
	}
	
	public StackMapFrames getSmf() {
		if(smf == null && outer != null) {
			return this.outer.getSmf();
		} 
		return smf;
	}
	
	public List<Object> getFrameObjs() {
		if(smf == null && outer != null) {
			return this.outer.getFrameObjs();
		}
		return smf.getFrameObjs();
	}
	
	public void putFrameObjs(List<Object> objList) { 
		if(smf == null && outer != null) {
			this.outer.putFrameObjs(objList);
		} else {
			smf.putFrameObjs(objList);
		}
	}
	
	public void putFrameObj(Object value) { 
		if(smf == null && outer != null) {
			this.outer.putFrameObj(value);
		} else {
			smf.putFrameObj(value);
		}
	}
	
	public void clearFrameObjs() { 
		if(smf == null && outer != null) {
			this.outer.clearFrameObjs();
		} else {
			smf.clearFrameObjs();
		}
	}
	
	public byte getRunWay() {
		if(outer != null) {
			return this.outer.getRunWay();
		}
		return runWay;
	}
	
	public void setRunWay(byte runWay) {
		if(outer != null) {
			this.outer.setRunWay(runWay);
		} else {
			this.runWay =  runWay;
		}
	}
	
}
