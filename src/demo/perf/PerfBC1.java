package demo.perf;

import java.io.FileReader;
import java.io.LineNumberReader;

import org.ks.core.KsRunner;
import org.ks.util.KsDebug;

public class PerfBC1 {

	public static void main(String[] args) throws Exception {

		LineNumberReader r = new LineNumberReader(new FileReader(System.getProperty("user.dir") + "\\src\\demo\\perf\\bc1.ks"));

		String str = null;
		String code = "";
		while ((str = r.readLine()) != null) {
			code += str + "\n";
		}
		r.close();
		
		KsDebug.showProcess = false;
		
		KsRunner kr = new KsRunner(code, null, "test", System.getProperty("user.dir") + "\\bin\\");
		kr.exec();
	}

}
