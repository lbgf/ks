/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.parser;

import java.io.IOException;

import org.ks.lexer.Token;

/**
 * 解释异常.
 *
 */
@SuppressWarnings("serial")
public class ParseException extends Exception {
	public ParseException(Token t) {
		this("", t);
	}

	public ParseException(String msg, Token t) {
		super("语法错误 " + location(t) + ". " + msg);
	}

	private static String location(Token t) {
		if (t == Token.EOF)
			return "最后一行";
		else
			return "\"" + t.getText() + "\" 在第" + t.getLineNumber() + "行 第" + t.getColumnNumber() + "列";
	}

	public ParseException(IOException e) {
		super(e);
	}

	public ParseException(String msg) {
		super(msg);
	}
}
