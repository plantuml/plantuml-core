package net.sourceforge.plantuml.svek;

public final class PortGeometry implements Comparable<PortGeometry> {

	private final String id;
	private final double position;
	private final double height;
	private final int score;

	public PortGeometry(String id, double position, double height, int score) {
		this.id = id;
		this.position = position;
		this.height = height;
		this.score = score;
	}

	public PortGeometry translateY(double deltaY) {
		return new PortGeometry(id, position + deltaY, height, score);
	}

	@Override
	public String toString() {
		return "pos=" + position + " height=" + height + " (" + score + ")";
	}

	public double getHeight() {
		return height;
	}

	public double getPosition() {
		return position;
	}

	public double getLastY() {
		return position + height;
	}

	public int getScore() {
		return score;
	}

	public String getId() {
		return id;
	}

	@Override
	public int compareTo(PortGeometry other) {
		return Double.compare(this.position, other.position);
	}

}
