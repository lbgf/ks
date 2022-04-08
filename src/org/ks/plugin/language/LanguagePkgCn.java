/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
 * ���԰�(����).
 *
 */
public class LanguagePkgCn extends LanguagePkg {
	
	public LanguagePkgCn() {
		this.l_class = "��";
		this.l_var = "����";
		this.l_return = "����";
		this.l_if = "���";
		this.l_else = "����";
		this.l_while = "����";
		this.l_for = "ѭ��";
		this.l_switch = "����";
		this.l_case = "����";
		this.l_func = "����";
		this.l_extends = "��չ";
		this.l_new = "����";
		this.l_try = "�쳣���";
		this.l_catch = "����";
		this.l_finally = "��β";
		this.l_import = "����";
		this.l_include = "����";
		this.l_break = "�ж�";
		this.l_continue = "����";
		this.l_closure = "�հ�";
		
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
		
		this.l_m_print = "��ӡ";
		this.l_m_delay = "�ȴ�";
	}
	
}
