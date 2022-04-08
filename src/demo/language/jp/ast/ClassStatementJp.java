package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.ClassStatement;

public class ClassStatementJp extends ClassStatement {
	public ClassStatementJp(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		String parent = superClass();
		if (parent == null)
			parent = "*";
		return "(епеще╣ " + name() + " " + parent + " " + body() + ")";
	}

}
