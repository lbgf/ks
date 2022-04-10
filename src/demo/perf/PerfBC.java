package demo.perf;

import java.io.File;

import org.ks.core.KsRunner;
import org.ks.util.KsDebug;

public class PerfBC {

	public static void main(String[] args) throws Exception {

		File f = new File(System.getProperty("user.dir") + "\\src\\demo\\perf\\bc.ks");
		
		KsDebug.showProcess = false;
		
		KsRunner kr = new KsRunner(f, null, System.getProperty("user.dir") + "\\bin\\");
		kr.exec();
	}

}
