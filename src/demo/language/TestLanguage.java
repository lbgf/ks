package demo.language;

import java.io.File;
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

		File f = new File(System.getProperty("user.dir") + "\\src\\demo\\language\\demo.ks");
		
		KsDebug.showProcess = false;
		KsRegex.extLanguageRegx("\\p{script=Hira}\\p{script=Kana}");
		List<LanguagePkg> lps = new ArrayList<LanguagePkg>();
		lps.add(new LanguagePkgEn());
		lps.add(new LanguagePkgCn());
		lps.add(new LanguagePkgJp());
		
		KsRunner kr = new KsRunner(f, lps);
		kr.exec();
	}

}
