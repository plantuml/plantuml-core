package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public abstract class CommonTile implements Tile, UDrawable {

	private final StringBounder stringBounder;
	private TimeHook y = new TimeHook(-1);

	public CommonTile(StringBounder stringBounder) {
		this.stringBounder = stringBounder;
	}

	final public void callbackY(TimeHook y) {
		if (YGauge.USE_ME) {
		} else {
			this.y = y;
			callbackY_internal(y);
		}
	}

	protected void callbackY_internal(TimeHook y) {
		if (YGauge.USE_ME) {
			System.err.println("callbackY_internal::y=" + y + " gauge=" + getYGauge() + " " + getClass());
		}
	}

	protected final StringBounder getStringBounder() {
		return stringBounder;
	}

	final public double getMiddleX() {
		final double max = getMaxX().getCurrentValue();
		final double min = getMinX().getCurrentValue();
		return (min + max) / 2;
	}

	public final TimeHook getTimeHook() {
		if (YGauge.USE_ME) {
			throw new IllegalStateException();
		}
		return y;
	}

}
