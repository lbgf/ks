package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.ForStatement;

public class ForStatementJp extends ForStatement {
	public ForStatementJp(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(¥ë©`¥× " + initializer() + " " + condition() + " " + incremental() + " " + body() + ")";
	}
	
}
