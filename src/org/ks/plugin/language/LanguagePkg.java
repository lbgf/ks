/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.plugin.language;

import org.ks.ast.ASTNode;

/**
 * 语言包.
 *
 */
public class LanguagePkg {
	
	public String l_class;
	public String l_var;
	public String l_return;
	public String l_if;
	public String l_else;
	public String l_while;
	public String l_for;
	public String l_switch;
	public String l_case;
	public String l_func;
	public String l_extends;
	public String l_new;
	public String l_try;
	public String l_catch;
	public String l_finally;
	public String l_import;
	public String l_include;
	public String l_break;
	public String l_continue;
	public String l_closure;
	
	public Class<? extends ASTNode> l_classStatement;
	public Class<? extends ASTNode>  l_forStatement;
	public Class<? extends ASTNode>  l_funcStatement;
	public Class<? extends ASTNode>  l_ifStatement;
	public Class<? extends ASTNode>  l_importStatement;
	public Class<? extends ASTNode>  l_includeStatement;
	public Class<? extends ASTNode>  l_newStatement;
	public Class<? extends ASTNode>  l_returnStatement;
	public Class<? extends ASTNode>  l_switchStatement;
	public Class<? extends ASTNode>  l_tryStatement;
	public Class<? extends ASTNode>  l_whileStatement;
	public Class<? extends ASTNode>  l_varStatement;
	public Class<? extends ASTNode>  l_breakStatement;
	public Class<? extends ASTNode>  l_continueStatement;
	public Class<? extends ASTNode>  l_closureStatement;
	
	public String l_m_print;
	public String l_m_delay;
	
}
