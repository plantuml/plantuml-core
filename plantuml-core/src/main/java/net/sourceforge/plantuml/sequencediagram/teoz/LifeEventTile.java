package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.real.RealUtils;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.LifeEvent;
import net.sourceforge.plantuml.sequencediagram.LifeEventType;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class LifeEventTile extends AbstractTile {

	private final LifeEvent lifeEvent;
	private final TileArguments tileArguments;
	private final LivingSpace livingSpace;
	private final Rose skin;
	private final ISkinParam skinParam;
	private final YGauge yGauge;

	@Override
	final protected void callbackY_internal(TimeHook y) {
		super.callbackY_internal(y);
		// System.err.println("LifeEventTile::updateStairs " + lifeEvent + " " +
		// livingSpace.getParticipant() + " y=" + y);
		livingSpace.addStepForLivebox(getEvent(), y.getValue());
	}

	public Event getEvent() {
		return lifeEvent;
	}

	@Override
	public double getContactPointRelative() {
		return 0;
	}

	public LifeEventTile(LifeEvent lifeEvent, TileArguments tileArguments, LivingSpace livingSpace, Rose skin,
			ISkinParam skinParam, YGauge currentY) {
		super(tileArguments.getStringBounder(), currentY);
		this.lifeEvent = lifeEvent;
		this.tileArguments = tileArguments;
		this.livingSpace = livingSpace;
		this.skin = skin;
		this.skinParam = skinParam;
		this.yGauge = YGauge.create(currentY.getMax(), getPreferredHeight());
	}

	@Override
	public YGauge getYGauge() {
		return yGauge;
	}

	private StyleSignatureBasic getStyleSignature() {
		return ComponentType.DESTROY.getStyleSignature();
	}

	public void drawU(UGraphic ug) {
		if (YGauge.USE_ME)
			ug = ug.apply(UTranslate.dy(getYGauge().getMin().getCurrentValue()));
		if (isDestroyWithoutMessage()) {
			final Style style = getStyleSignature().getMergedStyle(skinParam.getCurrentStyleBuilder());
			final Component cross = skin.createComponent(new Style[] { style }, ComponentType.DESTROY, null, skinParam,
					null);
			final XDimension2D dimCross = cross.getPreferredDimension(ug.getStringBounder());
			final double x = livingSpace.getPosC(ug.getStringBounder()).getCurrentValue();
			cross.drawU(ug.apply(UTranslate.dx(x - dimCross.getWidth() / 2)), null, (Context2D) ug);
		}
	}

	public boolean isDestroyWithoutMessage() {
		return lifeEvent.getMessage() == null && lifeEvent.getType() == LifeEventType.DESTROY;
	}

	public double getPreferredHeight() {
//		if (lifeEvent.isActivate()) {
//			return 20;
//		}
		if (isDestroyWithoutMessage()) {
			final Component cross = skin.createComponent(null, ComponentType.DESTROY, null, skinParam, null);
			final XDimension2D dimCross = cross.getPreferredDimension(getStringBounder());
			return dimCross.getHeight();
		}
		return 0;
	}

	public void addConstraints() {
	}

	public Real getMinX() {
		// return tileArguments.getLivingSpace(lifeEvent.getParticipant()).getPosB();
		return livingSpace.getPosB(getStringBounder());
	}

	public Real getMaxX() {
		// final LivingSpace livingSpace2 =
		// tileArguments.getLivingSpace(lifeEvent.getParticipant());
		return RealUtils.max(livingSpace.getPosD(getStringBounder()), livingSpace.getPosC2(getStringBounder()));
	}

}
