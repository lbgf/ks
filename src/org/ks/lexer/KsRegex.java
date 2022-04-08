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
 * 分词规则.
 */
public final class KsRegex {
	
	/*
	 \\p{Punct} 标点符号：!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
	 \s*：匹配0个或多个空格，会尽可能多的匹配 贪婪
	 \s*?：匹配最小数量的空格，也就是0个空格 非贪婪
	 .*具有贪婪的性质，首先匹配到不能匹配为止，根据后面的正则表达式，会进行回溯
	 [0-9]+就表示至少可以1个、最多不限制的数字
	 “\\”表示的是一个"\"
	 [\u4e00-\u9fa5]+只能包含汉字
	 \\p{script=Han} 支持中文标识符
	 ((\\d+\\.\\d+)|(\\d+)) 数字
	*/
	private static String REGEX1 = "\\s*";
	private static String REGEX2 = "(//.*|/\\*.*|\\*/|\"\"\")|";
	private static String REGEX3 = "(\\d+\\.\\d+D?)|(\\d+L?)|";
	private static String REGEX4 = "(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")|";
	private static String REGEX5 = "[\\p{script=Han}A-Z_a-z$][\\p{script=Han}A-Z_a-z0-9$]*|";
	private static String REGEX6 = "\\+=|-=|\\+\\+|<=>|--|==|<=|>=|!=|&&|\\|\\||";
	private static String REGEX7 = "\\p{Punct}";
	private static String REGEX8 = "?";
	
	/**
	 * 构造函数.
	 */
	private KsRegex() {

	}
	
	public static String getRegex() {
		return REGEX1 + "(" + REGEX2 + REGEX3 + REGEX4 + REGEX5 + REGEX6 + REGEX7 + ")" + REGEX8;
	}
	
	/**
	 * 扩展语言规则.
	 * 
	 * @param languageRegx String：正则式
	 */
	public static void extLanguageRegx(String languageRegx) {
		REGEX5 = "[\\p{script=Han}" + languageRegx + "A-Z_a-z$][\\p{script=Han}" + languageRegx + "A-Z_a-z0-9$]*|";
	}

}
