package net.sourceforge.plantuml.tim;

import java.util.Deque;
import java.util.LinkedList;

public abstract class ExecutionContexts {

	private final Deque<ExecutionContextIf> allIfs = new LinkedList<>();
	private final Deque<ExecutionContextWhile> allWhiles = new LinkedList<>();
	private final Deque<ExecutionContextForeach> allForeachs = new LinkedList<>();

	public void addIf(ExecutionContextIf value) {
		allIfs.addLast(value);
	}

	public void addWhile(ExecutionContextWhile value) {
		allWhiles.addLast(value);
	}

	public void addForeach(ExecutionContextForeach value) {
		allForeachs.addLast(value);
	}

	public ExecutionContextIf peekIf() {
		return allIfs.peekLast();
	}

	public ExecutionContextWhile peekWhile() {
		return allWhiles.peekLast();
	}

	public ExecutionContextForeach peekForeach() {
		return allForeachs.peekLast();
	}

	public ExecutionContextIf pollIf() {
		return allIfs.pollLast();
	}

	public ExecutionContextWhile pollWhile() {
		return allWhiles.pollLast();
	}

	public ExecutionContextForeach pollForeach() {
		return allForeachs.pollLast();
	}

	public boolean areAllIfOk(TContext context, TMemory memory) throws EaterException {
		for (ExecutionContextIf conditionalContext : allIfs) {
			if (conditionalContext.conditionIsOkHere() == false) {
				return false;
			}
		}
		return true;
	}

}
