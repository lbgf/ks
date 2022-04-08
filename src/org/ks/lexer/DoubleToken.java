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
 * DoubleToken.
 *
 */
public class DoubleToken extends Token {
	private double value;

	public DoubleToken(int line, int column, double v) {
		super(line, column);
		value = v;
	}
	
	public boolean isDouble() {
		return true;
	}

	public String getText() {
		return Double.toString(value);
	}

	public double getDouble() {
		return value;
	}
}
