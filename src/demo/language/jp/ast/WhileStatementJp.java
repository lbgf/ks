package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.WhileStatement;

public class WhileStatementJp extends WhileStatement {
	public WhileStatementJp(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(∑¥èÕ " + condition() + " " + body() + ")";
	}
	
}
