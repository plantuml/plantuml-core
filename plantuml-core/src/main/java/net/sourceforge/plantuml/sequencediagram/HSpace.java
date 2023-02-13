package net.sourceforge.plantuml.sequencediagram;

public class HSpace extends AbstractEvent implements Event {

	private final int pixel;

	public HSpace(int pixel) {
		this.pixel = pixel;
	}

	public int getPixel() {
		return pixel;
	}

	public boolean dealWith(Participant someone) {
		return false;
	}

}
