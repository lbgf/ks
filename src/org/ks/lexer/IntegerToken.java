/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */


package org.ks.lexer;

/**
 * IntegerToken.
 *
 */
public class IntegerToken extends Token {
	private int value;

	public IntegerToken(int line, int column, int v) {
		super(line, column);
		value = v;
	}
	
	public boolean isInteger() {
		return true;
	}

	public String getText() {
		return Integer.toString(value);
	}

	public int getInteger() {
		return value;
	}
}
