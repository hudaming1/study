package org.hum.study.datastruct.stack;

import java.math.BigDecimal;
import java.util.regex.Matcher;
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
		Stack<BigDecimal> numberStack = new StackUtils.ArrStack<>();
		Stack<Op> opStack = new StackUtils.LinkedStack<>();
		
		for (char ch : expresion.toCharArray()) {
			// 匹配到数字
			if (NumberPattern.matcher(String.valueOf(ch)).matches()) {
				
			}
			// 匹配到符号
			else if (OpPattern.matcher(String.valueOf(ch)).matches()) {
				
			} else {
				throw new IllegalArgumentException("unknown charset[" + ch + "] ");
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(OpPattern.matcher("+").matches());
		System.out.println(OpPattern.matcher("-").matches());
		System.out.println(OpPattern.matcher("*").matches());
		System.out.println(OpPattern.matcher("/").matches());
	}
}
