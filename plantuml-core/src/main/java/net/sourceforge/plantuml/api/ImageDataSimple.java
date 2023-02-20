package net.sourceforge.plantuml.api;

import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class ImageDataSimple extends ImageDataAbstract {

	public ImageDataSimple(int width, int height) {
		super(width, height);
	}

	public ImageDataSimple(XDimension2D dim) {
		super(dim);
	}

	public ImageDataSimple(XDimension2D dim, int status) {
		super(dim);
		setStatus(status);
	}

	private ImageDataSimple() {
		this(0, 0);
	}

	public boolean containsCMapData() {
		return false;
	}

	public String getCMapData(String nameId) {
		throw new UnsupportedOperationException();
	}

	public String getWarningOrError() {
		return null;
	}

	public static ImageData error() {
		final ImageDataSimple result = new ImageDataSimple();
		result.setStatus(503);
		return result;
	}

	public static ImageData ok() {
		return new ImageDataSimple();
	}

}
