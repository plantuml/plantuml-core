package net.sourceforge.plantuml.klimt.geom;

import java.awt.geom.PathIterator;
import java.util.EnumSet;

public enum USegmentType {

	SEG_MOVETO(PathIterator.SEG_MOVETO), //
	SEG_LINETO(PathIterator.SEG_LINETO), //
	SEG_QUADTO(PathIterator.SEG_QUADTO), //
	SEG_CUBICTO(PathIterator.SEG_CUBICTO), //
	SEG_CLOSE(PathIterator.SEG_CLOSE), //
	SEG_ARCTO(4321);//

	final public static int SEG_ARCTO_VALUE = 4321;

	private final int code;

	private USegmentType(int code) {
		this.code = code;
	}

	public int getNbPoints() {
		switch (this) {
		case SEG_MOVETO:
			return 1;
		case SEG_LINETO:
			return 1;
		case SEG_CUBICTO:
			return 3;
		case SEG_CLOSE:
			return 0;
		}
		throw new UnsupportedOperationException();
	}

	public static USegmentType getByCode(int code) {
		for (USegmentType p : EnumSet.allOf(USegmentType.class))
			if (p.code == code)
				return p;

		throw new IllegalArgumentException();
	}
}
