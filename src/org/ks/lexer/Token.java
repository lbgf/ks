/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */


package org.ks.lexer;

import org.ks.core.KsException;

/**
 * 标记单元.
 *
 */
public class Token {
	
	public static final Token EOF = new Token(-1, -1); // 文件尾
	public static final String EOL = "\\n"; // 行尾
	
	private int lineNumber; // 行号
	private int columnNumber; // 列号

	protected Token(int line, int column) {
		lineNumber = line;
		columnNumber = column;
	}

	// 行号
	public int getLineNumber() {
		return lineNumber;
	}
	
	// 列号
	public int getColumnNumber() {
		return columnNumber;
	}

	// 是否标识符(用于变量名)
	public boolean isIdentifier() {
		return false;
	}
	
	// 是否整数
	public boolean isInteger() {
		return false;
	}
	
	// 是否长整数
	public boolean isLong() {
		return false;
	}
	
	// 是否小数
	public boolean isFloat() {
		return false;
	}
	
	// 是否高精小数
	public boolean isDouble() {
		return false;
	}
	
	// 是否布尔型
	public boolean isBoolean() {
		return false;
	}

	// 是否字符串
	public boolean isString() {
		return false;
	}
	
	public int getInteger() {
		throw new KsException("没有发现标记");
	}
	
	public long getLong() {
		throw new KsException("没有发现标记");
	}
	
	public double getFloat() {
		throw new KsException("没有发现标记");
	}
	
	public double getDouble() {
		throw new KsException("没有发现标记");
	}
	
	public boolean getBoolean() {
		throw new KsException("没有发现标记");
	}

	public String getText() {
		return "";
	}
}
