package net.sourceforge.plantuml.posimo;

import java.util.Objects;

import net.sourceforge.plantuml.klimt.shape.DotPath;

public class Path {

	private final Label label;
	private final Block start;
	private final Block end;
	private final int length;
	private DotPath dotPath;
	private final boolean invis;

	public Path(Block start, Block end, Label label) {
		this(start, end, label, 2, false);
	}

	public Path(Block start, Block end, Label label, int length, boolean invis) {
		if (length < 1) {
			throw new IllegalArgumentException("length=" + length);
		}
		this.invis = invis;
		this.start = Objects.requireNonNull(start);
		this.end = Objects.requireNonNull(end);
		this.label = label;
		this.length = length;
	}

	public final Label getLabel() {
		return label;
	}

	public final Block getStart() {
		return start;
	}

	public final Block getEnd() {
		return end;
	}

	public void setLabelPositionCenter(double labelX, double labelY) {
		label.setCenterX(labelX);
		label.setCenterY(labelY);
	}

	public void setLabelPosition(double x, double y) {
		label.setX(x);
		label.setY(y);
	}

	public void setDotPath(DotPath dotPath) {
		this.dotPath = dotPath;

	}

	public final DotPath getDotPath() {
		return dotPath;
	}

	public int getLength() {
		return length;
	}

	public final boolean isInvis() {
		return invis;
	}

}
