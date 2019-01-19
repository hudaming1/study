package org.hum.study.datastruct.stack;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.hum.study.datastruct.stack.StackUtils.Stack;

public class 表达式运算优先级 {
	
	// 匹配数字和小数点
	private static final Pattern NumberPattern = Pattern.compile("[0-9[\\.]]");
	// 匹配运算符
	private static final Pattern OpPattern = Pattern.compile("[+-[\\*][\\/]]");
	// 匹配括号运算符
	private static final Pattern BracketsPattern = Pattern.compile("[[\\(][\\)]]");

	static enum OpEnum {
		Plus('+', 10), Minus('-', 10), Multiply('*', 20), Devide('/', 20);
		public char op;
		public int priority;
		OpEnum(char op, int priority) {
			this.op = op;
			this.priority = priority;
		}
		public BigDecimal calc(Stack<BigDecimal> numStack) {
			if (numStack.size() < 2) {
				throw new IllegalArgumentException("expresion is invaild");
			}
			BigDecimal num2 = numStack.pop();
			BigDecimal num1 = numStack.pop();
			return calc(num1, num2);
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
		public static OpEnum getEnum(char op) {
			for (OpEnum opEnum : values()) {
				if (opEnum.op == op) {
					return opEnum;
				}
			}
			throw new IllegalArgumentException("op[" + op + "] is invaild");
		}
	}
	
	static enum BracketsEnum {
		Left('('), Right(')');
		public char symbol;
		BracketsEnum(char symbol) {
			this.symbol = symbol;
		}
		public static BracketsEnum getEnum(char c) {
			for (BracketsEnum bracketsEnum : values()) {
				if (bracketsEnum.symbol == c) {
					return bracketsEnum;
				}
			}
			throw new IllegalArgumentException("symbol" + c + "] is invaild");
		}
	}
	
	/**
	 * <pre>
	 * 	1+1*2.2
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
		
		StringBuilder numTemp = new StringBuilder(); // 用户临时存储多位数字
		Stack<BigDecimal> numberStack = new StackUtils.ArrStack<>();
		Stack<Object> opStack = new StackUtils.ArrStack<>();
		
		/** 1.将操作数和运算符入栈 **/
		for (char ch : expresion.toCharArray()) {
			// 匹配到数字
			if (NumberPattern.matcher(String.valueOf(ch)).matches()) {
				numTemp.append(ch);
			}
			// 匹配到符号
			else if (OpPattern.matcher(String.valueOf(ch)).matches()) {
				// 遇到操作符，则将前几位的字符拼成一个数字压入栈
				if (numTemp != null && numTemp.length() > 0) {
					numberStack.push(new BigDecimal(numTemp.toString()));
					numTemp = new StringBuilder();
				}
				// 如果栈内已经存在运算符，则需要判断是否需要进行计算
				if (!opStack.isEmpty() && opStack.poll() instanceof BracketsEnum) {
					opStack.push(OpEnum.getEnum(ch));
					continue;
				}
				if (opStack.isEmpty() || ((OpEnum) opStack.poll()).priority < OpEnum.getEnum(ch).priority) {
					opStack.push(OpEnum.getEnum(ch));
					continue;
				}
				// 满足计算条件
				OpEnum op = (OpEnum) opStack.pop();
				BigDecimal num2 = numberStack.pop();
				BigDecimal num1 = numberStack.pop();
				numberStack.push(op.calc(num1, num2));
				opStack.push(OpEnum.getEnum(ch));
			}
			// 匹配括号
			else if (BracketsPattern.matcher(String.valueOf(ch)).matches()) {
				// 遇到操作符，则将前几位的字符拼成一个数字压入栈
				if (numTemp != null && numTemp.length() > 0) {
					numberStack.push(new BigDecimal(numTemp.toString()));
					numTemp = new StringBuilder();
				}
				BracketsEnum brackets = BracketsEnum.getEnum(ch);
				if (brackets == BracketsEnum.Left) {
					opStack.push(brackets);
				} else if (brackets == BracketsEnum.Right) {
					while (opStack.poll() != BracketsEnum.Left) {
						OpEnum op = (OpEnum) opStack.pop();
						numberStack.push(op.calc(numberStack));
					}
					
					opStack.pop(); // 弹出"("
				} else {
					throw new UnsupportedOperationException(ch + " is not support!");
				}
			} else {
				// throw 放开，可过滤无效字符，但不推荐这么做，追求的还是精准表达式
				throw new IllegalArgumentException("expresion(" + expresion + ") is invaild, unknown charset '" + ch + "' ");
			}
		}
		
		/** 2.最后肯定是要以数字结尾 **/
		if (numTemp != null && numTemp.length() > 0) {
			numberStack.push(new BigDecimal(numTemp.toString()));
			numTemp = null;
		}
		
		/** 3.反向弹出操作数，开始进行计算 **/
		while (!opStack.isEmpty()) {
			OpEnum op = (OpEnum) opStack.pop();
			numberStack.push(op.calc(numberStack));
		}
		
		// 最后一次校验
		if (numberStack.size() != 1) {
			throw new IllegalArgumentException("expresion(" + expresion + ") is invaild");
		}
		
		return numberStack.pop();
	}
	
	public static void main(String[] args) {
		System.out.println("1+1=" + calc("1+1"));
		System.out.println("1.2+1.12=" + calc("1.2+1.12"));
		System.out.println("3+2+3+2+2+1=" + calc("3+2+3+2+2+1"));
		System.out.println("1+1+2.3*3=" + calc("1+1+2.3*3"));
		System.out.println("1+2*3=" + calc("1+2*3"));
		System.out.println("1+2*3-1=" + calc("1+2*3-1"));
		System.out.println("1+2*3*2=" + calc("1+2*3*2"));
		System.out.println("1+2*3+1*4/2=" + calc("1+2*3+1*4/2"));
		System.out.println("1+2*(3+1)=" + calc("1+2*(3+1)"));
		System.out.println("1*2+(3+1)=" + calc("1*2+(3+1)"));
		System.out.println("1+2*(3+1)*4/2=" + calc("1+2*(3+1)*4/2"));
		
		
	}
}
