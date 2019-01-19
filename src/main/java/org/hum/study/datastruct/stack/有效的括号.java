package org.hum.study.datastruct.stack;

import org.hum.study.datastruct.stack.StackUtils.Stack;

public class 有效的括号 {
	
	private static boolean isBracketVaild(String exp) {
		if (exp == null || exp.isEmpty()) {
			throw new IllegalArgumentException("param is null");
		}
		
		Stack<Character> stack = new StackUtils.LinkedStack<>();
		
		for (char ch : exp.toCharArray()) {
			if (ch == '(' || ch == '[' || ch == '{') {
				stack.push(ch);
				continue;
			}
			// 如果匹配成功，则pop出来
			if (BracketsEnum.isMatch(stack.poll(), ch)) {
				stack.pop();
			} else {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isBracketVaild("()"));
		System.out.println(isBracketVaild("[]()"));
		System.out.println(isBracketVaild("[()]"));
		System.out.println(isBracketVaild("{[()]}"));
		System.out.println(isBracketVaild("{[(]}"));
	}
	
	private static enum BracketsEnum {
		Left1('(', ')'),
		Left2('[', ']'),
		Left3('{', '}'),
		Right1(')', '('),
		Right2(']', '['),
		Right3('}', '{'),
		;
		public char symbol;
		public char matchSymbol;
		BracketsEnum(char symbol, char matchSymbol) {
			this.symbol = symbol;
			this.matchSymbol = matchSymbol;
		}
		
		public static boolean isMatch(char ch, char ch2) {
			for (BracketsEnum enumType : values()) {
				if (enumType.symbol == ch && enumType.matchSymbol == ch2) {
					return true;
				}
			}
			return false;
		}
	}
}
