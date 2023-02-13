package net.sourceforge.plantuml.gitlog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.cucadiagram.Display;

public class GNode {

	private final List<GNode> up = new ArrayList<>();
	private final List<GNode> down = new ArrayList<>();
	private final List<String> texts = new ArrayList<>();

	private String comment;

	public void addText(String text) {
		this.texts.add(text);
	}

	public boolean isTop() {
		return up.size() == 0;
	}

	public final String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static void link(GNode n1, GNode n2) {
		n1.down.add(n2);
		n2.up.add(n1);
	}

	@Override
	public String toString() {
		return texts.toString();
	}

	public Display getDisplay() {
		return Display.create(texts);
	}

	public Collection<GNode> getDowns() {
		return Collections.unmodifiableCollection(down);
	}

	public boolean canEatTheNextOne() {
		if (up.size() != 1)
			return false;

		if (down.size() != 1)
			return false;

		final GNode next = down.get(0);
		if (next.up.size() != 1)
			return false;

		if (next.down.size() != 1)
			return false;

		return true;
	}

	public GNode eatTheNextOne() {
		if (canEatTheNextOne() == false)
			throw new IllegalStateException();

		final GNode removed = down.get(0);
		final GNode newNext = removed.down.get(0);
		this.texts.addAll(removed.texts);
		this.down.set(0, newNext);
		if (newNext.up.remove(removed) == false)
			throw new IllegalStateException();

		newNext.up.add(this);
		return removed;
	}

}
