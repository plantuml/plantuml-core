package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.activitydiagram3.Branch;
import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactoryDelegator;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileMinWidthCentered;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond.FtileSwitchNude;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond.FtileSwitchWithDiamonds;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond.FtileSwitchWithManyLinks;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond.FtileSwitchWithOneLink;
import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorateInLabel;
import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorateOutLabel;
import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDiamondInside;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.Rainbow;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

public class FtileFactoryDelegatorSwitch extends FtileFactoryDelegator {

	public FtileFactoryDelegatorSwitch(FtileFactory factory) {
		super(factory);
	}

	@Override
	public Ftile createSwitch(Swimlane swimlane, List<Branch> branches, LinkRendering afterEndwhile,
			LinkRendering topInlinkRendering, Display labelTest) {
		// return createNude(swimlane, branches);
		// return createWithDiamonds(swimlane, branches, labelTest);
		return createWithLinks(swimlane, branches, labelTest);
	}

	private Ftile createNude(Swimlane swimlane, List<Branch> branches) {
		final List<Ftile> ftiles = new ArrayList<>();
		for (Branch branch : branches) {
			ftiles.add(new FtileMinWidthCentered(branch.getFtile(), 30));
		}
		return new FtileSwitchNude(ftiles, swimlane);
	}

	private Ftile createWithDiamonds(Swimlane swimlane, List<Branch> branches, Display labelTest) {
		final List<Ftile> ftiles = new ArrayList<>();
		for (Branch branch : branches) {
			ftiles.add(new FtileMinWidthCentered(branch.getFtile(), 30));
		}
		final Ftile diamond1 = getDiamond1(swimlane, branches.get(0), labelTest);
		final Ftile diamond2 = getDiamond2(swimlane, branches.get(0));

		return new FtileSwitchWithDiamonds(ftiles, branches, swimlane, diamond1, diamond2, getStringBounder());
	}

	private Ftile createWithLinks(Swimlane swimlane, List<Branch> branches, Display labelTest) {
		final List<Ftile> ftiles = new ArrayList<>();
		final Ftile diamond1 = getDiamond1(swimlane, branches.get(0), labelTest);
		final Ftile diamond2 = getDiamond2(swimlane, branches.get(0));

		for (Branch branch : branches) {
			final XDimension2D dimLabelIn = branch.getTextBlockPositive().calculateDimension(getStringBounder());
			final XDimension2D dimLabelOut = branch.getTextBlockSpecial().calculateDimension(getStringBounder());
			ftiles.add(new FtileDecorateOutLabel(new FtileDecorateInLabel(branch.getFtile(), dimLabelIn), dimLabelOut));
		}

		final Style style = getDefaultStyleDefinitionArrow().getMergedStyle(skinParam().getCurrentStyleBuilder());
		final Rainbow arrowColor = Rainbow.build(style, skinParam().getIHtmlColorSet());

		if (ftiles.size() == 1) {
			final FtileSwitchWithOneLink result = new FtileSwitchWithOneLink(ftiles, branches, swimlane, diamond1,
					diamond2, getStringBounder(), arrowColor);
			return result.addLinks(getStringBounder());
		}
		final FtileSwitchWithManyLinks result = new FtileSwitchWithManyLinks(ftiles, branches, swimlane, diamond1,
				diamond2, getStringBounder(), arrowColor);
		return result.addLinks(getStringBounder());

	}

	private Ftile getDiamond1(Swimlane swimlane, Branch branch0, Display test) {

		LineBreakStrategy lineBreak = LineBreakStrategy.NONE;

		final Style style = getDefaultStyleDefinitionDiamond().getMergedStyle(skinParam().getCurrentStyleBuilder());
		lineBreak = style.wrapWidth();
		final FontConfiguration fcDiamond = style.getFontConfiguration(skinParam().getIHtmlColorSet());
		final HColor borderColor = style.value(PName.LineColor).asColor(skinParam().getIHtmlColorSet());
		final HColor backColor = branch0.getColor() == null ? style.value(PName.BackGroundColor)
				.asColor(skinParam().getIHtmlColorSet()) : branch0.getColor();

		final TextBlock tbTest;
		if (Display.isNull(test) || test.isWhite())
			tbTest = TextBlockUtils.empty(0, 0);
		else
			tbTest = test.create0(fcDiamond, branch0.skinParam().getDefaultTextAlignment(HorizontalAlignment.LEFT),
					branch0.skinParam(), lineBreak, CreoleMode.FULL, null, null);

		return new FtileDiamondInside(tbTest, branch0.skinParam(), backColor, borderColor, swimlane);
	}

	private Ftile getDiamond2(Swimlane swimlane, Branch branch0) {

		final Style style = getDefaultStyleDefinitionDiamond().getMergedStyle(skinParam().getCurrentStyleBuilder());
		final HColor borderColor = style.value(PName.LineColor).asColor(skinParam().getIHtmlColorSet());
		final HColor backColor = branch0.getColor() == null ? style.value(PName.BackGroundColor)
				.asColor(skinParam().getIHtmlColorSet()) : branch0.getColor();

		return new FtileDiamondInside(TextBlockUtils.empty(0, 0), branch0.skinParam(), backColor, borderColor,
				swimlane);
	}

}
