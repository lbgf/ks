/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.plugin.language;

import org.ks.ast.en.BreakStatement;
import org.ks.ast.en.ClassStatement;
import org.ks.ast.en.ClosureStatement;
import org.ks.ast.en.ContinueStatement;
import org.ks.ast.en.ForStatement;
import org.ks.ast.en.FuncStatement;
import org.ks.ast.en.IfStatement;
import org.ks.ast.en.ImportStatement;
import org.ks.ast.en.IncludeStatement;
import org.ks.ast.en.NewStatement;
import org.ks.ast.en.ReturnStatement;
import org.ks.ast.en.SwitchStatement;
import org.ks.ast.en.TryStatement;
import org.ks.ast.en.VarStatement;
import org.ks.ast.en.WhileStatement;

/**
 * 语言包(英文).
 *
 */
public class LanguagePkgEn extends LanguagePkg {
	
	public LanguagePkgEn() {
		this.l_class = "class";
		this.l_var = "var";
		this.l_return = "return";
		this.l_if = "if";
		this.l_else = "else";
		this.l_while = "while";
		this.l_for = "for";
		this.l_switch = "switch";
		this.l_case = "case";
		this.l_func = "function";
		this.l_extends = "extends";
		this.l_new = "new";
		this.l_try = "try";
		this.l_catch = "catch";
		this.l_finally = "finally";
		this.l_import = "import";
		this.l_include = "include";
		this.l_break = "break";
		this.l_continue = "continue";
		this.l_closure = "closure";
		
		this.l_classStatement = ClassStatement.class;
		this.l_forStatement = ForStatement.class;
		this.l_funcStatement = FuncStatement.class;
		this.l_ifStatement = IfStatement.class;
		this.l_importStatement = ImportStatement.class;
		this.l_includeStatement = IncludeStatement.class;
		this.l_newStatement = NewStatement.class;
		this.l_returnStatement = ReturnStatement.class;
		this.l_switchStatement = SwitchStatement.class;
		this.l_tryStatement = TryStatement.class;
		this.l_whileStatement = WhileStatement.class;
		this.l_varStatement = VarStatement.class;
		this.l_breakStatement = BreakStatement.class;
		this.l_continueStatement = ContinueStatement.class;
		this.l_closureStatement = ClosureStatement.class;
		
		this.l_m_print = "print";
		this.l_m_delay = "delay";
	}
	
}
