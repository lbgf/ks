package demo.dv;

import java.io.File;

import org.ks.core.KsRunner;
import org.ks.util.KsDebug;

public class TestDV {

	public static void main(String[] args) throws Exception {

		File f = new File(System.getProperty("user.dir") + "\\src\\demo\\dv\\demo.ks");
		
		KsDebug.showProcess = false;
		KsRunner kr = new KsRunner(f, null);
		// kr.setVariable("a", "aaa");
		kr.exec();
	}

}
