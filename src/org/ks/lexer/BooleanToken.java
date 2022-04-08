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
 * BooleanToken.
 *
 */
public class BooleanToken extends Token {
	private boolean value;

	public BooleanToken(int line, int column, boolean v) {
		super(line, column);
		value = v;
	}
	
	public boolean isBoolean() {
		return true;
	}

	public String getText() {
		return Boolean.toString(value);
	}

	public boolean getBoolean() {
		return value;
	}
}
