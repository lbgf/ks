package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.IfStatement;

public class IfStatementJp extends IfStatement {
	public IfStatementJp(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(もし " + condition() + " " + thenBlock() + " その麿 " + elseBlock() + ")";
	}
	
}
