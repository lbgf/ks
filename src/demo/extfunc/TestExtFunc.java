package demo.extfunc;

import java.io.FileReader;
import java.io.LineNumberReader;

import org.ks.core.KsRunner;
import org.ks.util.KsDebug;


public class TestExtFunc {

	public static void main(String[] args) throws Exception {

		LineNumberReader r = new LineNumberReader(new FileReader(System.getProperty("user.dir") + "\\src\\demo\\extfunc\\demo.ks"));

		String str = null;
		String code = "";
		while ((str = r.readLine()) != null) {
			code += str + "\n";
		}
		r.close();
		
		KsDebug.showProcess = false;
		
		KsRunner kr = new KsRunner(code, null);
		kr.appendNative("extPrint", TestExtFunc.class, "extPrint", new Class[] {Object.class});
		kr.exec();
	}
	
	public static void extPrint(Object o) {
		System.out.println(o + " ^_^!");
	}

}
