package net.sourceforge.plantuml.ugraphic;

import java.awt.image.AffineTransformOp;

public enum AffineTransformType {
	TYPE_NEAREST_NEIGHBOR, TYPE_BILINEAR;

	public int toLegacyInt() {
		switch (this) {
		case TYPE_BILINEAR:
			return AffineTransformOp.TYPE_BILINEAR;
		case TYPE_NEAREST_NEIGHBOR:
			return AffineTransformOp.TYPE_NEAREST_NEIGHBOR;
		}
		throw new AssertionError();
	}

}
