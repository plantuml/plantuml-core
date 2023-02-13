package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorate;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class FtileWithConnection extends FtileDecorate {

	private final List<Connection> connections = new ArrayList<>();

	FtileWithConnection(Ftile ftile, Collection<Connection> connections) {
		super(ftile);
		if (Objects.requireNonNull(connections).size() == 0) {
			throw new IllegalArgumentException();
		}
		this.connections.addAll(connections);
	}

	@Override
	public String toString() {
		return super.toString() + " " + connections;
	}

	public FtileWithConnection(Ftile ftile, Connection connection) {
		this(ftile, Arrays.asList(Objects.requireNonNull(connection)));
	}

	public void drawU(UGraphic ug) {
		getFtileDelegated().drawU(ug);
		for (Connection c : connections) {
			ug.draw(c);
		}
	}

	public Collection<Connection> getInnerConnections() {
		final List<Connection> result = new ArrayList<>(super.getInnerConnections());
		result.addAll(connections);
		return Collections.unmodifiableList(connections);
	}

}
