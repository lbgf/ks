package demo.perf;

import java.io.File;

import org.ks.core.KsRunner;
import org.ks.util.KsDebug;

public class PerfDV {

	public static void main(String[] args) throws Exception {

		File f = new File(System.getProperty("user.dir") + "\\src\\demo\\perf\\dv.ks");
		
		KsDebug.showProcess = false;
		KsRunner kr = new KsRunner(f, null);
		kr.exec();
	}

}
