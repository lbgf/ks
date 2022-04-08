/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ���븨����.
 *
 */
public class EncodeUtil {

	/**
	 * unicodeת����.
	 * 
	 * @param unicode
	 * @return
	 */
	public static String unicodeToCn(String unicode) {
    String unicodeCompile = "(?<=\\\\u).{4}?";
    String a;
    Matcher matcher = Pattern.compile(unicodeCompile).matcher(unicode);
    for (; matcher.find(); ) {
        a = matcher.group();
        unicode = unicode.replace("\\u" + a, String.valueOf((char) Integer.valueOf(a, 16).intValue()));
    }
    return unicode;
	}

	/**
	 * ����תunicode.
	 * 
	 * @param unicode
	 * @return
	 */
	public static String cnToUnicode(String cn) {
		String result = "";
		for (int i = 0; i < cn.length(); i++) {
			int chr1 = (char) cn.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) { // ���ַ�Χ \u4e00-\u9fa5 (����)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += cn.charAt(i);
			}
		}
		return result;
	}

	// test
	public static void main(String[] args) {
		String cn = "��cb1";
		System.out.println(cnToUnicode(cn));
		String unicode = "\\u4f60cb1";
		System.out.println(unicodeToCn(unicode));
	}

}
