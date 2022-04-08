package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.ReturnStatement;

public class ReturnStatementJp extends ReturnStatement {
	public ReturnStatementJp(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(С°ды " + result() + ")";
	}
	
}
