/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.runtime;

import java.util.HashMap;
import java.util.List;

import org.ks.bc.StackMapFrames;
import org.ks.core.KsClassLoader;
import org.ks.core.ListStack;
import org.objectweb.asm.Label;

/**
 * 环境接口.
 *
 */
public interface Environment {
	
	public Symbols symbols();
	
	public void put(String name, Object value);
	
	public void put(int nest, int index, Object value);

	public Object get(String name);
	
	public Object get(int nest, int index);

	public void putNew(String name, Object value);

	public Environment where(String name);

	public void setOuter(Environment e);
	
	public Environment getOuter();
		
	public void putClass(String name, Object value);
	
	public Object getClass(String name);
	
	public void setClassValues(HashMap<String, Object> classValues);
	
	public HashMap<String, Object> getClassValues();
	
	public void setValues(Object[] values);
	
	public Object[] getValues();
	
	public void setNames(Symbols names);
	
	public Symbols getNames();
	
	public void putType(int nest, int index, VarType type);
	
	public VarType getType(int nest, int index);
	
	public ListStack<Integer> getBreakList();
	
	public ListStack<Integer> getContinueList();
	
	public ListStack<Integer> getReturnList();
	
	public ListStack<Label> getBcBreakList();
	
	public ListStack<Label> getBcContinueList();
	
	public void putBcValue(String name, Object value);
	
	public Object getBcValue(String name);
	
	public void setScriptName(String name);
	
	public String getScriptName();
	
	public boolean isMethodEnvironment();

	public void setMethodEnvironment(boolean isMethodEnvironment);
	
	public String getScriptRootPath();
	
	public void setScriptRootPath(String saveClassPath);
	
	public String getSaveClassPath();
	
	public void setSaveClassPath(String saveClassPath);
	
	public void setSubScriptName(String name);
	
	public String getSubScriptName();
	
	public void setKsClassLoader(KsClassLoader loader);
	
	public KsClassLoader getKsClassLoader();
	
	public void initSmf();
	
	public void initSmf(StackMapFrames smf);
	
	public StackMapFrames getSmf();
	
	public List<Object> getFrameObjs();
	
	public void putFrameObjs(List<Object> objList);
	
	public void putFrameObj(Object value);
	
	public void clearFrameObjs();
	
	public byte getRunWay();
	
	public void setRunWay(byte runWay);
	
}
