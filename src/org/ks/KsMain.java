/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
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
 * ����������.
 *
 */
public class KsMain {

	public static void main(String[] args) throws Exception {
		try {
			if (args.length < 1) {
				System.out.println("ȱ�ٲ�������ָ��ks�ű�·�����磺ks �ű��ļ�·��");
			} else if (args.length >= 1 && args[0].equals("-h")) {
				System.out.println("���������� ks �ű��ļ� [����] ��ִ��ks�ű������в�����ѡ");
				System.out.println("����˵����");
				System.out.println("-bc ʹ��bcģʽִ�У�Ĭ��ʹ��dvģʽ");
				System.out.println("-p ��ʾ��ϸִ�й���");
				System.out.println("-v �ɲ鿴��ǰ�汾��Ϣ");
				System.out.println("-h �ɲ鿴������Ϣ");
				System.out.println("-a �ɲ鿴�������ɵ��﷨��");
				System.out.println("-sc ������·�����ɲ鿴�������ɵ�java��");
			} else if (args.length >= 1 && args[0].equals("-v")) {
				System.out.println(SysUtil.CN + "(" + SysUtil.EN + ")" + SysUtil.VERSION + "��"); // kid script
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
			System.out.println("ִ�г���" + e.getMessage() + "����ʹ��ks -h�鿴���������Ϣ");
		}
	}

}
