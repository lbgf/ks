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
 * StringToken.
 *
 */
public class StringToken extends Token {
	private String literal;

	public StringToken(int line, int column, String str) {
		super(line, column);
		literal = str;
	}

	public boolean isString() {
		return true;
	}

	public String getText() {
		return literal;
	}
}
