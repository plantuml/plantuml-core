package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.sequencediagram.InGroupable;
import net.sourceforge.plantuml.sequencediagram.InGroupableList;

class InGroupablesStack {

	final private List<InGroupableList> inGroupableStack = new ArrayList<>();

	public void addList(InGroupableList inGroupableList) {
		for (InGroupableList other : inGroupableStack) {
			other.addInGroupable(inGroupableList);
		}
		inGroupableStack.add(inGroupableList);

	}

	public void pop() {
		final int idx = inGroupableStack.size() - 1;
		inGroupableStack.remove(idx);
	}

	public void addElement(InGroupable inGroupable) {
		for (InGroupableList groupingStructure : inGroupableStack) {
			groupingStructure.addInGroupable(inGroupable);
		}
	}

	public InGroupableList getTopGroupingStructure() {
		if (inGroupableStack.size() == 0) {
			return null;
		}
		return inGroupableStack.get(inGroupableStack.size() - 1);
	}

}
