package demo.language.jp.parser;


import org.ks.plugin.language.LanguagePkg;

import demo.language.jp.ast.*;

public class LanguagePkgJp extends LanguagePkg {
	
	public LanguagePkgJp() {
		this.l_class = "クラス";
		this.l_var = "変数";
		this.l_return = "戻る";
		this.l_if = "もし";
		this.l_else = "その他";
		this.l_while = "反復";
		this.l_for = "ループ";
		this.l_switch = "スイッチ";
		this.l_case = "場合";
		this.l_func = "機能";
		this.l_extends = "拡張";
		this.l_new = "新しい";
		this.l_try = "異常監視";
		this.l_catch = "捕らえる";
		this.l_finally = "最後に";
		this.l_import = "導入";
		this.l_include = "含める";
		this.l_break = "ブレイク";
		this.l_continue = "続行";
		this.l_closure = "閉鎖";
		
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
		
		this.l_m_print = "印刷";
		this.l_m_delay = "遅延";
	}
	
}
