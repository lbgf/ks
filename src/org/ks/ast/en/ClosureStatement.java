/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast.en;

import java.util.List;

import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.BlockStatement;
import org.ks.ast.KsFunction;
import org.ks.ast.ParameterList;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;

/**
 * �հ�.
 *
 */
public class ClosureStatement extends ASTList {
	
	protected int size = -1;
	
	public ClosureStatement(List<ASTNode> c) {
		super(c);
	}

	public ParameterList parameters() {
		return (ParameterList) child(0);
	}

	public BlockStatement body() {
		return (BlockStatement) child(1);
	}

	public String toString() {
		return "(closure " + parameters() + " " + body() + ")";
	}
	
	public void lookup(Symbols syms) {
    size = lookup(syms, parameters(), body());
	}
	
	public Object eval(Environment env) {
    return new KsFunction(parameters(), body(), env, size);
	}
	
	public static int lookup(Symbols syms, ParameterList params,
                         BlockStatement body)	{
    Symbols newSyms = new Symbols(syms);
    newSyms.initVarIndex(); // ����BCģʽ
    params.lookup(newSyms);
    body.lookup(newSyms);
    return newSyms.size();
	}
}
