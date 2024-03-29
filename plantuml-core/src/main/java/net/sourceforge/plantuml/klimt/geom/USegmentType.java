// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.geom;

public enum USegmentType {

	SEG_MOVETO, //
	SEG_LINETO, //
	SEG_QUADTO, //
	SEG_CUBICTO, //
	SEG_CLOSE, //
	SEG_ARCTO;//

	final public static int SEG_ARCTO_VALUE = 4321;

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

}
