package net.sourceforge.plantuml.regexdiagram;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class ShuntingYard {

	final private List<ReToken> ouputQueue = new ArrayList<>();
	final private Deque<ReToken> operatorStack = new ArrayDeque<>();

	public ShuntingYard(Iterator<ReToken> it) {
		while (it.hasNext()) {
			final ReToken token = it.next();
//			System.err.println("token=" + token);
//			System.err.println("ouputQueue=" + ouputQueue);
//			System.err.println("operatorStack=" + operatorStack);
			if (token.getType() == ReTokenType.SIMPLE_CHAR) {
				ouputQueue.add(token);
			} else if (token.getType() == ReTokenType.ESCAPED_CHAR) {
				ouputQueue.add(token);
			} else if (token.getType() == ReTokenType.GROUP) {
				ouputQueue.add(token);
			} else if (token.getType() == ReTokenType.CLASS) {
				ouputQueue.add(token);
			} else if (token.getType() == ReTokenType.ANCHOR) {
				ouputQueue.add(token);
			} else if (token.getType() == ReTokenType.QUANTIFIER) {
				ouputQueue.add(token);
			} else if (token.getType() == ReTokenType.CONCATENATION_IMPLICIT) {
				// push it onto the operator stack.
				operatorStack.addFirst(token);
			} else if (token.getType() == ReTokenType.ALTERNATIVE) {
				while (thereIsAConcatenationAtTheTopOfTheOperatorStack())
					ouputQueue.add(operatorStack.removeFirst());
				// push it onto the operator stack.
				operatorStack.addFirst(token);
			} else if (token.getType() == ReTokenType.PARENTHESIS_OPEN) {
				operatorStack.addFirst(token);
			} else if (token.getType() == ReTokenType.PARENTHESIS_CLOSE) {
				while (operatorStack.peekFirst() != null
						&& operatorStack.peekFirst().getType() != ReTokenType.PARENTHESIS_OPEN)
					ouputQueue.add(operatorStack.removeFirst());
				final ReToken first = operatorStack.removeFirst();
//				ouputQueue.add(first);

			} else {
				throw new UnsupportedOperationException(token.toString());
			}

		}

		while (operatorStack.isEmpty() == false) {
			final ReToken token = operatorStack.removeFirst();
			ouputQueue.add(token);
		}

		// System.err.println("ouputQueue=" + ouputQueue);
	}

	private boolean thereIsAConcatenationAtTheTopOfTheOperatorStack() {
		final ReToken top = operatorStack.peekFirst();
		return top != null && top.getType() == ReTokenType.CONCATENATION_IMPLICIT;
	}

	public final List<ReToken> getOuputQueue() {
		return Collections.unmodifiableList(ouputQueue);
	}

}
