// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Connection;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicDelegator;
import net.sourceforge.plantuml.klimt.shape.ULine;

public class UGraphicInterceptorOneSwimlane extends UGraphicDelegator {

	private final Swimlane swimlane;
	private final List<Swimlane> orderedList;

	public UGraphicInterceptorOneSwimlane(UGraphic ug, Swimlane swimlane, List<Swimlane> orderedList) {
		super(ug);
		this.swimlane = swimlane;
		this.orderedList = orderedList;
	}

	public void draw(UShape shape) {
		// System.err.println("inter=" + shape.getClass());
		if (shape instanceof Ftile) {
			final Ftile tile = (Ftile) shape;
			final Set<Swimlane> swinlanes = tile.getSwimlanes();
			final boolean contained = swinlanes.contains(swimlane);
			if (contained) {
				tile.drawU(this);
				// drawGoto();
			}
		} else if (shape instanceof Connection) {
			final Connection connection = (Connection) shape;
			final Ftile tile1 = connection.getFtile1();
			final Ftile tile2 = connection.getFtile2();
			final boolean contained1 = tile1 == null || tile1.getSwimlaneOut() == null
					|| tile1.getSwimlaneOut() == swimlane;
			final boolean contained2 = tile2 == null || tile2.getSwimlaneIn() == null
					|| tile2.getSwimlaneIn() == swimlane;

			if (contained1 && contained2) {
				connection.drawU(this);
			}
		} else {
			getUg().draw(shape);
			// System.err.println("Drawing " + shape);
		}

	}

	private void drawGoto() {
		final UGraphic ugGoto = getUg().apply(HColors.GREEN).apply(HColors.GREEN.bg());
		ugGoto.draw(new ULine(100, 100));
	}

	public UGraphic apply(UChange change) {
		return new UGraphicInterceptorOneSwimlane(getUg().apply(change), swimlane, orderedList);
	}

	public final Swimlane getSwimlane() {
		return swimlane;
	}

	public final List<Swimlane> getOrderedListOfAllSwimlanes() {
		return Collections.unmodifiableList(orderedList);
	}

}
