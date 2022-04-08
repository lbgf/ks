package demo.language.jp.ast;

import java.util.List;

import org.ks.ast.ASTNode;
import org.ks.ast.en.ImportStatement;

public class ImportStatementJp extends ImportStatement {

	public ImportStatementJp(List<ASTNode> c) {
		super(c);
	}

	public String toString() {
		return "(åß»Î " + name() + ")";
	}

}
