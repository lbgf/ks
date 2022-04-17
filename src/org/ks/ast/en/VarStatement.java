/*
 * ==============================================
 * kid script�ű�����
 * ==============================================
 *
 * Project Info: kid script�ű�����;
 *
 */

package org.ks.ast.en;

import java.util.List;

import org.ks.ast.ASTLeaf;
import org.ks.ast.ASTList;
import org.ks.ast.ASTNode;
import org.ks.ast.TypeStatement;
import org.ks.bc.BcGenerator;
import org.ks.bc.BcOpcodes;
import org.ks.core.KsException;
import org.ks.jvm.JavaKsObject;
import org.ks.jvm.JavaKsStaticClass;
import org.ks.runtime.Environment;
import org.ks.runtime.Symbols;
import org.ks.runtime.VarType;

/**
 * ���������ڵ�.
 *
 */
public class VarStatement extends ASTList {

	protected int nest;
	protected int index;
	protected int bcIndex;

	public VarStatement(List<ASTNode> c) {
		super(c);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public TypeStatement type() {
		return (TypeStatement) child(1);
	}

	public ASTNode initializer() {
		return child(2);
	}

	public String toString() {
		return "(var " + name() + " " + type() + " " + initializer() + ")";
	}

	public void lookup(Symbols syms) {
		// �¼ӵģ���������ռ�����
		if (syms.get(name()) != null)
			throw new KsException("�ظ��ı���: " + name(), this);
		//
		
		// �¼ӵģ���������ռ�����
		//Location loc = syms.put(name());
    //nest = loc.nest;
    //index = loc.index;
    //
    
    index = syms.putNew(name());
    if (!type().getType().equals(TypeStatement.UNDEFINED)) {
    	if (type().getType().equals("long") || type().getType().equals("double")) {
    		bcIndex = syms.addVarIndex(name(), 2);
    	} else {
    		bcIndex = syms.addVarIndex(name(), 1); //syms.getVarIndex(name());
    	}
    } else {
    	bcIndex = syms.addVarIndex(name(), 1); //syms.getVarIndex(name());
    }
    initializer().lookup(syms);
	}

	public Object eval(Environment env) {
		Object value = initializer().eval(env);
		env.put(0, index, value);
		return value;
	}
	
	public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("Var"); // test
		
		Object value = initializer().compile(env, bcOp); // value
		
		// System.out.println("value:" + value); // test
		
		if (value instanceof VarType) {
			VarType type = (VarType)value;
			if (type.isExternal()) { // ������ⲿ����
				
			} else if (BcGenerator.isValueType(type)) { // ����ֵ���͵����
				if(type().getType().equals(TypeStatement.UNDEFINED)) {
					// ��û�ж�������ʱ���޸�Ϊ��װ��
					type = BcGenerator.toWrapperType(type, bcOp);
				} else {
					// ��������˻������;�תΪ��������
					if(BcGenerator.isValueType(type().getType())) {
						type = BcGenerator.toValueType(type().getType()); // ����е���࣬��ʱ��������
					}
				}
			} else {
				// ���ص����࣬������
			}
			
			/*if (env.get(0, index) != null)
				throw new KsException("�ظ��ı���: " + name(), this);*/
			
			env.put(0, index, type);
			env.putType(0, index, type); // �������������
						
			bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(type), bcIndex);
			// {}�����ڲ�����Ҫ���ɾֲ�������nest > 0 or env.getOuter() == null��
			/*
			if (env.getOuter() == null) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			} else if (env.getOuter() != null && env.getOuter().isMethodEnvironment()) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			}
			*/

			bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createAndVisitLabel(), bcOp.createAndVisitLabel(), bcIndex);
			
			// ����java.lang.VerifyError����
			env.putFrameObj(BcGenerator.getClassType2(type.getJavaClass()));
			// 
			
		} else if (value instanceof JavaKsStaticClass) {
			JavaKsStaticClass jksc = (JavaKsStaticClass)value;
						
			VarType type = new VarType(jksc.getJavaClass());
			type.setJavaStaticClass(true);
			
			env.put(0, index, type);
			env.putType(0, index, type); // �������������
			
			bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(type), bcIndex);
			
			/*
			if (env.getOuter() == null) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			} else if (env.getOuter() != null && env.getOuter().isMethodEnvironment()) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			}
			*/

			bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createAndVisitLabel(), bcOp.createAndVisitLabel(), bcIndex);
			
			// ����java.lang.VerifyError����
			env.putFrameObj(BcGenerator.getClassType2(type.getJavaClass()));
			// 
			
			return type;
		} else if (value instanceof JavaKsObject) {
			JavaKsObject jko = (JavaKsObject)value;
						
			VarType type = new VarType(jko.getJavaClass());
			type.setJavaObject(true);
			
			env.put(0, index, type);
			env.putType(0, index, type); // �������������
			
			bcOp.gcMethod().visitVarInsn(BcGenerator.getOpsType(type), bcIndex);

			/*
			if (env.getOuter() == null) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			} else if (env.getOuter() != null && env.getOuter().isMethodEnvironment()) {
				bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createLabel(), bcOp.createLabel(), bcIndex);
			}
			*/

			bcOp.gcMethod().visitLocalVariable(name(), BcGenerator.getBcType(type), null, bcOp.createAndVisitLabel(), bcOp.createAndVisitLabel(), bcIndex);
			// ����java.lang.VerifyError����
			env.putFrameObj(BcGenerator.getClassType2(type.getJavaClass()));
			// 
			
			return type;
		} else {
			throw new KsException("��֧�ֵ�����", this);
		}
		
		//
		return value;
	}
	
	// ֻ���ڵ���
	protected void showMsg(Environment env) {
		System.out.println("------------------------" );
		System.out.println("�ؼ��֣�" + name());
		System.out.println("�����ռ䣺" + nest);
		System.out.println("�����ţ�" + index);
		System.out.println("���ͣ�" + env.getType(nest, index));
		System.out.println("������" + env.get(nest, index));
		System.out.println("bc������:" + bcIndex);
		System.out.println("------------------------" );
	}

}
