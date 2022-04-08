/*
 * ==============================================
 * kid script脚本引擎
 * ==============================================
 *
 * Project Info: kid script脚本引擎;
 *
 */

package org.ks.core;

/**
 * 运算器.
 *
 */
public class ArithmeticUnit {
	
	public Object negative(Object o) {
		if (o instanceof Integer) {
			return negative(((Integer)o).intValue());
		} else if (o instanceof Long) {
			return negative(((Long)o).longValue());
		} else if (o instanceof Float) {
			return negative(((Float)o).floatValue());
		} else if (o instanceof Double) {
			return negative(((Double)o).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public int negative(int o) {
		return new Integer(-o).intValue();
	}
	
	public long negative(long o) {
		return new Long(-o).longValue();
	}
	
	public float negative(float o) {
		return new Float(-o).floatValue();
	}
	
	public double negative(double o) {
		return new Double(-o).doubleValue();
	}
	
	public Object plus(Object t1, Object t2) {
		if (t1 instanceof Integer && t2 instanceof Integer) {
			return plus(((Integer)t1).intValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Integer && t2 instanceof Long) {
			return plus(((Integer)t1).intValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Integer && t2 instanceof Float) {
			return plus(((Integer)t1).intValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Integer && t2 instanceof Double) {
			return plus(((Integer)t1).intValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Long && t2 instanceof Integer) {
			return plus(((Long)t1).longValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Long && t2 instanceof Long) {
			return plus(((Long)t1).longValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Long && t2 instanceof Float) {
			return plus(((Long)t1).longValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Long && t2 instanceof Double) {
			return plus(((Long)t1).longValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Float && t2 instanceof Integer) {
			return plus(((Float)t1).floatValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Float && t2 instanceof Long) {
			return plus(((Float)t1).floatValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Float && t2 instanceof Float) {
			return plus(((Float)t1).floatValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Float && t2 instanceof Double) {
			return plus(((Float)t1).floatValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Double && t2 instanceof Integer) {
			return plus(((Double)t1).doubleValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Double && t2 instanceof Long) {
			return plus(((Double)t1).doubleValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Double && t2 instanceof Float) {
			return plus(((Double)t1).doubleValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Double && t2 instanceof Double) {
			return plus(((Double)t1).doubleValue(), ((Double)t2).doubleValue());
		} 
		return t1.toString() + t2.toString();
	}
	
	public Object plus(int t1, Object t2) {
		if (t2 instanceof Integer) {
			return plus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return plus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return plus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return plus(t1, ((Double)t2).doubleValue());
		}
		return t1 + t2.toString();
	}
	
	public Object plus(Object t1, int t2) {
		if (t1 instanceof Integer) {
			return plus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return plus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return plus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return plus(((Double)t1).doubleValue(), t2);
		}
		return t1.toString() + t2;
	}
	
	public Object plus(long t1, Object t2) {
		if (t2 instanceof Integer) {
			return plus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return plus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return plus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return plus(t1, ((Double)t2).doubleValue());
		}
		return t1 + t2.toString();
	}
	
	public Object plus(Object t1, long t2) {
		if (t1 instanceof Integer) {
			return plus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return plus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return plus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return plus(((Double)t1).doubleValue(), t2);
		}
		return t1.toString() + t2;
	}
	
	public Object plus(float t1, Object t2) {
		if (t2 instanceof Integer) {
			return plus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return plus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return plus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return plus(t1, ((Double)t2).doubleValue());
		}
		return t1 + t2.toString();
	}
	
	public Object plus(Object t1, float t2) {
		if (t1 instanceof Integer) {
			return plus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return plus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return plus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return plus(((Double)t1).doubleValue(), t2);
		}
		return t1.toString() + t2;
	}
	
	public Object plus(double t1, Object t2) {
		if (t2 instanceof Integer) {
			return plus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return plus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return plus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return plus(t1, ((Double)t2).doubleValue());
		}
		return t1 + t2.toString();
	}
	
	public Object plus(Object t1, double t2) {
		if (t1 instanceof Integer) {
			return plus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return plus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return plus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return plus(((Double)t1).doubleValue(), t2);
		}
		return t1.toString() + t2;
	}
	
	public Object plus(boolean t1, Object t2) {
		return t1 + t2.toString();
	}
	
	public Object plus(Object t1, boolean t2) {
		return t1.toString() + t2;
	}
	
	public int plus(int t1, int t2) {
		return t1 + t2;
	}
	
	public long plus(int t1, long t2) {
		return t1 + t2;
	}
	
	public long plus(long t1, int t2) {
		return t1 + t2;
	}
	
	public float plus(int t1, float t2) {
		return t1 + t2;
	}
	
	public float plus(float t1, int t2) {
		return t1 + t2;
	}
	
	public double plus(int t1, double t2) {
		return t1 + t2;
	}
	
	public double plus(double t1, int t2) {
		return t1 + t2;
	}
	
	public int plus(int t1, boolean t2) {
		return t1 + (t2 == true?0:-1);
	}
	
	public int plus(boolean t1, int t2) {
		return (t1 == true?0:-1) + t2;
	}
	
	public long plus(long t1, long t2) {
		return t1 + t2;
	}
	
	public float plus(long t1, float t2) {
		return t1 + t2;
	}
	
	public float plus(float t1, long t2) {
		return t1 + t2;
	}
	
	public double plus(long t1, double t2) {
		return t1 + t2;
	}
	
	public double plus(double t1, long t2) {
		return t1 + t2;
	}
	
	public long plus(long t1, boolean t2) {
		return t1 + (t2 == true?0:-1);
	}
	
	public long plus(boolean t1, long t2) {
		return (t1 == true?0:-1) + t2;
	}
	
	public float plus(float t1, float t2) {
		return t1 + t2;
	}
	
	public double plus(float t1, double t2) {
		return t1 + t2;
	}
	
	public float plus(float t1, boolean t2) {
		return t1 + (t2 == true?0:-1);
	}
	
	public float plus(boolean t1, float t2) {
		return (t1 == true?0:-1) + t2;
	}
	
	public double plus(double t1, float t2) {
		return t1 + t2;
	}
	
	public double plus(double t1, double t2) {
		return t1 + t2;
	}
	
	public double plus(double t1, boolean t2) {
		return t1 + (t2 == true?0:-1);
	}
	
	public double plus(boolean t1, double t2) {
		return (t1 == true?0:-1) + t2;
	}
	
	public boolean plus(boolean t1, boolean t2) {
		return t1 && t2;
	}
	
	//
	public Object minus(Object t1, Object t2) {
		if (t1 instanceof Integer && t2 instanceof Integer) {
			return minus(((Integer)t1).intValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Integer && t2 instanceof Long) {
			return minus(((Integer)t1).intValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Integer && t2 instanceof Float) {
			return minus(((Integer)t1).intValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Integer && t2 instanceof Double) {
			return minus(((Integer)t1).intValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Long && t2 instanceof Integer) {
			return minus(((Long)t1).longValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Long && t2 instanceof Long) {
			return minus(((Long)t1).longValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Long && t2 instanceof Float) {
			return minus(((Long)t1).longValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Long && t2 instanceof Double) {
			return minus(((Long)t1).longValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Float && t2 instanceof Integer) {
			return minus(((Float)t1).floatValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Float && t2 instanceof Long) {
			return minus(((Float)t1).floatValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Float && t2 instanceof Float) {
			return minus(((Float)t1).floatValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Float && t2 instanceof Double) {
			return minus(((Float)t1).floatValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Double && t2 instanceof Integer) {
			return minus(((Double)t1).doubleValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Double && t2 instanceof Long) {
			return minus(((Double)t1).doubleValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Double && t2 instanceof Float) {
			return minus(((Double)t1).doubleValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Double && t2 instanceof Double) {
			return minus(((Double)t1).doubleValue(), ((Double)t2).doubleValue());
		} 
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(int t1, Object t2) {
		if (t2 instanceof Integer) {
			return minus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return minus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return minus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return minus(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(Object t1, int t2) {
		if (t1 instanceof Integer) {
			return minus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return minus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return minus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return minus(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(long t1, Object t2) {
		if (t2 instanceof Integer) {
			return minus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return minus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return minus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return minus(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(Object t1, long t2) {
		if (t1 instanceof Integer) {
			return minus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return minus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return minus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return minus(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(float t1, Object t2) {
		if (t2 instanceof Integer) {
			return minus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return minus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return minus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return minus(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(Object t1, float t2) {
		if (t1 instanceof Integer) {
			return minus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return minus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return minus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return minus(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(double t1, Object t2) {
		if (t2 instanceof Integer) {
			return minus(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return minus(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return minus(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return minus(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object minus(Object t1, double t2) {
		if (t1 instanceof Integer) {
			return minus(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return minus(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return minus(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return minus(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public int minus(int t1, int t2) {
		return t1 - t2;
	}
	
	public long minus(int t1, long t2) {
		return t1 - t2;
	}
	
	public long minus(long t1, int t2) {
		return t1 - t2;
	}
	
	public float minus(int t1, float t2) {
		return t1 - t2;
	}
	
	public float minus(float t1, int t2) {
		return t1 - t2;
	}
	
	public double minus(int t1, double t2) {
		return t1 - t2;
	}
	
	public double minus(double t1, int t2) {
		return t1 - t2;
	}
	
	public long minus(long t1, long t2) {
		return t1 - t2;
	}
	
	public float minus(long t1, float t2) {
		return t1 - t2;
	}
	
	public float minus(float t1, long t2) {
		return t1 - t2;
	}
	
	public double minus(long t1, double t2) {
		return t1 - t2;
	}
	
	public double minus(double t1, long t2) {
		return t1 - t2;
	}
	
	public float minus(float t1, float t2) {
		return t1 - t2;
	}
	
	public double minus(float t1, double t2) {
		return t1 - t2;
	}
	
	public double minus(double t1, float t2) {
		return t1 - t2;
	}
	
	public double minus(double t1, double t2) {
		return t1 - t2;
	}
	
	//
	public Object multiply(Object t1, Object t2) {
		if (t1 instanceof Integer && t2 instanceof Integer) {
			return multiply(((Integer)t1).intValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Integer && t2 instanceof Long) {
			return multiply(((Integer)t1).intValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Integer && t2 instanceof Float) {
			return multiply(((Integer)t1).intValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Integer && t2 instanceof Double) {
			return multiply(((Integer)t1).intValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Long && t2 instanceof Integer) {
			return multiply(((Long)t1).longValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Long && t2 instanceof Long) {
			return multiply(((Long)t1).longValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Long && t2 instanceof Float) {
			return multiply(((Long)t1).longValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Long && t2 instanceof Double) {
			return multiply(((Long)t1).longValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Float && t2 instanceof Integer) {
			return multiply(((Float)t1).floatValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Float && t2 instanceof Long) {
			return multiply(((Float)t1).floatValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Float && t2 instanceof Float) {
			return multiply(((Float)t1).floatValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Float && t2 instanceof Double) {
			return multiply(((Float)t1).floatValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Double && t2 instanceof Integer) {
			return multiply(((Double)t1).doubleValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Double && t2 instanceof Long) {
			return multiply(((Double)t1).doubleValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Double && t2 instanceof Float) {
			return multiply(((Double)t1).doubleValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Double && t2 instanceof Double) {
			return multiply(((Double)t1).doubleValue(), ((Double)t2).doubleValue());
		} 
		throw new KsException("不是数值类型对象");
	}
	
	public Object multiply(int t1, Object t2) {
		if (t2 instanceof Integer) {
			return multiply(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return multiply(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return multiply(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return multiply(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object multiply(Object t1, int t2) {
		if (t1 instanceof Integer) {
			return multiply(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return multiply(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return multiply(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return multiply(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object multiply(long t1, Object t2) {
		if (t2 instanceof Integer) {
			return multiply(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return multiply(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return multiply(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return multiply(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object multiply(Object t1, long t2) {
		if (t1 instanceof Integer) {
			return multiply(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return multiply(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return multiply(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return multiply(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object multiply(double t1, Object t2) {
		if (t2 instanceof Integer) {
			return multiply(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return multiply(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return multiply(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return multiply(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object multiply(Object t1, double t2) {
		if (t1 instanceof Integer) {
			return multiply(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return multiply(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return multiply(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return multiply(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public int multiply(int t1, int t2) {
		return t1 * t2;
	}
	
	public long multiply(int t1, long t2) {
		return t1 * t2;
	}
	
	public long multiply(long t1, int t2) {
		return t1 * t2;
	}
	
	public float multiply(int t1, float t2) {
		return t1 * t2;
	}
	
	public float multiply(float t1, int t2) {
		return t1 * t2;
	}
	
	public double multiply(int t1, double t2) {
		return t1 * t2;
	}
	
	public double multiply(double t1, int t2) {
		return t1 * t2;
	}
	
	public long multiply(long t1, long t2) {
		return t1 * t2;
	}
	
	public float multiply(long t1, float t2) {
		return t1 * t2;
	}
	
	public float multiply(float t1, long t2) {
		return t1 * t2;
	}
	
	public double multiply(long t1, double t2) {
		return t1 * t2;
	}
	
	public double multiply(double t1, long t2) {
		return t1 * t2;
	}
	
	public float multiply(float t1, float t2) {
		return t1 * t2;
	}
	
	public double multiply(float t1, double t2) {
		return t1 * t2;
	}
	
	public double multiply(double t1, float t2) {
		return t1 * t2;
	}
	
	public double multiply(double t1, double t2) {
		return t1 * t2;
	}

	//
	public Object divide(Object t1, Object t2) {
		if (t1 instanceof Integer && t2 instanceof Integer) {
			return divide(((Integer)t1).intValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Integer && t2 instanceof Long) {
			return divide(((Integer)t1).intValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Integer && t2 instanceof Float) {
			return divide(((Integer)t1).intValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Integer && t2 instanceof Double) {
			return divide(((Integer)t1).intValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Long && t2 instanceof Integer) {
			return divide(((Long)t1).longValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Long && t2 instanceof Long) {
			return divide(((Long)t1).longValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Long && t2 instanceof Float) {
			return divide(((Long)t1).longValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Long && t2 instanceof Double) {
			return divide(((Long)t1).longValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Float && t2 instanceof Integer) {
			return divide(((Float)t1).floatValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Float && t2 instanceof Long) {
			return divide(((Float)t1).floatValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Float && t2 instanceof Float) {
			return divide(((Float)t1).floatValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Float && t2 instanceof Double) {
			return divide(((Float)t1).floatValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Double && t2 instanceof Integer) {
			return divide(((Double)t1).doubleValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Double && t2 instanceof Long) {
			return divide(((Double)t1).doubleValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Double && t2 instanceof Float) {
			return divide(((Double)t1).doubleValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Double && t2 instanceof Double) {
			return divide(((Double)t1).doubleValue(), ((Double)t2).doubleValue());
		} 
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(int t1, Object t2) {
		if (t2 instanceof Integer) {
			return divide(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return divide(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return divide(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return divide(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(Object t1, int t2) {
		if (t1 instanceof Integer) {
			return divide(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return divide(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return divide(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return divide(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(long t1, Object t2) {
		if (t2 instanceof Integer) {
			return divide(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return divide(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return divide(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return divide(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(Object t1, long t2) {
		if (t1 instanceof Integer) {
			return divide(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return divide(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return divide(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return divide(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(float t1, Object t2) {
		if (t2 instanceof Integer) {
			return divide(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return divide(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return divide(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return divide(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(Object t1, float t2) {
		if (t1 instanceof Integer) {
			return divide(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return divide(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return divide(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return divide(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(double t1, Object t2) {
		if (t2 instanceof Integer) {
			return divide(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return divide(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return divide(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return divide(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object divide(Object t1, double t2) {
		if (t1 instanceof Integer) {
			return divide(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return divide(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return divide(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return divide(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public int divide(int t1, int t2) {
		return t1 / t2;
	}
	
	public long divide(int t1, long t2) {
		return t1 / t2;
	}
	
	public long divide(long t1, int t2) {
		return t1 / t2;
	}
	
	public float divide(int t1, float t2) {
		return t1 / t2;
	}
	
	public float divide(float t1, int t2) {
		return t1 / t2;
	}
	
	public double divide(int t1, double t2) {
		return t1 / t2;
	}
	
	public double divide(double t1, int t2) {
		return t1 / t2;
	}
	
	public long divide(long t1, long t2) {
		return t1 / t2;
	}
	
	public float divide(long t1, float t2) {
		return t1 / t2;
	}
	
	public float divide(float t1, long t2) {
		return t1 / t2;
	}
	
	public double divide(long t1, double t2) {
		return t1 / t2;
	}
	
	public double divide(double t1, long t2) {
		return t1 / t2;
	}
	
	public float divide(float t1, float t2) {
		return t1 / t2;
	}
	
	public double divide(float t1, double t2) {
		return t1 / t2;
	}
	
	public double divide(double t1, float t2) {
		return t1 / t2;
	}
	
	public double divide(double t1, double t2) {
		return t1 / t2;
	}
	
	// 
	public Object mod(Object t1, Object t2) {
		if (t1 instanceof Integer && t2 instanceof Integer) {
			return mod(((Integer)t1).intValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Integer && t2 instanceof Long) {
			return mod(((Integer)t1).intValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Integer && t2 instanceof Float) {
			return mod(((Integer)t1).intValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Integer && t2 instanceof Double) {
			return mod(((Integer)t1).intValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Long && t2 instanceof Integer) {
			return mod(((Long)t1).longValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Long && t2 instanceof Long) {
			return mod(((Long)t1).longValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Long && t2 instanceof Float) {
			return mod(((Long)t1).longValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Long && t2 instanceof Double) {
			return mod(((Long)t1).longValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Float && t2 instanceof Integer) {
			return divide(((Float)t1).floatValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Float && t2 instanceof Long) {
			return divide(((Float)t1).floatValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Float && t2 instanceof Float) {
			return divide(((Float)t1).floatValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Float && t2 instanceof Double) {
			return divide(((Float)t1).floatValue(), ((Double)t2).doubleValue());
		} else if (t1 instanceof Double && t2 instanceof Integer) {
			return mod(((Double)t1).doubleValue(), ((Integer)t2).intValue());
		} else if (t1 instanceof Double && t2 instanceof Long) {
			return mod(((Double)t1).doubleValue(), ((Long)t2).longValue());
		} else if (t1 instanceof Double && t2 instanceof Float) {
			return mod(((Double)t1).doubleValue(), ((Float)t2).floatValue());
		} else if (t1 instanceof Double && t2 instanceof Double) {
			return mod(((Double)t1).doubleValue(), ((Double)t2).doubleValue());
		} 
		throw new KsException("不是数值类型对象");
	}
	
	public Object mod(int t1, Object t2) {
		if (t2 instanceof Integer) {
			return mod(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return mod(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return mod(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return mod(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object mod(Object t1, int t2) {
		if (t1 instanceof Integer) {
			return mod(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return mod(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return mod(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return mod(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object mod(long t1, Object t2) {
		if (t2 instanceof Integer) {
			return mod(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return mod(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return mod(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return mod(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object mod(Object t1, long t2) {
		if (t1 instanceof Integer) {
			return mod(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return mod(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return mod(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return mod(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object mod(double t1, Object t2) {
		if (t2 instanceof Integer) {
			return mod(t1, ((Integer)t2).intValue());
		} else if (t2 instanceof Long) {
			return mod(t1, ((Long)t2).longValue());
		} else if (t2 instanceof Float) {
			return mod(t1, ((Float)t2).floatValue());
		} else if (t2 instanceof Double) {
			return mod(t1, ((Double)t2).doubleValue());
		}
		throw new KsException("不是数值类型对象");
	}
	
	public Object mod(Object t1, double t2) {
		if (t1 instanceof Integer) {
			return mod(((Integer)t1).intValue(), t2);
		} else if (t1 instanceof Long) {
			return mod(((Long)t1).longValue(), t2);
		} else if (t1 instanceof Float) {
			return mod(((Float)t1).floatValue(), t2);
		} else if (t1 instanceof Double) {
			return mod(((Double)t1).doubleValue(), t2);
		}
		throw new KsException("不是数值类型对象");
	}
	
	public int mod(int t1, int t2) {
		return t1 % t2;
	}
	
	public long mod(int t1, long t2) {
		return t1 % t2;
	}
	
	public long mod(long t1, int t2) {
		return t1 % t2;
	}
	
	public float mod(int t1, float t2) {
		return t1 % t2;
	}
	
	public float mod(float t1, int t2) {
		return t1 % t2;
	}
	
	public double mod(int t1, double t2) {
		return t1 % t2;
	}
	
	public double mod(double t1, int t2) {
		return t1 % t2;
	}
	
	public long mod(long t1, long t2) {
		return t1 % t2;
	}
	
	public float mod(long t1, float t2) {
		return t1 % t2;
	}
	
	public float mod(float t1, long t2) {
		return t1 % t2;
	}
	
	public double mod(long t1, double t2) {
		return t1 % t2;
	}
	
	public double mod(double t1, long t2) {
		return t1 % t2;
	}
	
	public float mod(float t1, float t2) {
		return t1 % t2;
	}
	
	public double mod(float t1, double t2) {
		return t1 % t2;
	}
	
	public double mod(double t1, float t2) {
		return t1 % t2;
	}
	
	public double mod(double t1, double t2) {
		return t1 % t2;
	}
	
	//
	public boolean compare(Object t1, Object t2, String op) {
		
		if (t1 instanceof Integer && t2 instanceof Integer) {
			return compare(((Integer)t1).intValue(), ((Integer)t2).intValue(), op);
		} else if (t1 instanceof Integer && t2 instanceof Long) {
			return compare(((Integer)t1).intValue(), ((Long)t2).longValue(), op);
		} else if (t1 instanceof Integer && t2 instanceof Float) {
			return compare(((Integer)t1).intValue(), ((Float)t2).floatValue(), op);
		} else if (t1 instanceof Integer && t2 instanceof Double) {
			return compare(((Integer)t1).intValue(), ((Double)t2).doubleValue(), op);
		} else if (t1 instanceof Integer && t2 instanceof Boolean) {
			return compare(((Integer)t1).intValue(), ((Boolean)t2).booleanValue(), op);
		} else if (t1 instanceof Long && t2 instanceof Integer) {
			return compare(((Long)t1).longValue(), ((Integer)t2).intValue(), op);
		} else if (t1 instanceof Long && t2 instanceof Long) {
			return compare(((Long)t1).longValue(), ((Long)t2).longValue(), op);
		} else if (t1 instanceof Long && t2 instanceof Float) {
			return compare(((Long)t1).longValue(), ((Float)t2).floatValue(), op);
		} else if (t1 instanceof Long && t2 instanceof Double) {
			return compare(((Long)t1).longValue(), ((Double)t2).doubleValue(), op);
		} else if (t1 instanceof Long && t2 instanceof Boolean) {
			return compare(((Long)t1).longValue(), ((Boolean)t2).booleanValue(), op);
		} else if (t1 instanceof Float && t2 instanceof Integer) {
			return compare(((Float)t1).floatValue(), ((Integer)t2).intValue(), op);
		} else if (t1 instanceof Float && t2 instanceof Long) {
			return compare(((Float)t1).floatValue(), ((Long)t2).longValue(), op);
		} else if (t1 instanceof Float && t2 instanceof Float) {
			return compare(((Float)t1).floatValue(), ((Float)t2).floatValue(), op);
		} else if (t1 instanceof Float && t2 instanceof Double) {
			return compare(((Float)t1).floatValue(), ((Double)t2).doubleValue(), op);
		} else if (t1 instanceof Float && t2 instanceof Boolean) {
			return compare(((Float)t1).floatValue(), ((Boolean)t2).booleanValue(), op);
		} else if (t1 instanceof Double && t2 instanceof Integer) {
			return compare(((Double)t1).doubleValue(), ((Integer)t2).intValue(), op);
		} else if (t1 instanceof Double && t2 instanceof Long) {
			return compare(((Double)t1).doubleValue(), ((Long)t2).longValue(), op);
		} else if (t1 instanceof Double && t2 instanceof Float) {
			return compare(((Double)t1).doubleValue(), ((Float)t2).floatValue(), op);
		} else if (t1 instanceof Double && t2 instanceof Double) {
			return compare(((Double)t1).doubleValue(), ((Double)t2).doubleValue(), op);
		} else if (t1 instanceof Double && t2 instanceof Boolean) {
			return compare(((Double)t1).doubleValue(), ((Boolean)t2).booleanValue(), op);
		} else if (t1 instanceof Boolean && t2 instanceof Integer) {
			return compare(((Boolean)t1).booleanValue(), ((Integer)t2).intValue(), op);
		} else if (t1 instanceof Boolean && t2 instanceof Long) {
			return compare(((Boolean)t1).booleanValue(), ((Long)t2).longValue(), op);
		} else if (t1 instanceof Boolean && t2 instanceof Float) {
			return compare(((Boolean)t1).booleanValue(), ((Float)t2).floatValue(), op);
		} else if (t1 instanceof Boolean && t2 instanceof Double) {
			return compare(((Boolean)t1).booleanValue(), ((Double)t2).doubleValue(), op);
		} else if (t1 instanceof Boolean && t2 instanceof Boolean) {
			return compare(((Boolean)t1).booleanValue(), ((Boolean)t2).booleanValue(), op);
		} 
		
		if(op.equals("==")) {
			if (t1 != null && t2 != null) {
				return t1.toString().equals(t2.toString());
			} else {
				return t1 == t2;
			}
		} else if(op.equals("!=")) {
			if (t1 != null && t2 != null) {
				return !t1.toString().equals(t2.toString());
			} else {
				return t1 != t2;
			}
		} else if(op.equals("<=>")) {
			if (t1 != null && t2 != null) {
				return ((Class<?>)t1).isAssignableFrom(t2.getClass())?true : false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean compare(int t1, Object t2, String op) {
		if (t2 instanceof Integer) {
			return compare(t1, ((Integer)t2).intValue(), op);
		} else if (t2 instanceof Long) {
			return compare(t1, ((Long)t2).longValue(), op);
		} else if (t2 instanceof Float) {
			return compare(t1, ((Float)t2).floatValue(), op);
		} else if (t2 instanceof Double) {
			return compare(t1, ((Double)t2).doubleValue(), op);
		} else if ( t2 instanceof Boolean) {
			return compare(t1, ((Boolean)t2).booleanValue(), op);
		} else {
			return false;
		}
	}
	
	public boolean compare(Object t1, int t2, String op) {
		if (t1 instanceof Integer) {
			return compare(((Integer)t1).intValue(), t2, op);
		} else if (t1 instanceof Long) {
			return compare(((Long)t1).longValue(), t2, op);
		} else if (t1 instanceof Float) {
			return compare(((Float)t1).floatValue(), t2, op);
		} else if (t1 instanceof Double) {
			return compare(((Double)t1).doubleValue(), t2, op);
		} else if (t1 instanceof Boolean) {
			return compare(((Boolean)t1).booleanValue(), t2, op);
		} else {
			return false;
		}
	}
	
	public boolean compare(long t1, Object t2, String op) {
		if (t2 instanceof Integer) {
			return compare(t1, ((Integer)t2).intValue(), op);
		} else if (t2 instanceof Long) {
			return compare(t1, ((Long)t2).longValue(), op);
		} else if (t2 instanceof Float) {
			return compare(t1, ((Float)t2).floatValue(), op);
		} else if (t2 instanceof Double) {
			return compare(t1, ((Double)t2).doubleValue(), op);
		} else if ( t2 instanceof Boolean) {
			return compare(t1, ((Boolean)t2).booleanValue(), op);
		} else {
			return false;
		}
	}
	
	public boolean compare(Object t1, long t2, String op) {
		if (t1 instanceof Integer) {
			return compare(((Integer)t1).intValue(), t2, op);
		} else if (t1 instanceof Long) {
			return compare(((Long)t1).longValue(), t2, op);
		} else if (t1 instanceof Float) {
			return compare(((Float)t1).floatValue(), t2, op);
		} else if (t1 instanceof Double) {
			return compare(((Double)t1).doubleValue(), t2, op);
		} else if (t1 instanceof Boolean) {
			return compare(((Boolean)t1).booleanValue(), t2, op);
		} else {
			return false;
		}
	}
	
	public boolean compare(float t1, Object t2, String op) {
		if (t2 instanceof Integer) {
			return compare(t1, ((Integer)t2).intValue(), op);
		} else if (t2 instanceof Long) {
			return compare(t1, ((Long)t2).longValue(), op);
		} else if (t2 instanceof Float) {
			return compare(t1, ((Float)t2).floatValue(), op);
		} else if (t2 instanceof Double) {
			return compare(t1, ((Double)t2).doubleValue(), op);
		} else if ( t2 instanceof Boolean) {
			return compare(t1, ((Boolean)t2).booleanValue(), op);
		} else {
			return false;
		}
	}
	
	public boolean compare(Object t1, float t2, String op) {
		if (t1 instanceof Integer) {
			return compare(((Integer)t1).intValue(), t2, op);
		} else if (t1 instanceof Long) {
			return compare(((Long)t1).longValue(), t2, op);
		} else if (t1 instanceof Float) {
			return compare(((Float)t1).floatValue(), t2, op);
		} else if (t1 instanceof Double) {
			return compare(((Double)t1).doubleValue(), t2, op);
		} else if (t1 instanceof Boolean) {
			return compare(((Boolean)t1).booleanValue(), t2, op);
		} else {
			return false;
		}
	}
	
	public boolean compare(double t1, Object t2, String op) {
		if (t2 instanceof Integer) {
			return compare(t1, ((Integer)t2).intValue(), op);
		} else if (t2 instanceof Long) {
			return compare(t1, ((Long)t2).longValue(), op);
		} else if (t2 instanceof Float) {
			return compare(t1, ((Float)t2).floatValue(), op);
		} else if (t2 instanceof Double) {
			return compare(t1, ((Double)t2).doubleValue(), op);
		} else if ( t2 instanceof Boolean) {
			return compare(t1, ((Boolean)t2).booleanValue(), op);
		} else {
			return false;
		}
	}
	
	public boolean compare(Object t1, double t2, String op) {
		if (t1 instanceof Integer) {
			return compare(((Integer)t1).intValue(), t2, op);
		} else if (t1 instanceof Long) {
			return compare(((Long)t1).longValue(), t2, op);
		} else if (t1 instanceof Float) {
			return compare(((Float)t1).floatValue(), t2, op);
		} else if (t1 instanceof Double) {
			return compare(((Double)t1).doubleValue(), t2, op);
		} else if (t1 instanceof Boolean) {
			return compare(((Boolean)t1).booleanValue(), t2, op);
		} else {
			return false;
		}
	}
	
	public boolean compare(boolean t1, Object t2, String op) {
		if (t2 instanceof Integer) {
			return compare(t1, ((Integer)t2).intValue(), op);
		} else if (t2 instanceof Long) {
			return compare(t1, ((Long)t2).longValue(), op);
		} else if (t2 instanceof Float) {
			return compare(t1, ((Float)t2).floatValue(), op);
		} else if (t2 instanceof Double) {
			return compare(t1, ((Double)t2).doubleValue(), op);
		} else if ( t2 instanceof Boolean) {
			return compare(t1, ((Boolean)t2).booleanValue(), op);
		} else {
			return false;
		}
	}
	
	public boolean compare(Object t1, boolean t2, String op) {
		if (t1 instanceof Integer) {
			return compare(((Integer)t1).intValue(), t2, op);
		} else if (t1 instanceof Long) {
			return compare(((Long)t1).longValue(), t2, op);
		} else if (t1 instanceof Float) {
			return compare(((Float)t1).floatValue(), t2, op);
		} else if (t1 instanceof Double) {
			return compare(((Double)t1).doubleValue(), t2, op);
		} else if (t1 instanceof Boolean) {
			return compare(((Boolean)t1).booleanValue(), t2, op);
		} else {
			return false;
		}
	}
	
	public boolean compare(int t1, int t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(int t1, long t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(long t1, int t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(int t1, float t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(float t1, int t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(int t1, double t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(double t1, int t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(int t1, boolean t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(boolean t1, int t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(long t1, long t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(long t1, float t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(float t1, long t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(long t1, double t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(double t1, long t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(long t1, boolean t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(boolean t1, long t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(float t1, float t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(float t1, double t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(double t1, float t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(float t1, boolean t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(boolean t1, float t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(double t1, double t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else if(op.equals(">")) {
			return t1 > t2;
		} else if(op.equals("<")) {
			return t1 < t2;
		} else if(op.equals(">=")) {
			return t1 >= t2;
		} else if(op.equals("<=")) {
			return t1 <= t2;
		} else {
			return false;
		}
	}
	
	public boolean compare(double t1, boolean t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(boolean t1, double t2, String op) {
		if(op.equals("==")) {
			return false;
		} else if(op.equals("&&")) {
			return false;
		} else if(op.equals("||")) {
			return false;
		} else {
			return false;
		}
	}
	
	public boolean compare(boolean t1, boolean t2, String op) {
		if(op.equals("==")) {
			return t1 == t2;
		} else if(op.equals("!=")) {
			return t1 != t2;
		} else if(op.equals("&&")) {
			return t1 && t2;
		} else if(op.equals("||")) {
			return t1 || t2;
		} else {
			return false;
		}
	}

}
