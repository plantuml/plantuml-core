package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

public class FtileCircleStart extends AbstractFtile {

	private static final int SIZE = 20;

	private HColor backColor;
	private HColor borderColor;
	private final Swimlane swimlane;
	private double shadowing;

	public FtileCircleStart(ISkinParam skinParam, HColor backColor, Swimlane swimlane, Style style) {
		super(skinParam);
		this.swimlane = swimlane;
		this.backColor = backColor;
		this.borderColor = HColors.none();
		this.shadowing = style.value(PName.Shadowing).asDouble();
		this.backColor = style.value(PName.BackGroundColor).asColor(getIHtmlColorSet());
		this.borderColor = style.value(PName.LineColor).asColor(getIHtmlColorSet());
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
		circle.setDeltaShadow(shadowing);
		ug.apply(borderColor).apply(backColor.bg()).draw(circle);
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		return new FtileGeometry(SIZE, SIZE, SIZE / 2, 0, SIZE);
	}

}
