/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.tools;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTNode;
import org.ks.ast.Arguments;
import org.ks.ast.ArrayLiteral;
import org.ks.ast.ArrayRef;
import org.ks.ast.BinaryExpression;
import org.ks.ast.BlockStatement;
import org.ks.ast.ClassBody;
import org.ks.ast.Dot;
import org.ks.ast.Name;
import org.ks.ast.NegativeExpression;
import org.ks.ast.ParameterList;
import org.ks.ast.Postfix;
import org.ks.ast.PrimaryExpression;
import org.ks.ast.TernaryStatement;
import org.ks.lexer.KsLexer;
import org.ks.lexer.Token;
import org.ks.parser.KsParser;
import org.ks.plugin.language.LanguagePkg;
import org.ks.plugin.language.LanguagePkgCn;
import org.ks.plugin.language.LanguagePkgEn;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AstView {

	protected String version = "v0.5.1";
	protected String code = "";
	protected KsLexer kl = null;
	protected KsParser kp = null;
	public static List<LanguagePkg> GLPS;

	/**
	 * 构造函数.
	 * 
	 * @param code String: 代码.
	 * @param lps List<LanguagePkg>: 语言包.
	 */
	public AstView(String code, List<LanguagePkg> lps) {
		this.code = code;
		if (lps == null) {
			lps = new ArrayList<LanguagePkg>();
			lps.add(new LanguagePkgEn());
			lps.add(new LanguagePkgCn());
		}
		this.GLPS = lps;
		kl = new KsLexer(code);
		kp = new KsParser(lps);
	}
	
	/**
	 * 构造函数.
	 * 
	 * @param f File: 脚本文件.
	 * @param lps List<LanguagePkg>: 语言包.
	 */
	public AstView(File f, List<LanguagePkg> lps) throws Exception {
		LineNumberReader r = new LineNumberReader(new FileReader(f));
		String str = null;
		String code = "";
    while ((str = r.readLine()) != null) {
    	code += str + "\n";
    }
    r.close();
		this.code = code;
		if (lps == null) {
			lps = new ArrayList<LanguagePkg>();
			lps.add(new LanguagePkgEn());
			lps.add(new LanguagePkgCn());
		}
		this.GLPS = lps;
		kl = new KsLexer(code);
		kp = new KsParser(lps);
	}

	protected boolean check(ASTNode t, int no) {
		boolean isFlag = false;
		
		for (LanguagePkg e : GLPS) {
			if (no == 1 && t.getClass().isAssignableFrom(e.l_classStatement)) {
				isFlag = true;
				break;
			} else if (no == 15 && t.getClass().isAssignableFrom(e.l_forStatement)) {
				isFlag = true;
				break;
			} else if (no == 2 && t.getClass().isAssignableFrom(e.l_funcStatement)) {
				isFlag = true;
				break;
			} else if (no == 3 && t.getClass().isAssignableFrom(e.l_ifStatement)) {
				isFlag = true;
				break;
			} else if (no == 4 && t.getClass().isAssignableFrom(e.l_importStatement)) {
				isFlag = true;
				break;
			} else if (no == 5 && t.getClass().isAssignableFrom(e.l_includeStatement)) {
				isFlag = true;
				break;
			} else if (no == 6 && t.getClass().isAssignableFrom(e.l_newStatement)) {
				isFlag = true;
				break;
			} else if (no == 7 && t.getClass().isAssignableFrom(e.l_returnStatement)) {
				isFlag = true;
				break;
			} else if (no == 8 && t.getClass().isAssignableFrom(e.l_switchStatement)) {
				isFlag = true;
				break;
			} else if (no == 9 && t.getClass().isAssignableFrom(e.l_tryStatement)) {
				isFlag = true;
				break;
			} else if (no == 10 && t.getClass().isAssignableFrom(e.l_whileStatement)) {
				isFlag = true;
				break;
			} else if (no == 11 && t.getClass().isAssignableFrom(e.l_varStatement)) {
				isFlag = true;
				break;
			} else if (no == 12 && t.getClass().isAssignableFrom(e.l_breakStatement)) {
				isFlag = true;
				break;
			} else if (no == 13 && t.getClass().isAssignableFrom(e.l_continueStatement)) {
				isFlag = true;
				break;
			} else if (no == 14 && t.getClass().isAssignableFrom(e.l_closureStatement)) {
				isFlag = true;
				break;
			}
		}
		return isFlag;
	}

	public void visitNode(DefaultMutableTreeNode tree, ASTNode node) {

		DefaultMutableTreeNode newNode = null;

		if (tree == null)
			return;

		// System.out.println(node.getClass());

		if (node.getClass().getName().equals("org.ks.ast.ASTList")) {
			newNode = new DefaultMutableTreeNode("param");
			tree.add(newNode);
		} else if (check(node, 1)) {
			newNode = new DefaultMutableTreeNode("class");
			tree.add(newNode);
		} else if (check(node, 2)) {
			newNode = new DefaultMutableTreeNode("function");
			tree.add(newNode);
		} else if (check(node, 3)) {
			newNode = new DefaultMutableTreeNode("if");
			tree.add(newNode);
		} else if (check(node, 4)) {
			newNode = new DefaultMutableTreeNode("import");
			tree.add(newNode);
		} else if (check(node, 5)) {
			newNode = new DefaultMutableTreeNode("include");
			tree.add(newNode);
		} else if (check(node, 6)) {
			newNode = new DefaultMutableTreeNode("new");
			tree.add(newNode);
		} else if (check(node, 7)) {
			newNode = new DefaultMutableTreeNode("return");
			tree.add(newNode);
		} else if (check(node, 8)) {
			newNode = new DefaultMutableTreeNode("switch");
			tree.add(newNode);
		} else if (check(node, 9)) {
			newNode = new DefaultMutableTreeNode("try");
			tree.add(newNode);
		} else if (check(node, 10)) {
			newNode = new DefaultMutableTreeNode("while");
			tree.add(newNode);
		} else if (check(node, 11)) {
			newNode = new DefaultMutableTreeNode("var");
			tree.add(newNode);
		}  else if (check(node, 12)) {
			newNode = new DefaultMutableTreeNode("break");
			tree.add(newNode);
		}  else if (check(node, 13)) {
			newNode = new DefaultMutableTreeNode("continue");
			tree.add(newNode);
		}  else if (check(node, 14)) {
			newNode = new DefaultMutableTreeNode("closure");
			tree.add(newNode);
		} else if (check(node, 15)) {
			newNode = new DefaultMutableTreeNode("for");
			tree.add(newNode);
		} else if (node instanceof BlockStatement) {
			newNode = new DefaultMutableTreeNode("block");
			tree.add(newNode);
		} else if (node instanceof PrimaryExpression) {
			newNode = new DefaultMutableTreeNode("pe");
			tree.add(newNode);
		} else if (node instanceof BinaryExpression) {
			newNode = new DefaultMutableTreeNode("bin"); //(((BinaryExpression) node).operator());
			tree.add(newNode);
		} else if (node instanceof Name) {
			newNode = new DefaultMutableTreeNode(((Name) node).name());
			tree.add(newNode);
		} else if (node instanceof ParameterList) {
			newNode = new DefaultMutableTreeNode("params");
			tree.add(newNode);
		} else if (node instanceof Dot) {
			newNode = new DefaultMutableTreeNode(".");
			tree.add(newNode);
		} else if (node instanceof ClassBody) {
			newNode = new DefaultMutableTreeNode("class body");
			tree.add(newNode);
		} else if (node instanceof Arguments) {
			newNode = new DefaultMutableTreeNode("arg");
			tree.add(newNode);
		} else if (node instanceof Postfix) {
			newNode = new DefaultMutableTreeNode("postfix");
			tree.add(newNode);
		} else if (node instanceof ArrayRef) {
			newNode = new DefaultMutableTreeNode("array");
			tree.add(newNode);
		} else if (node instanceof ArrayLiteral) {
			newNode = new DefaultMutableTreeNode("[ ]");
			tree.add(newNode);
		} else if (node instanceof ASTLeaf) {
			newNode = new DefaultMutableTreeNode(((ASTLeaf) node).token().getText());
			tree.add(newNode);
		} else if (node instanceof NegativeExpression) {
			newNode = new DefaultMutableTreeNode("-");
			tree.add(newNode);
		} else if (node instanceof TernaryStatement) {
			newNode = new DefaultMutableTreeNode("@");
			tree.add(newNode);
		}

		if (node.numChildren() > 0) {
			for (int i = 0; i < node.numChildren(); i++) {
				visitNode(newNode, node.child(i));
			}
		}
	}

	public void show() throws Exception {
		JFrame jf = new JFrame("ks语法树查看器 " + version);
		jf.setSize(900, 600);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());

		// 创建根节点
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("program");

		while (kl.peek(0) != Token.EOF) {
			ASTNode ast = kp.parse(kl);

			if (!ast.toString().equals("()")) {
				visitNode(rootNode, ast);
			}
		}
		
		JTree tree = new JTree(rootNode);
		tree.setShowsRootHandles(true);
		tree.setEditable(false);
		tree.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 16));
		expandAll(tree, true);

		// 设置节点选中监听器
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// System.out.println("当前被选中的节点: " + e.getPath());
			}
		});

		JScrollPane scrollPane = new JScrollPane(tree);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea(5, 50);
		textArea.setEditable(false);
		textArea.setTabSize(2);
		textArea.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 16));
		textArea.setText(this.code);
		JScrollPane scrollPane2 = new JScrollPane(textArea);
		panel.add(scrollPane2, BorderLayout.WEST);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}

	/**
	 * 展开所有的树节点
	 */
	public void expandAll(JTree tree, boolean expand) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();

		expandAll(tree, new TreePath(root), expand);
	}

	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}

		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

}
