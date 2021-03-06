/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast.en;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.NullStatement;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.core.KsRunner;
import org.ks.lexer.KsLexer;
import org.ks.parser.KsParser;
import org.ks.lexer.Token;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;

/**
 * 引用其他脚本节点.
 *
 */
public class IncludeStatement extends ASTList {

	protected int index;

	public IncludeStatement(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		String name = "";
		name = ((ASTLeaf)child(0)).token().getText();
		return name;
	}

	public String toString() {
		return "(include " + name() + ")";
	}

	public void lookup(Symbols syms) {
		
	}

	public Object eval(Environment env) {
		
		String name = name();
		String path = "";

		if (env.getScriptRootPath() != null) {
			path = env.getScriptRootPath();
		} else {
			path = new File("").getAbsolutePath();
		}
		
		// 执行外部脚本
		Object rr = null;
		try {
			LineNumberReader r = new LineNumberReader(new FileReader(path + "/" + name));
			String str = null;
			String code = "";
	    while ((str = r.readLine()) != null) {
	    	code += str + "\n";
	    }
	    r.close();
			KsLexer l = new KsLexer(code);
			KsParser kp = new KsParser(KsRunner.GLPS);    
			while (l.peek(0) != Token.EOF) {
				ASTNode t = kp.parse(l);
				if (!(t instanceof NullStatement)) {
					t.lookup(env.symbols());
					rr = t.eval(env);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new KsException("引用脚本(" + name + ")发生错误：" + e.getMessage());
		}
		
		return null;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Include"); // test
		
		String name = name();
		String path = "";

		if (env.getScriptRootPath() != null) {
			path = env.getScriptRootPath();
		} else {
			path = new File("").getAbsolutePath();
		}
		
		// 执行外部脚本
		try {
			LineNumberReader r = new LineNumberReader(new FileReader(path + "/" + name));
			String str = null;
			String code = "";
	    while ((str = r.readLine()) != null) {
	    	code += str + "\n";
	    }
	    r.close();
			KsLexer l = new KsLexer(code);
			KsParser bp = new KsParser(KsRunner.GLPS);    
			
			while (l.peek(0) != Token.EOF) {
				ASTNode t = bp.parse(l);
				if (!(t instanceof NullStatement)) {
					t.lookup(env.symbols());
					t.compile(env, bcOp);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw new KsException("引用脚本(" + name + ")发生错误：" + e.getMessage());
		}
		
		return null;
	}

}
