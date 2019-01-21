package org.hum.study.datastruct.stack;

import org.hum.study.datastruct.stack.StackUtils.Stack;

public class 汉诺塔 {

	static class Hanoi {
		private Stack<Integer>[] towers;
		public Hanoi(int number) {
			towers = new StackUtils.ArrStack[number];
		}

		public void print() {
			for (int i = 0; i < towers.length; i++) {

			}
		}
	}
	
	static class Uitl {
		
		public static Stack<Integer> createShuffleTower() {
			return null;
		}

		public static Stack<Integer> createSortedTower(Integer maxSize) {
			Stack<Integer> tower = new StackUtils.ArrStack<>();
			for (int i = 1; i <= maxSize; i++) {
				tower.push(i);
			}
			return tower;
		}
	}

	public static void main(String[] args) {
	}
}
