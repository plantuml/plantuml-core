package net.sourceforge.plantuml.utils;

import java.util.Objects;



public class LineLocationImpl implements LineLocation {

	private final String desc;
	private final int position;
	private final LineLocation parent;

	@Override
	public String toString() {
		return desc + " : " + position;
	}

	public LineLocationImpl(String desc, LineLocation parent) {
		this(desc, parent, -1);
	}

	
	private LineLocationImpl(String desc, LineLocation parent, int position) {
		this.parent = parent;
		this.desc = Objects.requireNonNull(desc);
		this.position = position;
	}

	public LineLocationImpl oneLineRead() {
		return new LineLocationImpl(desc, parent, position + 1);
	}

	public int getPosition() {
		return position;
	}

	public String getDescription() {
		return desc;
	}

	public LineLocation getParent() {
		return parent;
	}

	private boolean isStandardLibrary() {
		return desc.startsWith("<");
	}

	public int compareTo(LineLocation other) {
		final LineLocationImpl other2 = (LineLocationImpl) other;
		if (this.isStandardLibrary() && other2.isStandardLibrary() == false) {
			return -1;
		}
		if (this.isStandardLibrary() == false && other2.isStandardLibrary()) {
			return 1;
		}
		return this.position - other2.position;
	}

}
