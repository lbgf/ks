/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
