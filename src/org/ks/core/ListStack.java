/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.core;

import java.util.Arrays;

/**
 * ջ
 *
 * @param <T>
 */
public class ListStack<T> {
	
	// ʵ��ջ������
	private Object[] stack;
	// �����С
	private int size;

	public ListStack() {
		stack = new Object[10]; // ��ʼ����Ϊ10
	}

	/**
	 * ȡ��ջ����
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * �ж��Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * ����ջ��Ԫ��
	 * 
	 * @return
	 */
	public T peek() {
		T t = null;
		if (size > 0)
			t = (T) stack[size - 1];
		return t;
	}
	
	/**
	 * ����ջ��Ԫ��
	 * 
	 * @return
	 */
	public void setPeek(T t) {
		if (size > 0)
			stack[size - 1] = t;
	}

	/**
	 * ѹջ
	 * 
	 * @param t
	 */
	public void push(T t) {
		expandCapacity(size + 1);
		stack[size] = t;
		size++;
	}

	/**
	 * ��ջ
	 * 
	 * @return
	 */
	public T pop() {
		T t = peek();
		if (size > 0) {
			stack[size - 1] = null;
			size--;
		}
		return t;
	}

	/**
	 * ��������
	 * 
	 * @param size
	 */
	public void expandCapacity(int size) {
		int len = stack.length;
		if (size > len) {
			size = size * 3 / 2 + 1;// ÿ������50%
			stack = Arrays.copyOf(stack, size);
		}
	}

}
