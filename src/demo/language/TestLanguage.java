package demo.language;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.ks.core.KsRunner;
import org.ks.lexer.KsRegex;
import org.ks.plugin.language.LanguagePkg;
import org.ks.plugin.language.LanguagePkgCn;
import org.ks.plugin.language.LanguagePkgEn;
import org.ks.util.KsDebug;

import demo.language.jp.parser.LanguagePkgJp;

public class TestLanguage {

	public static void main(String[] args) throws Exception {

		LineNumberReader r = new LineNumberReader(new FileReader(System.getProperty("user.dir") + "\\src\\demo\\language\\demo.ks"));

		String str = null;
		String code = "";
		while ((str = r.readLine()) != null) {
			code += str + "\n";
		}
		r.close();
		
		KsDebug.showProcess = false;
		KsRegex.extLanguageRegx("\\p{script=Hira}\\p{script=Kana}");
		List<LanguagePkg> lps = new ArrayList<LanguagePkg>();
		lps.add(new LanguagePkgEn());
		lps.add(new LanguagePkgCn());
		lps.add(new LanguagePkgJp());
		
		KsRunner kr = new KsRunner(code, lps);
		kr.exec();
	}

}
