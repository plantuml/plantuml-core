package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.creole.Display;

public final class Page {

	private final double headerHeight;
	private final double newpage1;
	private final double newpage2;
	private final double tailHeight;
	private final double signatureHeight;
	private final Display title;

	@Override
	public String toString() {
		return "headerHeight=" + headerHeight + " newpage1=" + newpage1 + " newpage2=" + newpage2;
	}

	public Page(double headerHeight, double newpage1, double newpage2, double tailHeight, double signatureHeight,
			Display title) {
		if (headerHeight < 0) {
			throw new IllegalArgumentException();
		}
		if (tailHeight < 0) {
			throw new IllegalArgumentException();
		}
		if (signatureHeight < 0) {
			throw new IllegalArgumentException();
		}
		if (newpage1 > newpage2) {
			throw new IllegalArgumentException();
		}
		this.headerHeight = headerHeight;
		this.newpage1 = newpage1;
		this.newpage2 = newpage2;
		this.tailHeight = tailHeight;
		this.signatureHeight = signatureHeight;
		this.title = title;
	}

	public double getHeight() {
		return headerHeight + getBodyHeight() + tailHeight + signatureHeight;
	}

	public double getHeaderRelativePosition() {
		return 0;
	}

	public double getBodyRelativePosition() {
		return getHeaderRelativePosition() + headerHeight;
	}

	public double getBodyHeight() {
		return newpage2 - newpage1;
	}

	public double getTailRelativePosition() {
		return getBodyRelativePosition() + getBodyHeight();
	}

	public double getSignatureRelativePosition() {
		if (displaySignature() == false) {
			return -1;
		}
		return getTailRelativePosition() + tailHeight;
	}

	public boolean displaySignature() {
		return signatureHeight > 0;
	}

	public double getNewpage1() {
		return newpage1;
	}

	public double getNewpage2() {
		return newpage2;
	}

	public double getHeaderHeight() {
		return headerHeight;
	}

	public final Display getTitle() {
		return title;
	}

}
