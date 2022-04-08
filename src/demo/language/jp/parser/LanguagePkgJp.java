package demo.language.jp.parser;


import org.ks.plugin.language.LanguagePkg;

import demo.language.jp.ast.*;

public class LanguagePkgJp extends LanguagePkg {
	
	public LanguagePkgJp() {
		this.l_class = "���饹";
		this.l_var = "����";
		this.l_return = "����";
		this.l_if = "�⤷";
		this.l_else = "������";
		this.l_while = "����";
		this.l_for = "��`��";
		this.l_switch = "�����å�";
		this.l_case = "����";
		this.l_func = "�C��";
		this.l_extends = "����";
		this.l_new = "�¤���";
		this.l_try = "�����Oҕ";
		this.l_catch = "���館��";
		this.l_finally = "�����";
		this.l_import = "����";
		this.l_include = "�����";
		this.l_break = "�֥쥤��";
		this.l_continue = "�A��";
		this.l_closure = "�]�i";
		
		this.l_classStatement = ClassStatementJp.class;
		this.l_forStatement = ForStatementJp.class;
		this.l_funcStatement = FuncStatementJp.class;
		this.l_ifStatement = IfStatementJp.class;
		this.l_importStatement = ImportStatementJp.class;
		this.l_includeStatement = IncludeStatementJp.class;
		this.l_newStatement = NewStatementJp.class;
		this.l_returnStatement = ReturnStatementJp.class;
		this.l_switchStatement = SwitchStatementJp.class;
		this.l_tryStatement = TryStatementJp.class;
		this.l_whileStatement = WhileStatementJp.class;
		this.l_varStatement = VarStatementJp.class;
		this.l_breakStatement = BreakStatementJp.class;
		this.l_continueStatement = ContinueStatementJp.class;
		this.l_closureStatement = ClosureStatementJp.class;
		
		this.l_m_print = "ӡˢ";
		this.l_m_delay = "�W��";
	}
	
}
