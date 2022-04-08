package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.ClosureStatement;

public class ClosureStatementJp extends ClosureStatement {
	public ClosureStatementJp(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(é]æi " + parameters() + " " + body() + ")";
	}

}
