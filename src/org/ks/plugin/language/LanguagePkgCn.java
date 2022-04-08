/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.plugin.language;

import org.ks.ast.cn.BreakStatementCn;
import org.ks.ast.cn.ClassStatementCn;
import org.ks.ast.cn.ContinueStatementCn;
import org.ks.ast.cn.ForStatementCn;
import org.ks.ast.cn.FuncStatementCn;
import org.ks.ast.cn.IfStatementCn;
import org.ks.ast.cn.ImportStatementCn;
import org.ks.ast.cn.IncludeStatementCn;
import org.ks.ast.cn.NewStatementCn;
import org.ks.ast.cn.ReturnStatementCn;
import org.ks.ast.cn.SwitchStatementCn;
import org.ks.ast.cn.TryStatementCn;
import org.ks.ast.cn.VarStatementCn;
import org.ks.ast.cn.WhileStatementCn;
import org.ks.ast.cn.ClosureStatementCn;

/**
 * 语言包(中文).
 *
 */
public class LanguagePkgCn extends LanguagePkg {
	
	public LanguagePkgCn() {
		this.l_class = "类";
		this.l_var = "变量";
		this.l_return = "返回";
		this.l_if = "如果";
		this.l_else = "否则";
		this.l_while = "迭代";
		this.l_for = "循环";
		this.l_switch = "开关";
		this.l_case = "门锁";
		this.l_func = "函数";
		this.l_extends = "扩展";
		this.l_new = "创建";
		this.l_try = "异常监控";
		this.l_catch = "捕获";
		this.l_finally = "收尾";
		this.l_import = "导入";
		this.l_include = "包含";
		this.l_break = "中断";
		this.l_continue = "继续";
		this.l_closure = "闭包";
		
		this.l_classStatement = ClassStatementCn.class;
		this.l_forStatement = ForStatementCn.class;
		this.l_funcStatement = FuncStatementCn.class;
		this.l_ifStatement = IfStatementCn.class;
		this.l_importStatement = ImportStatementCn.class;
		this.l_includeStatement = IncludeStatementCn.class;
		this.l_newStatement = NewStatementCn.class;
		this.l_returnStatement = ReturnStatementCn.class;
		this.l_switchStatement = SwitchStatementCn.class;
		this.l_tryStatement = TryStatementCn.class;
		this.l_whileStatement = WhileStatementCn.class;
		this.l_varStatement = VarStatementCn.class;
		this.l_breakStatement = BreakStatementCn.class;
		this.l_continueStatement = ContinueStatementCn.class;
		this.l_closureStatement = ClosureStatementCn.class;
		
		this.l_m_print = "打印";
		this.l_m_delay = "等待";
	}
	
}
