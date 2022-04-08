/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.core;

import java.util.Arrays;

/**
 * 栈
 *
 * @param <T>
 */
public class ListStack<T> {
	
	// 实现栈的数组
	private Object[] stack;
	// 数组大小
	private int size;

	public ListStack() {
		stack = new Object[10]; // 初始容量为10
	}

	/**
	 * 取得栈长度
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 判断是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 返回栈顶元素
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
	 * 设置栈顶元素
	 * 
	 * @return
	 */
	public void setPeek(T t) {
		if (size > 0)
			stack[size - 1] = t;
	}

	/**
	 * 压栈
	 * 
	 * @param t
	 */
	public void push(T t) {
		expandCapacity(size + 1);
		stack[size] = t;
		size++;
	}

	/**
	 * 出栈
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
	 * 扩大容量
	 * 
	 * @param size
	 */
	public void expandCapacity(int size) {
		int len = stack.length;
		if (size > len) {
			size = size * 3 / 2 + 1;// 每次扩大50%
			stack = Arrays.copyOf(stack, size);
		}
	}

}
