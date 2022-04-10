package demo.extfunc;

import java.io.File;

import org.ks.core.KsRunner;
import org.ks.util.KsDebug;


public class TestExtFunc {

	public static void main(String[] args) throws Exception {

		File f = new File(System.getProperty("user.dir") + "\\src\\demo\\extfunc\\demo.ks");
		
		KsDebug.showProcess = false;
		
		KsRunner kr = new KsRunner(f, null);
		kr.appendNative("extPrint", TestExtFunc.class, "extPrint", new Class[] {Object.class});
		kr.exec();
	}
	
	public static void extPrint(Object o) {
		System.out.println(o + " ^_^!");
	}

}
