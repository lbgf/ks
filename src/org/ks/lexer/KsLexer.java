/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.lexer;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ks.parser.ParseException;

/**
 * 词法分析器.
 *
 */
public class KsLexer {
	
	private String preIdToken = "";
	private boolean isCommentBlock = false;
	private boolean isStringBlock = false;
	private StringBuilder stringBlock = new StringBuilder();
	private Pattern pattern = Pattern.compile(KsRegex.getRegex());
	private List<Token> queue = new ArrayList<Token>();
	private boolean hasMore;
	private LineNumberReader reader;

	public KsLexer(Reader r) {
		hasMore = true;
		reader = new LineNumberReader(r);
	}

	public KsLexer(String code) {
		hasMore = true;
		reader = new LineNumberReader(new StringReader(code));
	}

	public Token read() throws ParseException {
		if (fillQueue(0)) {
			return queue.remove(0);
		} else {
			return Token.EOF;
		}
	}

	public Token peek(int i) throws ParseException {
		if (fillQueue(i)) {
			return queue.get(i);
		} else {
			return Token.EOF;
		}
	}

	private boolean fillQueue(int i) throws ParseException {
		while (i >= queue.size()) {
			if (hasMore) {
				readLine();
			} else {
				return false;
			}
		}
		return true;
	}

	protected void readLine() throws ParseException {
		String line;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			throw new ParseException(e);
		}
		if (line == null) {
			hasMore = false;
			return;
		}
		int lineNo = reader.getLineNumber();
		Matcher matcher = pattern.matcher(line);
		matcher.useTransparentBounds(true).useAnchoringBounds(false);
		int pos = 0;
		int endPos = line.length();
		while (pos < endPos) {
			matcher.region(pos, endPos);
			if (matcher.lookingAt()) {
				addToken(lineNo, pos+1, matcher);
				pos = matcher.end();
			} else {
				throw new ParseException("错误的标记，出错行号为：" + lineNo);
			}
		}
		String tmpStr = line.trim();
		if (isStringBlock) { // 字符块内不加结束符
			
		} else if (tmpStr.lastIndexOf(",") == tmpStr.length()-1) { // ,号结尾不加结束符
			
		} else if (tmpStr.lastIndexOf(".") == tmpStr.length()-1) { // .号结尾不加结束符
			
		} else {
			queue.add(new IdToken(lineNo, 0, Token.EOL)); // 结束符
		}
	}

	protected void addToken(int lineNo, int columnNo, Matcher matcher) {
		String m = matcher.group(1);
		
		if (m != null) { // 空格
			
			if (matcher.group(2) == null) { // 注释
				Token token;
				if (isCommentBlock) { // 注释块
					return;
				}
				if (isStringBlock) { // 字符块
					stringBlock.append(m.toString() + "\n");
					return;
				} else if(!isStringBlock && stringBlock.length() > 0) {
					token = new StringToken(lineNo, columnNo, stringBlock.toString().trim());
					stringBlock.delete(0, stringBlock.length());
					queue.add(token);
					return;
				}
				if (matcher.group(3) != null) { // 小数	
					if(m.substring(m.length() - 1).equals("D")) { // double
						token = new DoubleToken(lineNo, columnNo, Double.parseDouble(m.substring(0, m.length()-1)));
					} else {
						if (Double.parseDouble(m) >= Float.MAX_VALUE) {
							token = new DoubleToken(lineNo, columnNo, Double.parseDouble(m));
						} else {
							token = new FloatToken(lineNo, columnNo, Float.parseFloat(m));
						}
					}
				} else if (matcher.group(4) != null) { // 整数
					if(m.substring(m.length() - 1).equals("L")) { // long
						token = new LongToken(lineNo, columnNo, Long.parseLong(m.substring(0, m.length()-1)));
					} else {
						if (Long.parseLong(m) >= Integer.MAX_VALUE) {
							token = new LongToken(lineNo, columnNo, Long.parseLong(m));
						} else {
							token = new IntegerToken(lineNo, columnNo, Integer.parseInt(m));
						}
					}
				} else if (matcher.group(5) != null) { // 字符串
					// to do 处理boolean型
					token = new StringToken(lineNo, columnNo, toStringLiteral(m));
				} else { // 标识（关键字和一般标识符）
					if (m.equals("++")) {
						token = new IdToken(lineNo, columnNo, "="); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, preIdToken); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, "+"); 
						queue.add(token);
						token = new IntegerToken(lineNo, columnNo, Integer.parseInt("1")); 
					} else if (m.equals("--")) {
						token = new IdToken(lineNo, columnNo, "="); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, preIdToken); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, "-"); 
						queue.add(token);
						token = new IntegerToken(lineNo, columnNo, Integer.parseInt("1")); 
					} else if (m.equals("+=")) {
						token = new IdToken(lineNo, columnNo, "="); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, preIdToken); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, "+"); 
					} else if (m.equals("-=")) {
						token = new IdToken(lineNo, columnNo, "="); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, preIdToken); 
						queue.add(token);
						token = new IdToken(lineNo, columnNo, "-"); 
					} else {
						token = new IdToken(lineNo, columnNo, m); 
						preIdToken = m;
					}
				}
				queue.add(token);
			} else {
				m = matcher.group(2);
				if (m.indexOf("/*") >= 0) {
					isCommentBlock = true;
				} else if (m.indexOf("*/") >= 0) {
					isCommentBlock = false;
				} else if (m.indexOf("\"\"\"") >= 0)  {
					if (isStringBlock) {
						isStringBlock = false;
					} else {
						isStringBlock = true;
					}
				}
			}
		}
		
	}

	protected String toStringLiteral(String s) {
		StringBuilder sb = new StringBuilder();
		int len = s.length() - 1;
		for (int i = 1; i < len; i++) {
			char c = s.charAt(i);
			if (c == '\\' && i + 1 < len) {
				int c2 = s.charAt(i + 1);
				if (c2 == '"' || c2 == '\\') {
					c = s.charAt(++i);
				} else if (c2 == 'n') {
					++i;
					c = '\n';
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}

}
