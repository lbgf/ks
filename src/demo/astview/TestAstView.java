
package demo.astview;

import java.io.File;

import org.ks.tools.AstView;

public class TestAstView {
	
	public static void main(String[] args) throws Exception {

		File f = new File(System.getProperty("user.dir") + "\\src\\demo\\astview\\demo.ks");
		 
		AstView av = new AstView(f, null);
		av.show();
	}

}
