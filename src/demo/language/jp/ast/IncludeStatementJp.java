package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.IncludeStatement;

public class IncludeStatementJp extends IncludeStatement {

	public IncludeStatementJp(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(º¬¤á¤ë " + name() + ")";
	}

}
