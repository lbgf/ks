/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.ks.ast.ASTNode;
import org.ks.ast.NullStatement;
import org.ks.bc.BcOpcodes;
import org.ks.natives.KsNatives;
import org.ks.lexer.KsLexer;
import org.ks.parser.KsParser;
import org.ks.plugin.language.LanguagePkg;
import org.ks.plugin.language.LanguagePkgCn;
import org.ks.plugin.language.LanguagePkgEn;
import org.ks.lexer.Token;
import org.ks.runtime.Environment;
import org.ks.runtime.KsEnvironment;
import org.ks.util.KsDebug;

/**
 * ks脚本运行器.
 *
 */
public class KsRunner {

	public byte RUN_WAY = -1;
	public static List<LanguagePkg> GLPS;
	
	private KsEnvironment ksEnv;
	private String code;
	private List<LanguagePkg> lps;
	
	private BcOpcodes bcOp;
	private String scriptName;
	private String saveClassPath;
	private KsNatives kn;

	/**
	 * 构造函数.
	 * 
	 * @param code String: 代码.
	 * @param lps List<LanguagePkg>: 语言包.
	 */
	public KsRunner(String code, List<LanguagePkg> lps) {
		initRunner(code, lps, Constant.DV);
		this.scriptName = String.valueOf(ThreadLocalRandom.current().nextLong(10000));
	}
	
	/**
	 * 构造函数.
	 * 
	 * @param f File: 脚本文件.
	 * @param lps List<LanguagePkg>: 语言包.
	 */
	public KsRunner(File f, List<LanguagePkg> lps) throws Exception {
		LineNumberReader r = new LineNumberReader(new FileReader(f));
		String scriptName = f.getName().split("[.]")[f.getName().split("[.]").length - 2];
		String str = null;
		String code = "";
    while ((str = r.readLine()) != null) {
    	code += str + "\n";
    }
    r.close();
		initRunner(code, lps, Constant.DV);
		this.scriptName = scriptName;
		ksEnv.setScriptRootPath(f.getParent());
	}
	
	/**
	 * 构造函数.
	 * 
	 * @param code String: 代码.
	 * @param lps List<LanguagePkg>: 语言包.
	 * @param scriptName String: 脚本名称.
	 * @param saveClassPath String: 把生成的字节码保存到文件（null时不保存）.
	 */
	public KsRunner(String code, List<LanguagePkg> lps, String scriptName, String saveClassPath) {
		initRunner(code, lps, Constant.BC);
		this.scriptName = scriptName;
		this.saveClassPath = saveClassPath;
		ksEnv.setScriptName(this.scriptName);
		ksEnv.setSaveClassPath(this.saveClassPath);
	}
	
	/**
	 * 构造函数.
	 * 
	 * @param f File: 脚本文件.
	 * @param lps List<LanguagePkg>: 语言包.
	 * @param scriptName String: 脚本名称.
	 * @param saveClassPath String: 把生成的字节码保存到文件（null时不保存）.
	 */
	public KsRunner(File f, List<LanguagePkg> lps, String saveClassPath) throws Exception  {
		LineNumberReader r = new LineNumberReader(new FileReader(f));
		String scriptName = f.getName().split("[.]")[f.getName().split("[.]").length - 2];
		String str = null;
		String code = "";
    while ((str = r.readLine()) != null) {
    	code += str + "\n";
    }
    r.close();
		
		initRunner(code, lps, Constant.BC);
		this.scriptName = scriptName;
		this.saveClassPath = saveClassPath;
		ksEnv.setScriptName(this.scriptName);
		ksEnv.setSaveClassPath(this.saveClassPath);
		ksEnv.setScriptRootPath(f.getParent());
	}
	
	/**
	 * 构造函数.
	 * 
	 * @param code String: 代码.
	 * @param lps List<LanguagePkg>: 语言包.
	 * @param runWay byte: 运行方式.
	 */
	public KsRunner(String code, List<LanguagePkg> lps, byte runWay) {
		initRunner(code, lps, runWay);
	}
	
	/**
	 * 初始化运行器.
	 * 
	 * @param code String: 代码.
	 * @param lps List<LanguagePkg>: 语言包.
	 * @param runWay byte: 运行方式.
	 */
	protected void initRunner(String code, List<LanguagePkg> lps, byte runWay) {
		RUN_WAY = runWay;

		this.code = code;
		ksEnv = new KsEnvironment();
		if (runWay == Constant.BC) {
			ksEnv.initBcFristEnv();
			ksEnv.setKsClassLoader(new KsClassLoader());
			ksEnv.initFrameObjs();
		} else {
			ksEnv.initFristEnv();
		}
		ksEnv.initVarIndex(); // 用于BC
		
		if(lps == null) {
			lps = new ArrayList<LanguagePkg>();
			lps.add(new LanguagePkgEn());
			lps.add(new LanguagePkgCn());
		}
		this.lps = lps;
		KsRunner.GLPS = lps;
		
		kn = new KsNatives(lps);
		
		setVariable("null", Constant.getNullObject());
		setVariable("true", Constant.getTrue());
		setVariable("false", Constant.getFalse());
		setVariable("err", Constant.getNullObject());
		setVariable("default", -1);
	}
	
	/**
	 * 获取环境.
	 * 
	 * @return
	 */
	public Environment getEnvironment() {
		return ksEnv;
	}
		
	/**
	 * 设置变量.
	 * 
	 * @param name String: 名称.
	 * @param value Object: 值.
	 */
	public void setVariable(String name, Object value) {
		ksEnv.put(name, value);
		if (RUN_WAY == Constant.BC) {
			ksEnv.putBcValue(name, value);
		}
	}
	
	/**
	 * 获取变量.
	 * 
	 * @param name String: 名称.
	 */
	public Object getVariable(String name) {
		if (RUN_WAY == Constant.BC) {
			return ksEnv.getBcValue(name);
		} else {
			return ksEnv.get(name);
		}
	}
	
	/**
	 * 加入扩展方法.
	 * 
	 * @param name
	 * @param clazz
	 * @param methodName
	 * @param params
	 */
	public void appendNative(String name, Class<?> clazz, String methodName, Class<?>... params) {
		kn.append(ksEnv, name, clazz, methodName, params);
	}
	
	/**
	 * 执行.
	 * 
	 * @return
	 */
	public Object exec() throws Exception {
		if (KsDebug.showProcess) {
			System.out.println("--------------------代码分词--------------------------");
			KsLexer l = new KsLexer(code);
			for (Token t; (t = l.read()) != Token.EOF;) {
				System.out.println("token: " + t.getText() + " : " + t.getClass().getSimpleName());
			}
			System.out.println("--------------------生成语法树--------------------------");
			l = new KsLexer(code);
			KsParser kp = new KsParser(lps);
	    while (l.peek(0) != Token.EOF) {
	      ASTNode ast = kp.parse(l);
	      System.out.println("节点：" + ast.toString());
	    }
	    System.out.println("--------------------执行语法树--------------------------");
		}
		KsLexer l = new KsLexer(code);
		KsParser bp = new KsParser(lps);
		
    Environment env = kn.environment(ksEnv);
    
    Object rr = null;
    
    if (RUN_WAY == Constant.DV) {
			while (l.peek(0) != Token.EOF) {
				ASTNode t = bp.parse(l);
				if (!(t instanceof NullStatement)) {
					t.lookup(env.symbols());
					rr = t.eval(env);
				}
			}
    } else if (RUN_WAY == Constant.BC) {
    	bcOp = new BcOpcodes();
    	String className = "Script" + scriptName;
    	bcOp.createClass(className);
    	
    	// 环境属性
    	// bcOp.createField(Opcodes.ACC_PRIVATE, "env", "Lorg/ks/runtime/Environment;", null, null);
    	
    	// 构造函数
    	bcOp.createConstructor(BcOpcodes.ACC_PUBLIC, "Lorg/ks/runtime/Environment;");
    	bcOp.gcMethod().visitParameter("env", 0);
    	bcOp.gcMethod().visitCode();
    	bcOp.getThis();
    	bcOp.invokeSpecial("org/ks/bc/ScriptBase", "<init>", null, "V", false);
    	bcOp.getThis();
    	bcOp.gcMethod().visitVarInsn(BcOpcodes.ALOAD, 1);
    	bcOp.setField("org/ks/bc/ScriptBase", "env", "Lorg/ks/runtime/Environment;");
    	bcOp.returnVoid();
    	bcOp.gcMethod().visitLocalVariable("this", "L"+className+";", null, bcOp.createLabel(), bcOp.createLabel(), 0);
    	bcOp.gcMethod().visitLocalVariable("env", "Lorg/ks/runtime/Environment;", null, bcOp.createLabel(), bcOp.createLabel(), 1);
    	bcOp.endConstructor();
    	
    	// run方法
    	bcOp.createMethod(BcOpcodes.ACC_PUBLIC, "run", null, "Ljava/lang/Object;");
    	bcOp.gcMethod().visitCode();
    	// bcOp.gcMethod().visitVarInsn(Opcodes.ALOAD, 0); // // 这句代码引起性能问题，循环的时候很慢
    	// bcOp.gcMethod().visitParameter("evn", 0);
    	boolean hasReturn = false;
    	while (l.peek(0) != Token.EOF) {
				ASTNode t = bp.parse(l);
				if (!(t instanceof NullStatement)) {
					t.lookup(env.symbols());
					t.compile(env, bcOp);
					if (checkReturn(t)) {
						hasReturn = true;
					}
				}
			}
    	
    	if(!hasReturn) {
	    	bcOp.gcMethod().visitTypeInsn(BcOpcodes.NEW, "java/lang/Object");
	    	bcOp.gcMethod().visitInsn(BcOpcodes.DUP);
	    	bcOp.invokeSpecial("java/lang/Object", "<init>", null, "V", false);
	     	bcOp.returnObject();
    	}
     	bcOp.gcMethod().visitLocalVariable("this", "L"+className+";", null, bcOp.createLabel(), bcOp.createLabel(), 0);
    	bcOp.endMethod();
    	bcOp.endClass();
    	
    	// 获取字节码
      byte[] code = bcOp.getByteCode();
      
      if (saveClassPath != null) {
	      FileOutputStream fos = new FileOutputStream(saveClassPath+className+".class");
	      //写文件
	      fos.write(code);
	      //关闭输出流
	      fos.close();
	    }
      
      KsClassLoader loader = env.getKsClassLoader();
      Class<?> clazz = loader.createClass(className, code, 0, code.length);

      rr = clazz.getMethods()[0].invoke(clazz.getConstructor(Environment.class).newInstance(env));
    	
    }
		
		return rr;
	}
	
	protected boolean checkReturn(ASTNode t) {
		boolean isReturn = false;
  	for(LanguagePkg e: GLPS) {
  		if (t.getClass().isAssignableFrom(e.l_returnStatement)) {
  			isReturn = true;
  			break;
  		}
  	}
  	return isReturn;
	}

}
