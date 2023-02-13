package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;

public class FtileUtils {

	public static Ftile addConnection(Ftile ftile, Connection connection) {
		return new FtileWithConnection(ftile, connection);
	}

	public static Ftile addConnection(Ftile ftile, Collection<Connection> connections) {
		if (connections.size() == 0) {
			return ftile;
		}
		return new FtileWithConnection(ftile, connections);
	}

	public static Ftile withSwimlaneOut(Ftile ftile, Swimlane out) {
		return new FtileWithSwimlanes(ftile, ftile.getSwimlaneIn(), out);
	}

	public static Ftile addBottom(Ftile ftile, double marginBottom) {
		return new FtileMargedVertically(ftile, 0, marginBottom);
	}

	public static Ftile addVerticalMargin(Ftile ftile, double marginTop, double marginBottom) {
		if (marginTop == 0 && marginBottom == 0) {
			return ftile;
		}
		return new FtileMargedVertically(ftile, marginTop, marginBottom);
	}

	public static Ftile addHorizontalMargin(Ftile ftile, double margin1, double margin2) {
		if (margin1 == 0 && margin2 == 0) {
			return ftile;
		}
		return new FtileMarged(ftile, margin1, margin2);
	}

	public static Ftile addHorizontalMargin(Ftile ftile, double margin) {
		if (margin == 0) {
			return ftile;
		}
		return new FtileMarged(ftile, margin, margin);
	}

	// public static Ftile addHorizontalMargin(Ftile ftile, double margin) {
	// return new FtileMarged(ftile, margin);
	// }

	// private static Ftile neverNull(Ftile ftile, ISkinParam skinParam) {
	// if (ftile == null) {
	// return new FtileEmpty(skinParam);
	// }
	// return ftile;
	// }

}
