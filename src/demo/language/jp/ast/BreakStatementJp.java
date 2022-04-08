package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.BreakStatement;

public class BreakStatementJp extends BreakStatement {
	public BreakStatementJp(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(ブレイク)";
	}
	
}
