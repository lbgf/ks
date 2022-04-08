
package demo.astview;

import java.io.FileReader;
import java.io.LineNumberReader;

import org.ks.tools.AstView;

public class TestAstView {
	
	public static void main(String[] args) throws Exception {

		LineNumberReader r = new LineNumberReader(new FileReader(System.getProperty("user.dir") + "\\src\\demo\\astview\\demo.ks"));

		String str = null;
		String code = "";
		while ((str = r.readLine()) != null) {
			code += str + "\n";
		}
		r.close();
		 
		AstView av = new AstView(code, null);
		av.show();
	}

}
