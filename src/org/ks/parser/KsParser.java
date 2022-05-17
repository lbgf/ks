/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.parser;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.ks.comb.KsCombinator.rule;
import org.ks.ast.ASTNode;
import org.ks.ast.Arguments;
import org.ks.ast.ArrayLiteral;
import org.ks.ast.ArrayRef;
import org.ks.ast.BinaryExpression;
import org.ks.ast.BlockStatement;
import org.ks.ast.BooleanLiteral;
import org.ks.ast.ClassBody;
import org.ks.ast.Dot;
import org.ks.ast.DoubleLiteral;
import org.ks.ast.FloatLiteral;
import org.ks.ast.IntegerLiteral;
import org.ks.ast.LongLiteral;
import org.ks.ast.Name;
import org.ks.ast.NegativeExpression;
import org.ks.ast.NotExpression;
import org.ks.ast.NullStatement;
import org.ks.ast.ParameterList;
import org.ks.ast.PrimaryExpression;
import org.ks.ast.StringLiteral;
import org.ks.ast.TernaryStatement;
import org.ks.ast.TypeStatement;
import org.ks.comb.Operators;
import org.ks.lexer.KsLexer;
import org.ks.lexer.Token;
import org.ks.plugin.bnf.SyntaxExt;
import org.ks.plugin.language.LanguagePkg;
import org.ks.comb.KsCombinator;

/**
 * 语法分析器.
 *
 */
public class KsParser {
	
	protected KsCombinator program = null;
	protected KsCombinator pe = null;
	protected KsCombinator statement = null;
	protected Operators operators = new Operators(); // 保存操作符
	protected HashSet<String> endMarks = new HashSet<String>(); // 保存结束符
	
	public KsParser(List<LanguagePkg> lps) {
		initEM();
		initBNF(lps);
		initOP();
		initEXTS();
	}

	public ASTNode parse(KsLexer lexer) throws ParseException {
		return program.parse(lexer);
	}
	
	public KsCombinator getProgram() {
		return program;
	}
	
	/**
	 * 初始化结束符
	 */
	private void initEM() {
		endMarks.add("]");
    endMarks.add(":");
    endMarks.add(";");
    endMarks.add("}");
    endMarks.add(")");
    endMarks.add(Token.EOL);
	}
	
	/**
	 * 初始化bnf范式
	 */
	private void initBNF(List<LanguagePkg> lps) {
		// 表达式
		KsCombinator expr0 = rule();
		pe = rule(PrimaryExpression.class).or(
				rule().sep("(").ast(expr0).sep(")"), 
				rule().ksInteger(IntegerLiteral.class), 
				rule().ksLong(LongLiteral.class),
				rule().ksFloat(FloatLiteral.class),
				rule().ksDouble(DoubleLiteral.class),
				rule().ksBoolean(BooleanLiteral.class),
				rule().ksString(StringLiteral.class),
				rule().identifier(Name.class, endMarks));
		KsCombinator factor = rule().or(
				rule(NegativeExpression.class).sep("-").ast(pe),
				rule(NotExpression.class).sep("!").ast(pe), 
				pe);
		KsCombinator expr = expr0.expression(BinaryExpression.class, factor, operators);
		KsCombinator simple = rule(PrimaryExpression.class).ast(expr);
		
		/*KsCombinator ternary = rule(TernaryStatement.class).sep("@").sep("(").ast(expr).sep("?").ast(expr)
				.sep(":").ast(expr).sep(")"); // 三元表达式
		pe.insertChoice(ternary);*/
		// 三元表达式
		KsCombinator ternary = rule(TernaryStatement.class).ast(expr).sep("?").ast(expr).sep(":").ast(expr);
		pe.insertChoice(rule().sep("@").maybe(ternary));
		
		// 类型
		KsCombinator type = rule(TypeStatement.class).sep(":").identifier(endMarks);
		
		// 参数
		KsCombinator param = rule().identifier(endMarks);
		param.maybe(type);
	  KsCombinator params = rule(ParameterList.class).ast(param).repeat(rule().sep(",").ast(param));
	  KsCombinator paramList = rule().sep("(").maybe(params).sep(")");
	  KsCombinator args = rule(Arguments.class).ast(expr).repeat(rule().sep(",").ast(expr));
		KsCombinator postfix = rule().sep("(").maybe(args).sep(")");
		pe.repeat(postfix);
		simple.option(args);
	  
	  // 块
	  KsCombinator statement0 = rule();
	  KsCombinator block = rule(BlockStatement.class).sep("{").option(statement0)
				.repeat(rule().sep(";", Token.EOL).option(statement0)).sep("}");
		
    // 条件、迭代
    KsCombinator[] statementArray = (KsCombinator[]) Array.newInstance(KsCombinator.class, 
    		lps.size()*5 + 1);
    KsCombinator[] elseifArray = (KsCombinator[]) Array.newInstance(KsCombinator.class, 
    		lps.size());
    KsCombinator elseif = rule();
    int[] cnt = {0};
    Stream.iterate(0, i -> i + 1).limit(lps.size()).forEach(i -> {
    	LanguagePkg e = lps.get(i);
    	KsCombinator ifelse = rule(e.l_ifStatement).sep(e.l_if).ast(expr).ast(block)
  				.option(rule().sep(e.l_else).or(block, elseif));
      Array.set(statementArray, i+cnt[0], ifelse);
      Array.set(elseifArray, i, ifelse);
      KsCombinator whileLoop = rule(e.l_whileStatement).sep(e.l_while).ast(expr).ast(block);
      Array.set(statementArray, i+(++cnt[0]), whileLoop);
      KsCombinator forLoop = rule(e.l_forStatement).sep(e.l_for).sep("(").ast(expr).sep(";")
      		.ast(expr).sep(";").ast(expr).sep(")").ast(block);
      Array.set(statementArray, i+(++cnt[0]), forLoop);
      KsCombinator switchcase = rule(e.l_switchStatement).sep(e.l_switch).ast(expr).sep("{")
  				.repeat(rule().sep(";", Token.EOL).option(rule().sep(e.l_case).ast(expr).ast(block)))
  				.sep("}");
      Array.set(statementArray, i+(++cnt[0]), switchcase);
      KsCombinator returnValue = rule(e.l_returnStatement).sep(e.l_return).ast(expr);
      Array.set(statementArray, i+(++cnt[0]), returnValue);
    });
    Array.set(statementArray, lps.size()*5, simple);
		elseif.or(elseifArray); // else if
		statement = statement0.or(statementArray);
		
		program = rule().or(statement, rule(NullStatement.class)).sep(";", Token.EOL);
		
		// 函数
		KsCombinator[] funcArray = (KsCombinator[]) Array.newInstance(KsCombinator.class, lps.size() + 1);
    Stream.iterate(0, i -> i + 1).limit(lps.size()).forEach(i -> {
    	LanguagePkg e = lps.get(i);
    	KsCombinator func = rule(e.l_funcStatement).sep(e.l_func).identifier(endMarks).ast(paramList)
	  	.maybe(type).ast(block);
      program.insertChoice(func);
      Array.set(funcArray, i, func);
    });
    Array.set(funcArray, lps.size(), simple);
	  
	  // 类
	  KsCombinator member = rule().or(funcArray);
	  KsCombinator class_body = rule(ClassBody.class).sep("{").option(member)
        .repeat(rule().sep(";", Token.EOL).option(member))
        .sep("}");
    postfix.insertChoice(rule(Dot.class).sep(".").identifier(endMarks));
    lps.stream().forEach(e -> {
    	program.insertChoice(rule(e.l_classStatement).sep(e.l_class).identifier(endMarks)
  	  		.option(rule().sep(e.l_extends).identifier(endMarks))
  	  		.ast(class_body));
	  });
	  
	  // 数组
	  KsCombinator elements = rule(ArrayLiteral.class)
	      .ast(expr).repeat(rule().sep(",").ast(expr));
	  pe.insertChoice(rule().sep("[").maybe(elements).sep("]"));
    postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
    
    // 
    lps.stream().forEach(e -> {
    	// 闭包
    	pe.insertChoice(rule(e.l_closureStatement).sep(e.l_closure).ast(paramList).ast(block));
      
    	// 变量
    	statement.insertChoice(rule(e.l_varStatement).sep(e.l_var).identifier(endMarks).maybe(type)
          .sep("=").ast(expr));

    	// new
    	pe.insertChoice(rule(e.l_newStatement).sep(e.l_new).identifier(Name.class, endMarks));

    	// 异常处理
    	statement.insertChoice(rule(e.l_tryStatement).sep(e.l_try).ast(block)
      		.sep(e.l_catch).ast(paramList).ast(block).sep(e.l_finally).ast(block));
   
    	// 导入
    	program.insertChoice(rule(e.l_importStatement).sep(e.l_import).ast(pe)
    			.sep(";", Token.EOL));
    	
    	// 引用(包含)
    	program.insertChoice(rule(e.l_includeStatement).sep(e.l_include).ast(rule()
    			.ksString(StringLiteral.class)).sep(";", Token.EOL));
    	
    	// break
    	statement.insertChoice(rule(e.l_breakStatement).sep(e.l_break).sep(";", Token.EOL));
    	
    	// continue
    	statement.insertChoice(rule(e.l_continueStatement).sep(e.l_continue).sep(";", Token.EOL));
	  });
		
	}
	
	/**
	 * 初始化操作符
	 */
	private void initOP() {
		operators.add("=", 1, Operators.RIGHT); // 右边优先
		//operators.add("++", 1, Operators.RIGHT);
		//operators.add("--", 1, Operators.RIGHT);
		operators.add("==", 2, Operators.LEFT); // 左边优先
		operators.add("!=", 2, Operators.LEFT); 
		operators.add(">", 2, Operators.LEFT);
		operators.add("<", 2, Operators.LEFT);
		operators.add("<=", 2, Operators.LEFT);
		operators.add(">=", 2, Operators.LEFT);
		operators.add("+", 3, Operators.LEFT);
		operators.add("-", 3, Operators.LEFT);
		operators.add("*", 4, Operators.LEFT);
		operators.add("/", 4, Operators.LEFT);
		operators.add("%", 4, Operators.LEFT);
		operators.add("||", 2, Operators.LEFT); 
		operators.add("&&", 2, Operators.LEFT); 
		operators.add("<=>", 2, Operators.LEFT); 
	}
	
	/**
	 * 初始化扩展语法
	 */
	private void initEXTS() {
		SyntaxExt.getProgramExts().stream().forEach(comb -> {
			program.insertChoice(comb);
		});
		SyntaxExt.getPeExts().stream().forEach(comb -> {
			pe.insertChoice(comb);
		});
		SyntaxExt.getStatementExts().stream().forEach(comb -> {
			statement.insertChoice(comb);
		});
	}

}
