package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Divider;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.style.ISkinParam;

public class DividerTile extends AbstractTile implements Tile {

	private final Rose skin;
	private final ISkinParam skinParam;
	private final Divider divider;
	private final Real xorigin;
	private final TileArguments tileArguments;
	private final YGauge yGauge;

	public Event getEvent() {
		return divider;
	}

	public DividerTile(Divider divider, TileArguments tileArguments, YGauge currentY) {
		super(tileArguments.getStringBounder(), currentY);
		this.tileArguments = tileArguments;
		this.divider = divider;
		this.skin = tileArguments.getSkin();
		this.skinParam = tileArguments.getSkinParam();
		this.xorigin = tileArguments.getXOrigin();
		this.yGauge = YGauge.create(currentY.getMax(), getPreferredHeight());
	}

	@Override
	public YGauge getYGauge() {
		return yGauge;
	}

	private Component getComponent(StringBounder stringBounder) {
		final Component comp = skin.createComponent(divider.getUsedStyles(), ComponentType.DIVIDER, null, skinParam,
				divider.getText());
		return comp;
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final Component comp = getComponent(stringBounder);
		final XDimension2D dim = comp.getPreferredDimension(stringBounder);
		final Area area = Area.create(
				tileArguments.getBorder2() - tileArguments.getBorder1() - xorigin.getCurrentValue(), dim.getHeight());

		ug = ug.apply(UTranslate.dx(tileArguments.getBorder1()));
		comp.drawU(ug, area, (Context2D) ug);
	}

	public double getPreferredHeight() {
		final Component comp = getComponent(getStringBounder());
		final XDimension2D dim = comp.getPreferredDimension(getStringBounder());
		return dim.getHeight();
	}

	public void addConstraints() {
		// final Component comp = getComponent(stringBounder);
		// final Dimension2D dim = comp.getPreferredDimension(stringBounder);
		// final double width = dim.getWidth();
	}

	public Real getMinX() {
		return xorigin;
	}

	public Real getMaxX() {
		final Component comp = getComponent(getStringBounder());
		final XDimension2D dim = comp.getPreferredDimension(getStringBounder());
		return xorigin.addFixed(dim.getWidth());
	}

}
