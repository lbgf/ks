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
 * IdToken.
 *
 */
public class IdToken extends Token {
	private String text;

	public IdToken(int line, int column, String id) {
		super(line, column);
		text = id;
	}

	public boolean isIdentifier() {
		return true;
	}

	public String getText() {
		return text;
	}
}
