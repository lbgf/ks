/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.ast.en;

import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.Dot;
import org.ks.ast.PrimaryExpression;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;
import org.ks.runtime.VarType;
import org.ks.util.JavaUtil;

/**
 * 导入节点.
 *
 */
public class ImportStatement extends ASTList {

	protected int index;

	public ImportStatement(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		String name = "";
		PrimaryExpression pe = (PrimaryExpression)child(0);
		for (int i = 0; i < pe.numChildren(); i++) {
			if (pe.child(i) instanceof ASTLeaf) {
				name += ((ASTLeaf)pe.child(i)).token().getText();
			} else if (pe.child(i) instanceof Dot) {
				name += ((Dot)pe.child(i)).toString();
			}
		}
		return name;
	}

	public String toString() {
		return "(import " + name() + ")";
	}

	public void lookup(Symbols syms) {
		String name = name();
		String key = name.split("[.]")[name.split("[.]").length - 1];
		index = syms.putNew(key);
	}

	public Object eval(Environment env) {
		String name = JavaUtil.getRealClassName(name()); //name();
		String key = "";
		if (name.indexOf("$") > 0) {
			key = name.split("[.]")[name.split("[.]").length - 1];
			key = key.substring(key.indexOf("$") + 1, key.length());
		} else {
			key = name.split("[.]")[name.split("[.]").length - 1];
		}
		env.putClass(key, name); // 针对java类库
		env.put(0, index, name);
		return "";
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		String name = JavaUtil.getRealClassName(name()); //name();
		String key = "";
		if (name.indexOf("$") > 0) {
			key = name.split("[.]")[name.split("[.]").length - 1];
			key = key.substring(key.indexOf("$") + 1, key.length());
		} else {
			key = name.split("[.]")[name.split("[.]").length - 1];
		}
		env.putClass(key, name); // 针对java类库
		env.put(0, index, name);
		try {
			VarType varType = new VarType(Class.forName(name));
			varType.setClassLib(true);
			env.putType(0, index, varType);
		} catch (Exception e) {
			throw new KsException(e.getMessage(), this);
		}
		return null;
	}
	
}
 