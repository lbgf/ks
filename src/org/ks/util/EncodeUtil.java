/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 编码辅助类.
 *
 */
public class EncodeUtil {

	/**
	 * unicode转中文.
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
	 * 中文转unicode.
	 * 
	 * @param unicode
	 * @return
	 */
	public static String cnToUnicode(String cn) {
		String result = "";
		for (int i = 0; i < cn.length(); i++) {
			int chr1 = (char) cn.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) { // 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += cn.charAt(i);
			}
		}
		return result;
	}

	// test
	public static void main(String[] args) {
		String cn = "你cb1";
		System.out.println(cnToUnicode(cn));
		String unicode = "\\u4f60cb1";
		System.out.println(unicodeToCn(unicode));
	}

}
