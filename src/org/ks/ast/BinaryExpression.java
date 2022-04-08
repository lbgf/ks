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
import org.ks.core.ArithmeticUnit;
import org.ks.core.KsException;
import org.ks.runtime.Environment;
import org.ks.runtime.MemberSymbols;
import org.ks.runtime.Symbols;

/**
 * ��Ԫ���ʽ.
 *
 */
public class BinaryExpression extends ASTList {
	
	protected KsClassInfo classInfo = null;
  protected int index;
  ArithmeticUnit au = new ArithmeticUnit();
	
	public BinaryExpression(List<ASTNode> c) {
		super(c);
	}

	public ASTNode left() {
		return child(0);
	}

	public String operator() {
		return ((ASTLeaf) child(1)).token().getText();
	}

	public ASTNode right() {
		return child(2);
	}
	
	public void lookup(Symbols syms) {
    ASTNode left = left();
    if ("=".equals(operator())) {
      if (left instanceof Name) {
        ((Name)left).lookupLeft(syms);
        right().lookup(syms);
        return;
      }
    }
    left.lookup(syms);
    right().lookup(syms);
	}

	public Object eval(Environment env) {
		String op = operator();
		if ("=".equals(op)) {
			Object right = right().eval(env);
			return computeLeft(env, right);
		} else {
			Object left = left().eval(env);
			Object right = right().eval(env);
			return computeOp(left, op, right);
		}
	}
	
	protected Object computeOp(Object left, String op, Object right) {
		switch(op) {
			case "+":
				return au.plus(left, right);
			case "-":
				return au.minus(left, right);
			case "*":
				return au.multiply(left, right);
			case "/":
				return au.divide(left, right);
			case "%":
				return au.mod(left, right);
			case "==":
				return au.compare(left, right, "==");
			case "!=":
				return au.compare(left, right, "!=");
			case "&&":
				return au.compare(left, right, "&&");
			case ">":
				return au.compare(left, right, ">");
			case "<":
				return au.compare(left, right, "<");
			case ">=":
				return au.compare(left, right, ">=");
			case "<=":
				return au.compare(left, right, "<=");
			case "<=>":
				/*if (left != null && right != null) {
					return ((Class<?>)right).isAssignableFrom(left.getClass())?true : false;
				} else {
					return false;
				}*/
				return au.compare(left, right, "<=>");
			default:
				throw new KsException("����������", this);
		}
	}
	
	protected Object computeLeft(Environment env, Object rvalue) {
    ASTNode l = left();
    if (l instanceof Name) {
        ((Name)l).evalLeft(env, rvalue);
        return rvalue;
    } else {

    	if (l instanceof PrimaryExpression) {
  			PrimaryExpression p = (PrimaryExpression) l;
  			if (p.hasPostfix(0) && p.postfix(0) instanceof Dot) { // ��
  				Object t = ((PrimaryExpression) l).evalSub(env, 1);
  				// System.out.println(p.postfix(0));
  				// System.out.println(t.getClass());
  				if (t instanceof KsObject) {
            return setField((KsObject)t, (Dot)p.postfix(0), rvalue);
  				} else { // java��
      			try {
      				Class<?> c = null;
      				if (t instanceof String) { // ���ַ������ж���Ҫ�Ľ�
      					c = Class.forName(t.toString());
      					c.getField(((Dot)p.postfix(0)).name()).set(c, rvalue); // ����
      				} else {
      					c = t.getClass();
      					c.getField(((Dot)p.postfix(0)).name()).set(t, rvalue); // ����
      				}
      				return c; 
      			} catch(Exception e) {
      				return null;
      			}
  				}
  			} else if (p.hasPostfix(0) && p.postfix(0) instanceof ArrayRef) { // ����
  				Object a = ((PrimaryExpression) l).evalSub(env, 1);
  				if (a instanceof Object[]) {
  					ArrayRef aref = (ArrayRef) p.postfix(0);
  					Object index = aref.index().eval(env);
  					if (index instanceof Integer) {
  						((Object[]) a)[(Integer) index] = rvalue;
  						return rvalue;
  					}
  				}
  				throw new KsException("�������", this);
  			}
  			throw new KsException("����ı��ʽ", this);
  		} else if (l instanceof Name) {
  			env.put(((Name) l).name(), rvalue);
  			return rvalue;
  		} else
  			throw new KsException("����ı��ʽ", this);
    	
    }
	}
  
  protected Object setField(KsObject obj, Dot expr, Object rvalue) {
		if (obj.classInfo() != classInfo) {
			String member = expr.name();
			classInfo = obj.classInfo();
			Integer i = classInfo.fieldIndex(member);
			if (i == null) {
				throw new KsException("���ܷ��ʳ�Ա: " + member, this); 
			}
			index = i;
		}
		obj.write(index, rvalue);
		return rvalue;
	}
  
  public Object compile(Environment env, BcOpcodes bcOp) {
		// System.out.println("BinaryExpression");  // test
		
		String op = operator();
		// System.out.println("op:" + op); // test
		if ("=".equals(op)) {
			// left().compile(env, bcOp);
			if (left() instanceof Name) {
				Name n = (Name)left();
				
				if (n.nest == MemberSymbols.FIELD) { // �Զ���������
					// bcOp.createField(bcOp.ACC_PUBLIC, "env", "Lorg/ks/runtime/Environment;", null, null);
					bcOp.getThis();
					Object obj = right().compile(env, bcOp);
					n.compileLeft(env, bcOp, obj);
				} else {
					if(env.getBcValue(n.name()) != null) { // �ⲿ����
						n.compileLeft(env, bcOp, right());
					} else {
						Object obj = right().compile(env, bcOp);
						n.compileLeft(env, bcOp, obj);
					}
				}
			} else {
				right().compile(env, bcOp);
			}
			
		} else {
			// System.out.println(left().getClass()); // test
			// System.out.println(right().getClass()); // test
			if (op.equals("+")) {
				return BcGenerator.plus(left(), right(), env, bcOp);
			} else if (op.equals("-")) {
				return BcGenerator.minus(left(), right(),env, bcOp);
			} else if (op.equals("*")) {
				return BcGenerator.multiply(left(), right(), env, bcOp);
			} else if (op.equals("/")) {
				return BcGenerator.divide(left(), right(), env, bcOp);
			} else if (op.equals("%")) {
				return BcGenerator.mod(left(), right(), env, bcOp);
			} else if (op.equals("==") || op.equals("&&") || op.equals("||") 
					|| op.equals("<") || op.equals(">") || op.equals("<=") || op.equals(">=")
					|| op.equals("!=")) {
				return BcGenerator.compare(left(), right(), op, env, bcOp);
			} 
		}
		return null;
	}
	
}
