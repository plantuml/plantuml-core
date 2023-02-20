package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.UPolygon;

public class ExtremityOther extends Extremity {

	final private UPolygon polygon;

	public ExtremityOther(UPolygon polygon) {
		this.polygon = polygon;
	}

	public void drawU(UGraphic ug) {
		ug.draw(polygon);

	}

	@Override
	public XPoint2D somePoint() {
		return polygon.getPoints().get(0);
	}
}
