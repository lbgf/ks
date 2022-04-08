package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.TryStatement;

public class TryStatementJp extends TryStatement {
	
	public TryStatementJp(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(�����Oҕ " + tryBlock() + " ���館�� " + catchVar() + catchBlock() + " ����� " + finallyBlock() + ")";
	}

}
