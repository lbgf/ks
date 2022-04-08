/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.plugin.bnf;

import java.util.ArrayList;
import java.util.List;

import org.ks.comb.KsCombinator;

/**
 * 语法扩展.
 *
 */
public class SyntaxExt {
	public final static int PROGRAM = 0;
	public final static int PE = 1;
	public final static int STATEMENT = 2;
	
	protected static List<KsCombinator> PROGRAM_EXTS = new ArrayList<KsCombinator>();
	protected static List<KsCombinator> PE_EXTS = new ArrayList<KsCombinator>();
	protected static List<KsCombinator> STATEMENT_EXTS = new ArrayList<KsCombinator>();
	
	public static void add(KsCombinator comb, int type) {
		switch(type) {
			case PROGRAM:
				PROGRAM_EXTS.add(comb);
				break;
			case PE:
				PE_EXTS.add(comb);
				break;
			case STATEMENT:
				STATEMENT_EXTS.add(comb);
				break;
		}
	}
	
	public static List<KsCombinator> getProgramExts() {
		return PROGRAM_EXTS;
	}
	
	public static List<KsCombinator> getPeExts() {
		return PE_EXTS;
	}
	
	public static List<KsCombinator> getStatementExts() {
		return STATEMENT_EXTS;
	}
	
}
