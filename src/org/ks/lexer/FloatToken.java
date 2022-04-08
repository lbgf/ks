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
 * FloatToken.
 *
 */
public class FloatToken extends Token {
	private float value;

	public FloatToken(int line, int column, float v) {
		super(line, column);
		value = v;
	}
	
	public boolean isFloat() {
		return true;
	}

	public String getText() {
		return Float.toString(value);
	}

	public double getFloat() {
		return value;
	}
}
