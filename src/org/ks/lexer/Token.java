/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */


package org.ks.lexer;

import org.ks.core.KsException;

/**
 * ��ǵ�Ԫ.
 *
 */
public class Token {
	
	public static final Token EOF = new Token(-1, -1); // �ļ�β
	public static final String EOL = "\\n"; // ��β
	
	private int lineNumber; // �к�
	private int columnNumber; // �к�

	protected Token(int line, int column) {
		lineNumber = line;
		columnNumber = column;
	}

	// �к�
	public int getLineNumber() {
		return lineNumber;
	}
	
	// �к�
	public int getColumnNumber() {
		return columnNumber;
	}

	// �Ƿ��ʶ��(���ڱ�����)
	public boolean isIdentifier() {
		return false;
	}
	
	// �Ƿ�����
	public boolean isInteger() {
		return false;
	}
	
	// �Ƿ�����
	public boolean isLong() {
		return false;
	}
	
	// �Ƿ�С��
	public boolean isFloat() {
		return false;
	}
	
	// �Ƿ�߾�С��
	public boolean isDouble() {
		return false;
	}
	
	// �Ƿ񲼶���
	public boolean isBoolean() {
		return false;
	}

	// �Ƿ��ַ���
	public boolean isString() {
		return false;
	}
	
	public int getInteger() {
		throw new KsException("û�з��ֱ��");
	}
	
	public long getLong() {
		throw new KsException("û�з��ֱ��");
	}
	
	public double getFloat() {
		throw new KsException("û�з��ֱ��");
	}
	
	public double getDouble() {
		throw new KsException("û�з��ֱ��");
	}
	
	public boolean getBoolean() {
		throw new KsException("û�з��ֱ��");
	}

	public String getText() {
		return "";
	}
}
