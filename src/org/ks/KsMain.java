/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks;

import java.io.File;

import org.ks.core.Constant;
import org.ks.core.KsRunner;
import org.ks.tools.AstView;
import org.ks.util.KsDebug;
import org.ks.util.SysUtil;

/**
 * 命令行主类.
 *
 */
public class KsMain {

	public static void main(String[] args) throws Exception {
		try {
			if (args.length < 1) {
				System.out.println("缺少参数，请指定ks脚本路径！如：ks 脚本文件路径");
			} else if (args.length >= 1 && args[0].equals("-h")) {
				System.out.println("命令行输入 ks 脚本文件 [参数] 可执行ks脚本，其中参数可选");
				System.out.println("参数说明：");
				System.out.println("-bc 使用bc模式执行，默认使用dv模式");
				System.out.println("-p 显示详细执行过程");
				System.out.println("-v 可查看当前版本信息");
				System.out.println("-h 可查看帮助信息");
				System.out.println("-a 可查看代码生成的语法树");
				System.out.println("-sc 保存类路径，可查看代码生成的java类");
			} else if (args.length >= 1 && args[0].equals("-v")) {
				System.out.println(SysUtil.CN + "(" + SysUtil.EN + ")" + SysUtil.VERSION + "版"); // kid script
			} else {
				File f = new File(args[0]);
		    
		    int mode = Constant.DV;
		    String savePath = null;
		    for(int i = 1; i < args.length; i++) {
		    	if (args[i].equals("-p")) {
			    	KsDebug.showProcess = true;
			    } else if (args[i].equals("-a")) {
			    	KsDebug.showAst = true;
			    } else if (args[i].equals("-bc")) {
			    	mode = Constant.BC;
			    } else if (args[i].equals("-sc")) {
			    	mode = Constant.BC;
			    	savePath = args[i+1];
			    }
		    }
		    
		    if (KsDebug.showAst) {
		    	AstView av = new AstView(f, null);
		  		av.show();
		    } else {
		    	KsRunner kr = null;
		    	if (mode == Constant.BC) {
		    		kr = new KsRunner(f, null, savePath);
		    	} else {
		    		kr = new KsRunner(f, null);
		    	}
			    kr.exec();
		    }
			}
		} catch(Exception e) {
			System.out.println("执行出错：" + e.getMessage() + "；请使用ks -h查看更多帮助信息");
		}
	}

}
