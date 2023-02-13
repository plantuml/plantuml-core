package net.sourceforge.plantuml.creole;

import java.util.ArrayList;
import java.util.List;

public class CreoleContext {

	private final List<Integer> stack = new ArrayList<>();

	public int getLocalNumber(int order) {
		ensureStackOk(order);
		final int n = stack.get(order);
		stack.set(order, n + 1);
		return n;
	}

	private void ensureStackOk(int order) {
		while (stack.size() <= order) {
			stack.add(0);
		}
		while (stack.size() > order + 1) {
			stack.remove(order + 1);
		}

	}

}
