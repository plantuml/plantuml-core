package net.sourceforge.plantuml.klimt.creole;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import net.sourceforge.plantuml.klimt.creole.atom.Atom;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class Sea {

	private double currentX;
	private final Map<Atom, Position> positions = new HashMap<Atom, Position>();
	private final StringBounder stringBounder;

	public Sea(StringBounder stringBounder) {
		this.stringBounder = Objects.requireNonNull(stringBounder);
	}

	public void add(Atom atom) {
		final XDimension2D dim = atom.calculateDimension(stringBounder);
		final double y = 0;
		final Position position = new Position(currentX, y, dim);
		positions.put(atom, position);
		currentX += dim.getWidth();
	}

	public Position getPosition(Atom atom) {
		return positions.get(atom);
	}

	public void doAlign() {
		for (Map.Entry<Atom, Position> ent : new HashMap<Atom, Position>(positions).entrySet()) {
			final Position pos = ent.getValue();
			final Atom atom = ent.getKey();
			final double height = atom.calculateDimension(stringBounder).getHeight();
			final Position newPos = pos.translateY(-height + atom.getStartingAltitude(stringBounder));
			positions.put(atom, newPos);
		}
	}

	public void translateMinYto(double newValue) {
		final double delta = newValue - getMinY();
		for (Map.Entry<Atom, Position> ent : new HashMap<Atom, Position>(positions).entrySet()) {
			final Position pos = ent.getValue();
			final Atom atom = ent.getKey();
			positions.put(atom, pos.translateY(delta));
		}
	}

	public void exportAllPositions(Map<Atom, Position> destination) {
		destination.putAll(positions);
	}

	public double getMinY() {
		if (positions.size() == 0) {
			throw new IllegalStateException();
		}
		double result = Double.MAX_VALUE;
		for (Position pos : positions.values()) {
			if (result > pos.getMinY()) {
				result = pos.getMinY();
			}
		}
		return result;
	}

	public double getMaxY() {
		if (positions.size() == 0) {
			throw new IllegalStateException();
		}
		double result = -Double.MAX_VALUE;
		for (Position pos : positions.values()) {
			if (result < pos.getMaxY()) {
				result = pos.getMaxY();
			}
		}
		return result;
	}

	public double getHeight() {
		return getMaxY() - getMinY();
	}

	public MinMax update(MinMax minMax) {
		for (Position position : positions.values()) {
			minMax = position.update(minMax);
		}
		return minMax;
	}

	public final double getWidth() {
		return currentX;
	}

}
