/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast;

import java.util.List;

import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;
import org.ks.runtime.VarType;

/**
 * �����б�.
 *
 */
public class ParameterList extends ASTList {
	
	protected int[] offsets = null;
	protected int[] bcOffsets = null; // ����BCģʽ
	
	public ParameterList(List<ASTNode> c) {
		super(c);
	}

	public String name(int i) {
		// return ((ASTLeaf) child(i)).token().getText();
		return ((ASTLeaf)child(i).child(0)).token().getText();
	}
	
	public int size() {
		return numChildren();
	}
	
	public void lookup(Symbols syms) {
    int s = size();
    offsets = new int[s];
    bcOffsets = new int[s];
    for (int i = 0; i < s; i++) {
    	offsets[i] = syms.putNew(name(i));
    	String type = typeTag(i).getType();
    	if (!type.equals(TypeStatement.UNDEFINED)) {
      	if (type.equals("long") || type.equals("double")) {
      		bcOffsets[i] = syms.addVarIndex(name(i), 2); 
      	} else {
      		bcOffsets[i] = syms.addVarIndex(name(i), 1); 
      	}
      } else {
      	bcOffsets[i] = syms.addVarIndex(name(i), 1); 
      }

    	// syms.updateVarIndex(name(i), i+1); // ����������,��Ӧ�ֽ����
    }
    
	}
	
	public void eval(Environment env, int index, Object value) {
		env.put(0, offsets[index], value);
	}
	
	public TypeStatement typeTag(int i) {
    return (TypeStatement)child(i).child(1);
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("ParameterList");  // test
		
		for (int i = 0; i < size(); i++) {
    	bcOp.gcMethod().visitParameter(name(i), i);
    	String type = typeTag(i).getType();
    	if (!type.equals(TypeStatement.UNDEFINED)) {
    		if (BcGenerator.isValueType(type)) {
    			env.put(0, offsets[i], BcGenerator.toValueType(type));
  	    	env.putType(0, offsets[i], BcGenerator.toValueType(type));
  	    	bcOp.gcMethod().visitLocalVariable(name(i), BcGenerator.getClassType(BcGenerator.toValueType(type).getJavaClass()),
  	    			null, bcOp.createLabel(), bcOp.createLabel(), bcOffsets[i]); 
    		} else {
    			throw new KsException("��֧�ֵĲ�������", this);
    		}
    	} else {
	    	env.put(0, offsets[i], new VarType(Object.class));
	    	env.putType(0, offsets[i], new VarType(Object.class));
	    	
	    	bcOp.gcMethod().visitLocalVariable(name(i), BcGenerator.getClassType(Object.class), null, bcOp.createLabel(), bcOp.createLabel(), bcOffsets[i]); 
    	}
    }
		
		return null;
  }
	
	public String getParamsType() {
		String paramsType = "";
		for (int i = 0; i < size(); i++) {
			String type = typeTag(i).getType();
    	if (!type.equals(TypeStatement.UNDEFINED)) {
    		if (BcGenerator.isValueType(type)) {
    			paramsType += BcGenerator.getClassType(BcGenerator.toValueType(type).getJavaClass());
    		} else {
    			throw new KsException("��֧�ֵĲ�������", this);
    		}
    	} else {
    		paramsType += BcGenerator.getClassType(Object.class);
    	}
		}
		return paramsType;
	}
	
	public String getReturnType() {
		return BcGenerator.getClassType(Object.class);
	}
	
}
