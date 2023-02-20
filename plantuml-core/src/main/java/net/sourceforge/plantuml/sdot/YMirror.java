package net.sourceforge.plantuml.sdot;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public class YMirror {

	private final double max;

	public YMirror(double max) {
		this.max = max;
	}

	public double getMirrored(double v) {
		if (v < 0 || v > max) {
			System.err.println("BAD VALUE IN YMirror");
		}
		return max - v;
	}

	public XPoint2D getMirrored(XPoint2D pt) {
		// return pt;
		return new XPoint2D(pt.getX(), max - pt.getY());
	}

	public DotPath getMirrored(DotPath path) {
		DotPath result = new DotPath();
		for (XCubicCurve2D bez : path.getBeziers()) {
			result = result.addCurve(getMirrored(bez.getP1()), getMirrored(bez.getCtrlP1()),
					getMirrored(bez.getCtrlP2()), getMirrored(bez.getP2()));
		}
		return result;
	}

	public UTranslate getMirrored(UTranslate tr) {
		return new UTranslate(tr.getDx(), max - tr.getDy());
		// return tr;
	}

}
