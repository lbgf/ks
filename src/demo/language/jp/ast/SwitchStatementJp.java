package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.SwitchStatement;

public class SwitchStatementJp extends SwitchStatement {
	public SwitchStatementJp(List<ASTNode> c) {
		super(c);
	}

	@Override
	public String toString() {
		String tmp = "(�����å� " + key();
		for (int i = 1; i < numChildren(); i++) {
			tmp += " ���� " + child(i) ;
		}
		return tmp;
	}
	
}
