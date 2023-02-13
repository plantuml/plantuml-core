package net.sourceforge.plantuml.api;


import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.core.ImageData;

public abstract class ImageDataAbstract implements ImageData {

	private final int width;
	private final int height;
	private int status;

	public ImageDataAbstract(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	
	public ImageDataAbstract(XDimension2D dim) {
		this((int) dim.getWidth(), (int) dim.getHeight());
	}

	public final int getWidth() {
		return width;
	}

	public final int getHeight() {
		return height;
	}

	public final int getStatus() {
		return status;
	}

	public final void setStatus(int status) {
		this.status = status;
	}

}
