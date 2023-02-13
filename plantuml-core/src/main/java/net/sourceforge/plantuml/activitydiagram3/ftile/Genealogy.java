package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class Genealogy {

	private Map<Ftile, Ftile> myFatherIs = new HashMap<Ftile, Ftile>();
	private final Ftile root;

	public Genealogy(Ftile root) {
		this.root = root;
		process(root);
		// System.err.println("myFatherIs=" + myFatherIs);
	}

	private void process(Ftile current) {
		final Collection<Ftile> children = current.getMyChildren();
		// System.err.println("current=" + current);
		// System.err.println("children=" + children);
		for (Ftile child : children) {
			setMyFather(child, current);
			process(child);
		}
	}

	public Ftile getMyFather(Ftile me) {
		return myFatherIs.get(me);
	}

	private void setMyFather(Ftile child, Ftile father) {
		if (myFatherIs.containsKey(child)) {
			throw new IllegalArgumentException();
		}
		myFatherIs.put(child, father);
	}

	public UTranslate getTranslate(Ftile child, StringBounder stringBounder) {
		Ftile current = child;
		UTranslate result = new UTranslate();
		while (current != root) {
			final Ftile father = getMyFather(current);
			final UTranslate tr = father.getTranslateFor(current, stringBounder);
//			System.err.println("Father=" + father);
//			System.err.println("current=" + current);
//			System.err.println("TR=" + tr);
			result = tr.compose(result);
			current = father;
		}
		return result;
	}

}
