package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.Branch;
import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactoryDelegator;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond.ConditionalBuilder;
import net.sourceforge.plantuml.decoration.Rainbow;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.skin.Pragma;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.svek.ConditionEndStyle;
import net.sourceforge.plantuml.svek.ConditionStyle;
import net.sourceforge.plantuml.url.Url;

public class FtileFactoryDelegatorIf extends FtileFactoryDelegator {

	private final Pragma pragma;

	public FtileFactoryDelegatorIf(FtileFactory factory, Pragma pragma) {
		super(factory);
		this.pragma = pragma;
	}

	@Override
	public Ftile createIf(Swimlane swimlane, List<Branch> thens, Branch elseBranch, LinkRendering afterEndwhile,
			LinkRendering topInlinkRendering, Url url) {

		final ConditionStyle conditionStyle = skinParam().getConditionStyle();
		final ConditionEndStyle conditionEndStyle = skinParam().getConditionEndStyle();
		final Branch branch0 = thens.get(0);

		final Style styleArrow = getDefaultStyleDefinitionArrow().getMergedStyle(skinParam().getCurrentStyleBuilder());
		final Style styleDiamond = getDefaultStyleDefinitionDiamond()
				.getMergedStyle(skinParam().getCurrentStyleBuilder());
		final HColor borderColor = styleDiamond.value(PName.LineColor).asColor(skinParam().getIHtmlColorSet());
		final HColor backColor = branch0.getColor() == null
				? styleDiamond.value(PName.BackGroundColor).asColor(skinParam().getIHtmlColorSet())
				: branch0.getColor();
		final Rainbow arrowColor = Rainbow.build(styleArrow, skinParam().getIHtmlColorSet());
		final FontConfiguration fcTest = styleDiamond.getFontConfiguration(skinParam().getIHtmlColorSet());
		final FontConfiguration fcArrow = styleArrow.getFontConfiguration(skinParam().getIHtmlColorSet());

		if (thens.size() > 1) {
			if (pragma.useVerticalIf()/* OptionFlags.USE_IF_VERTICAL */)
				return FtileIfLongVertical.create(swimlane, borderColor, backColor, arrowColor, getFactory(),
						conditionStyle, thens, elseBranch, fcArrow, topInlinkRendering, afterEndwhile);
			return FtileIfLongHorizontal.create(swimlane, borderColor, backColor, arrowColor, getFactory(),
					conditionStyle, thens, elseBranch, fcArrow, topInlinkRendering, afterEndwhile, fcTest);
		}
		return ConditionalBuilder.create(swimlane, borderColor, backColor, arrowColor, getFactory(), conditionStyle,
				conditionEndStyle, thens.get(0), elseBranch, skinParam(), getStringBounder(), fcArrow, fcTest, url);
	}

}
