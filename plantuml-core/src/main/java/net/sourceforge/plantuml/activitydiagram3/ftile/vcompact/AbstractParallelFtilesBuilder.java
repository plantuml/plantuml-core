package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileHeightFixedCentered;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileUtils;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.klimt.creole.CreoleMode;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public abstract class AbstractParallelFtilesBuilder {

	protected final double barHeight = 6;

	private final Rose rose = new Rose();

	private final ISkinParam skinParam;
	private final StringBounder stringBounder;
	protected final List<Ftile> list99 = new ArrayList<>();

	public StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.activity);
	}

	final public StyleSignatureBasic getStyleSignatureArrow() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.arrow);
	}

	public AbstractParallelFtilesBuilder(ISkinParam skinParam, StringBounder stringBounder, List<Ftile> all) {
		this.skinParam = skinParam;
		this.stringBounder = stringBounder;
		this.list99.addAll(getFoo2(all));
	}

	protected List<Ftile> getFoo2(List<Ftile> all) {
		final double maxHeight = computeMaxHeight(all);
		final List<Ftile> result = new ArrayList<>();
		for (Ftile ftile : all) {
			final Ftile newFtile = computeNewFtile(ftile, maxHeight);
			result.add(newFtile);
		}
		return result;
	}

	private Ftile computeNewFtile(Ftile ftile, double maxHeight) {
		final double spaceArroundBlackBar = 20;
		final double xMargin = 14;
		Ftile tmp;
		tmp = FtileUtils.addHorizontalMargin(ftile, xMargin);
		tmp = new FtileHeightFixedCentered(tmp, maxHeight + 2 * spaceArroundBlackBar);
		return tmp;
	}

	final protected double computeMaxHeight(List<Ftile> all) {
		double height = 0;
		for (Ftile tmp : all)
			height = Math.max(height, tmp.calculateDimension(getStringBounder()).getHeight());

		return height;
	}

	public final Ftile build(Ftile inner) {
		final Ftile step1 = doStep1(inner);
		return doStep2(inner, step1);
	}

	protected abstract Ftile doStep1(Ftile inner);

	protected abstract Ftile doStep2(Ftile inner, Ftile step1);

	protected StringBounder getStringBounder() {
		return stringBounder;
	}

	protected Rose getRose() {
		return rose;
	}

	protected ISkinParam skinParam() {
		return skinParam;
	}

	protected final TextBlock getTextBlock(Display display) {
		// DUP3945
		if (Display.isNull(display))
			return null;

		final Style style = getStyleSignatureArrow().getMergedStyle(skinParam().getCurrentStyleBuilder());
		final FontConfiguration fontConfiguration = style.getFontConfiguration(skinParam().getIHtmlColorSet());

		return display.create7(fontConfiguration, HorizontalAlignment.LEFT, skinParam(), CreoleMode.SIMPLE_LINE);
	}

	protected TextBlock getTextBlock(LinkRendering linkRendering) {
		// DUP1433
		final Display display = linkRendering.getDisplay();
		return getTextBlock(display);
	}

	protected final double getHeightOfMiddle(Ftile middle) {
		return middle.calculateDimension(getStringBounder()).getHeight();
	}

	protected Swimlane swimlaneOutForStep2() {
		return list99.get(list99.size() - 1).getSwimlaneOut();
	}

}
