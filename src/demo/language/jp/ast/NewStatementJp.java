package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.NewStatement;

public class NewStatementJp extends NewStatement {
	
	public NewStatementJp(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(ÐÂ¤·¤¤ " + name() + " " + ")";
	}

}
