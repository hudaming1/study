package org.hum.study.datastruct.stack;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.hum.study.datastruct.stack.StackUtils.Stack;

public class 表达式运算优先级 {
	
	private static final Pattern NumberPattern = Pattern.compile("[0-9]");
	private static final Pattern OpPattern = Pattern.compile("[+-[\\*][\\/]]");

	static enum Op {
		Plus('+', 10), Minus('-', 10), Multiply('*', 20), Devide('/', 20);
		public char op;
		public int priority;
		Op(char op, int priority) {
			this.op = op;
			this.priority = priority;
		}
		public BigDecimal calc(BigDecimal num1, BigDecimal num2) {
			if (num1 == null || num2 == null) {
				throw new IllegalArgumentException("param mustn't be null");
			}
			if (op == Plus.op) {
				return num1.add(num2);
			} else if (op == Minus.op) {
				return num1.subtract(num2);
			} else if (op == Multiply.op) {
				return num1.multiply(num2);
			} else if (op == Devide.op) {
				return num1.divide(num2).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			} else {
				throw new UnsupportedOperationException("oper expresion[" + op + "]");
			}
		}
		public static Op getEnum(char op) {
			for (Op opEnum : values()) {
				if (opEnum.op == op) {
					return opEnum;
				}
			}
			throw new IllegalArgumentException("op[" + op + "] is invaild");
		}
	}
	
	/**
	 * <pre>
	 * 	1+1*2
	 * 	3*4+2/2
	 * 	13*12-19
	 * 	123-09*3
	 * </pre>
	 * @param expresion
	 * @return
	 */
	public static BigDecimal calc(String expresion) {
		
		if (expresion == null || expresion.trim().equals("")) {
			throw new IllegalArgumentException("expresion mustn't be null");
		}
		
		StringBuilder numBuilder = new StringBuilder();
		Stack<BigDecimal> numberStack = new StackUtils.ArrStack<>();
		Stack<Op> opStack = new StackUtils.ArrStack<>();
		
		for (char ch : expresion.toCharArray()) {
			// 匹配到数字
			if (NumberPattern.matcher(String.valueOf(ch)).matches()) {
				numBuilder.append(ch);
			}
			// 匹配到符号
			else if (OpPattern.matcher(String.valueOf(ch)).matches()) {
				numberStack.push(new BigDecimal(numBuilder.toString()));
				numBuilder = new StringBuilder();
				if (opStack.isEmpty()) {
					opStack.push(Op.getEnum(ch));
					continue;
				}
				// 如果栈内已经存在运算符，则需要判断是否需要进行计算
				if (opStack.poll().priority < Op.getEnum(ch).priority) {
					opStack.push(Op.getEnum(ch));
					continue;
				}
				// 满足计算条件
				Op op = opStack.pop();
				BigDecimal num2 = numberStack.pop();
				BigDecimal num1 = numberStack.pop();
				numberStack.push(op.calc(num1, num2));
				opStack.push(Op.getEnum(ch));
			} else {
				throw new IllegalArgumentException("expresion(" + expresion + ") is invaild, unknown charset '" + ch + "' ");
			}
		}
		
		// 最后肯定是要以数字结尾
		if (numBuilder.length() == 0) {
			throw new IllegalArgumentException("expresion(" + expresion + ") is invaild");
		}
		numberStack.push(new BigDecimal(numBuilder.toString()));
		
		while (!opStack.isEmpty()) {
			Op op = opStack.pop();
			
			if (numberStack.size() < 2) {
				throw new IllegalArgumentException("expresion(" + expresion + ") is invaild");
			}
			
			BigDecimal num2 = numberStack.pop();
			BigDecimal num1 = numberStack.pop();
			
			numberStack.push(op.calc(num1, num2));
		}
		
		if (numberStack.size() != 1) {
			throw new IllegalArgumentException("expresion(" + expresion + ") is invaild");
		}
		
		return numberStack.pop();
	}
	
	public static void main(String[] args) {
		System.out.println(calc("12+1"));
		System.out.println(calc("3+2+3+2+2+1"));
		System.out.println(calc("1+1"));
		System.out.println(calc("1+1+2*3"));
		System.out.println(calc("1+2*3"));
		System.out.println(calc("1+2*3-1"));
		System.out.println(calc("1+2*3*2"));
		System.out.println(calc("1+2*3+1*4/2"));
	}
}
