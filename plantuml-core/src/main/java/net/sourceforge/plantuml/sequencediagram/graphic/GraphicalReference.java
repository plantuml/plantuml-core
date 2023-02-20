package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Objects;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.sequencediagram.InGroupable;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.url.Url;

class GraphicalReference extends GraphicalElement implements InGroupable {

	private final Component comp;
	private final LivingParticipantBox livingParticipantBox1;
	private final LivingParticipantBox livingParticipantBox2;
	private final Url url;

	public GraphicalReference(double startingY, Component comp, LivingParticipantBox livingParticipantBox1,
			LivingParticipantBox livingParticipantBox2, Url url) {
		super(startingY);
		this.url = url;
		this.comp = comp;
		this.livingParticipantBox1 = Objects.requireNonNull(livingParticipantBox1);
		this.livingParticipantBox2 = Objects.requireNonNull(livingParticipantBox2);
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {

		final StringBounder stringBounder = ug.getStringBounder();
		final double posX = getMinX(stringBounder);

		ug = ug.apply(new UTranslate(posX, getStartingY()));
		final double preferredWidth = comp.getPreferredWidth(stringBounder);
		final double w = getMaxX(stringBounder) - getMinX(stringBounder);

		final double width = Math.max(preferredWidth, w);

		final XDimension2D dim = new XDimension2D(width, comp.getPreferredHeight(stringBounder));
		if (url != null) {
			ug.startUrl(url);
		}
		comp.drawU(ug, new Area(dim), context);
		if (url != null) {
			ug.closeUrl();
		}
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return comp.getPreferredHeight(stringBounder);
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return comp.getPreferredWidth(stringBounder);
	}

	@Override
	public double getStartingX(StringBounder stringBounder) {
		return getMinX(stringBounder);
	}

	public double getMaxX(StringBounder stringBounder) {
		return Math.max(livingParticipantBox1.getMaxX(stringBounder), livingParticipantBox2.getMaxX(stringBounder));
	}

	public double getMinX(StringBounder stringBounder) {
		return Math.min(livingParticipantBox1.getMinX(stringBounder), livingParticipantBox2.getMinX(stringBounder));
	}

	public String toString(StringBounder stringBounder) {
		return toString();
	}

}
