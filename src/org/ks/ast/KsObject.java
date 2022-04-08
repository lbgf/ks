/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast;

import org.ks.core.KsException;

/**
 * ks������չ.
 *
 */
public class KsObject {
	
	protected String className;
	protected Class<?> javaClass;
	protected KsClassInfo classInfo;
	protected Object[] fields;

	public KsObject(KsClassInfo classInfo, int size) {
		this.classInfo = classInfo;
		fields = new Object[size];
	}
	
	// ����BCģʽ
	public KsObject(KsClassInfo classInfo, int size, String className, Class<?> javaClass) {
		this(classInfo, size);
		this.className = className;
		this.javaClass = javaClass;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}


	public KsClassInfo classInfo() {
		return classInfo;
	}

	public Object read(String name) throws KsException {
		Integer i = classInfo.fieldIndex(name);
		if (i != null) {
			return fields[i];
		} else {
			i = classInfo.methodIndex(name);
			if (i != null)
				return method(i);
			else
				throw new KsException("�򲻵�����");
		}
	}

	public void write(String name, Object value) throws KsException {
		Integer i = classInfo.fieldIndex(name);
		if (i == null)
			throw new KsException("�򲻵�����");
		else
			fields[i] = value;
	}

	public Object read(int index) {
		return fields[index];
	}

	public void write(int index, Object value) {
		fields[index] = value;
	}

	public Object method(int index) {
		return classInfo.method(this, index);
	}
}
