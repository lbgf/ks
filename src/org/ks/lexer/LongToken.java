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
 * LongToken.
 *
 */
public class LongToken extends Token {
	private long value;

	public LongToken(int line, int column, long v) {
		super(line, column);
		value = v;
	}
	
	public boolean isLong() {
		return true;
	}

	public String getText() {
		return Long.toString(value);
	}

	public long getLong() {
		return value;
	}
}
