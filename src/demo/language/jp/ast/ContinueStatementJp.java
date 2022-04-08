package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.ContinueStatement;

public class ContinueStatementJp extends ContinueStatement {
	public ContinueStatementJp(List<ASTNode> c) {
		super(c);
	}
	
	@Override
	public String toString() {
		return "(¾AÐÐ)";
	}
	
}
