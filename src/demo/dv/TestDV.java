package demo.dv;

import java.io.FileReader;
import java.io.LineNumberReader;

import org.ks.core.KsRunner;
import org.ks.util.KsDebug;

public class TestDV {

	public static void main(String[] args) throws Exception {

		LineNumberReader r = new LineNumberReader(new FileReader(System.getProperty("user.dir") + "\\src\\demo\\dv\\demo.ks"));

		String str = null;
		String code = "";
		while ((str = r.readLine()) != null) {
			code += str + "\n";
		}
		r.close();
		
		KsDebug.showProcess = false;
		KsRunner kr = new KsRunner(code, null);
		// kr.setVariable("a", "aaa");
		kr.exec();
	}

}
