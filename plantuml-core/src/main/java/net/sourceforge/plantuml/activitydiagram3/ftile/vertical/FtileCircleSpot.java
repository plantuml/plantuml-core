package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.shape.UCenteredCharacter;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

public class FtileCircleSpot extends AbstractFtile {

	private static final int SIZE = 20;

	private final Swimlane swimlane;
	private final String spot;
	private final FontConfiguration fc;
	private final HColor backColor;
	private final Style style;

	public FtileCircleSpot(ISkinParam skinParam, Swimlane swimlane, String spot, UFont font, HColor backColor,
			Style style) {
		super(skinParam);
		this.style = style;
		this.spot = spot;
		this.swimlane = swimlane;
		this.backColor = backColor;
		this.fc = FontConfiguration.create(skinParam, FontParam.ACTIVITY, null);
	}

	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.emptyList();
	}

	public Set<Swimlane> getSwimlanes() {
		if (swimlane == null)
			return Collections.emptySet();

		return Collections.singleton(swimlane);
	}

	public Swimlane getSwimlaneIn() {
		return swimlane;
	}

	public Swimlane getSwimlaneOut() {
		return swimlane;
	}

	public void drawU(UGraphic ug) {
		final UEllipse circle = new UEllipse(SIZE, SIZE);

		final HColor backColor = this.backColor == null ? style.value(PName.BackGroundColor).asColor(getIHtmlColorSet())
				: this.backColor;
		final HColor borderColor = style.value(PName.LineColor).asColor(getIHtmlColorSet());
		final double shadow = style.value(PName.Shadowing).asDouble();

		circle.setDeltaShadow(shadow);
		ug.apply(borderColor).apply(backColor.bg()).apply(getThickness(style)).draw(circle);

		ug.apply(fc.getColor()).apply(new UTranslate(SIZE / 2, SIZE / 2))
				.draw(new UCenteredCharacter(spot.charAt(0), fc.getFont()));

	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		return new FtileGeometry(SIZE, SIZE, SIZE / 2, 0, SIZE);
	}

}
