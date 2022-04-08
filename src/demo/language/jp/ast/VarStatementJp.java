package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.VarStatement;

public class VarStatementJp extends VarStatement {
	
	public VarStatementJp(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(‰äÊý " + name() + " " + type() + " " + initializer() + ")";
	}

}
