package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ConnectionCross extends AbstractConnection {

	private final Connection connection;

	public ConnectionCross(Connection connection) {
		super(connection.getFtile1(), connection.getFtile2());
		this.connection = connection;
	}

	public void drawU(UGraphic ug) {
		if (connection instanceof ConnectionTranslatable) {
			final ConnectionTranslatable conn = (ConnectionTranslatable) connection;

			final Swimlane swimlane1 = getFtile1().getSwimlaneOut();
			final Swimlane swimlane2 = getFtile2().getSwimlaneIn();
			if (swimlane1 == null) {
				return;
				// throw new IllegalStateException("" + getFtile1().getClass());
			}
			if (swimlane2 == null) {
				return;
				// throw new IllegalStateException("" + getFtile2().getClass());
			}
			conn.drawTranslate(ug, swimlane1.getTranslate(), swimlane2.getTranslate());
		}
	}
}
