package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.TryStatement;

public class TryStatementJp extends TryStatement {
	
	public TryStatementJp(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(Æê≥£±O“ï " + tryBlock() + " ≤∂§È§®§Î " + catchVar() + catchBlock() + " ◊Ó··§À " + finallyBlock() + ")";
	}

}
