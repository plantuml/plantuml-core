package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.InGroupable;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.skin.ArrowComponent;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.url.Url;

abstract class Arrow extends GraphicalElement implements InGroupable {

	private final Rose skin;
	private final ArrowComponent arrowComponent;
	private double paddingArrowHead;
	private double maxX;
	private final Url url;

	public void setMaxX(double m) {
		if (maxX != 0) {
			throw new IllegalStateException();
		}
		this.maxX = m;
	}

	final protected double getMaxX() {
		if (maxX == 0) {
			// throw new IllegalStateException();
		}
		return maxX;
	}

	public abstract double getActualWidth(StringBounder stringBounder);

	Arrow(double startingY, Rose skin, ArrowComponent arrowComponent, Url url) {
		super(startingY);
		this.skin = skin;
		this.arrowComponent = arrowComponent;
		this.url = url;
	}

	protected Url getUrl() {
		return url;
	}

	protected final void startUrl(UGraphic ug) {
		if (url != null) {
			ug.startUrl(url);
		}
	}

	protected final void endUrl(UGraphic ug) {
		if (url != null) {
			ug.closeUrl();
		}
	}

	public abstract int getDirection(StringBounder stringBounder);

	protected Rose getSkin() {
		return skin;
	}

	protected final ArrowComponent getArrowComponent() {
		return arrowComponent;
	}

	public double getArrowOnlyWidth(StringBounder stringBounder) {
		return getPreferredWidth(stringBounder);
	}

	public abstract double getArrowYStartLevel(StringBounder stringBounder);

	public abstract double getArrowYEndLevel(StringBounder stringBounder);

	public abstract LivingParticipantBox getParticipantAt(StringBounder stringBounder, NotePosition position);

	protected final double getPaddingArrowHead() {
		return paddingArrowHead;
	}

	protected final void setPaddingArrowHead(double paddingArrowHead) {
		this.paddingArrowHead = paddingArrowHead;
	}

	final public double getMargin() {
		return 5;
	}

}
