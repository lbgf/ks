package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.FuncStatement;

public class FuncStatementJp extends FuncStatement {
	public FuncStatementJp(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		return "(�C�� " + name() + " " + parameters() + " " + type() + " " + body() + ")";
	}
	
	
}
