/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
 * KS����.
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
	
	protected String scriptName; // �ű�����
	protected String subScriptName; // �ű��е�����
	protected boolean isMethodEnvironment = false;
	
	protected String scriptRootPath; // �ű���Ŀ¼
	
	protected String saveClassPath;  // �������Ŀ¼
	
	protected KsClassLoader loader; // �������
		
	protected StackMapFrames smf; // �����ջ��֤
	
	protected byte runWay = -1; // ����ģʽ

	public KsEnvironment() {
		values = new Object[10]; 
		
		types = new VarType[10];
		
		outer = null;
		names = new Symbols();
		classValues = new HashMap<String, Object>();
		
		bcValues = new HashMap<String, Object>();
		
	}
	
	public KsEnvironment(int size, Environment out) { // �����»���
		values = new Object[size];
		
		types = new VarType[size];
		
		outer = out;
		
		// ���ϼ����������¼����Ա��¼��ܲ鵽���
		classValues = out.getClassValues();// new HashMap<String, Object>();
	}

	public Symbols symbols() {
		return names;
	}

	public Object get(int nest, int index) {
		
		// �¼ӵģ���������ռ����� to do nest index�Ŀռ仹���е����⣬��Ȼ
		// �����˱�������nest index��ֵ���ǻ����ɣ���Ҫ����
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
			
			// �¼ӵģ���������ռ�����
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
				throw new KsException("�ϲ㻷��Ϊ��");
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
			int newLen = values.length * 3; // �������Ҫ���������Сʱ���������⣬ Ŀǰ�ı�3������
			if (index >= newLen) {
				newLen = index + 1;
			}
			values = Arrays.copyOf(values, newLen);
		}
		values[index] = value;
	}

	public void putClass(String name, Object value) { // ���java���
		classValues.put(name, value);
	}

	public Object getClass(String name) { // ���java���
		/* // ��ͷ�����ˣ���Ҫ�ݹ���
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
		/* // ��ͷ�����ˣ���Ҫ�ݹ���
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
		names.initVarIndex(); // ����BCģʽ
	}
	
	public void initFristEnv() {
		breakList = new ListStack<Integer>();
		continueList = new ListStack<Integer>();
		returnList = new ListStack<Integer>();
		returnList.push(0); // ������return
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
	
	public void initBcFristEnv() { // ����BCģʽ
		bcBreakList = new ListStack<Label>();
		bcContinueList = new ListStack<Label>();
	}
	
	public void putBcValue(String name, Object value) { // ���java�ֽ���Ļ�������
		if(outer != null) {
			this.outer.putBcValue(name, value);
		} else {
			bcValues.put(name, value);
		}
	}
	
	public Object getBcValue(String name) { // ���java�ֽ���Ļ�������
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
