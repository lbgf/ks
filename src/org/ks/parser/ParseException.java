/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.parser;

import java.io.IOException;

import org.ks.lexer.Token;

/**
 * �����쳣.
 *
 */
@SuppressWarnings("serial")
public class ParseException extends Exception {
	public ParseException(Token t) {
		this("", t);
	}

	public ParseException(String msg, Token t) {
		super("�﷨���� " + location(t) + ". " + msg);
	}

	private static String location(Token t) {
		if (t == Token.EOF)
			return "���һ��";
		else
			return "\"" + t.getText() + "\" �ڵ�" + t.getLineNumber() + "�� ��" + t.getColumnNumber() + "��";
	}

	public ParseException(IOException e) {
		super(e);
	}

	public ParseException(String msg) {
		super(msg);
	}
}
