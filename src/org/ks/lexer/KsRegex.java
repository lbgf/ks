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
 * �ִʹ���.
 */
public final class KsRegex {
	
	/*
	 \\p{Punct} �����ţ�!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
	 \s*��ƥ��0�������ո񣬻ᾡ���ܶ��ƥ�� ̰��
	 \s*?��ƥ����С�����Ŀո�Ҳ����0���ո� ��̰��
	 .*����̰�������ʣ�����ƥ�䵽����ƥ��Ϊֹ�����ݺ����������ʽ������л���
	 [0-9]+�ͱ�ʾ���ٿ���1������಻���Ƶ�����
	 ��\\����ʾ����һ��"\"
	 [\u4e00-\u9fa5]+ֻ�ܰ�������
	 \\p{script=Han} ֧�����ı�ʶ��
	 ((\\d+\\.\\d+)|(\\d+)) ����
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
	 * ���캯��.
	 */
	private KsRegex() {

	}
	
	public static String getRegex() {
		return REGEX1 + "(" + REGEX2 + REGEX3 + REGEX4 + REGEX5 + REGEX6 + REGEX7 + ")" + REGEX8;
	}
	
	/**
	 * ��չ���Թ���.
	 * 
	 * @param languageRegx String������ʽ
	 */
	public static void extLanguageRegx(String languageRegx) {
		REGEX5 = "[\\p{script=Han}" + languageRegx + "A-Z_a-z$][\\p{script=Han}" + languageRegx + "A-Z_a-z0-9$]*|";
	}

}
