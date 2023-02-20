package net.sourceforge.plantuml.descdiagram.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.cucadiagram.LinkArrow;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockArrow2;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.svek.GuideLine;

public class StringWithArrow {

	private final String label;
	private final LinkArrow linkArrow;

	public StringWithArrow(String completeLabel) {
		if (completeLabel == null) {
			this.linkArrow = LinkArrow.NONE_OR_SEVERAL;
			this.label = null;
			return;
		}
		completeLabel = StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(completeLabel, "\"");
		if (Display.hasSeveralGuideLines(completeLabel)) {
			this.linkArrow = LinkArrow.NONE_OR_SEVERAL;
			this.label = completeLabel;
		} else {

			if ("<".equals(completeLabel)) {
				this.linkArrow = LinkArrow.BACKWARD;
				this.label = null;
			} else if (">".equals(completeLabel)) {
				this.linkArrow = LinkArrow.DIRECT_NORMAL;
				this.label = null;
			} else if (completeLabel.startsWith("< ")) {
				this.linkArrow = LinkArrow.BACKWARD;
				this.label = StringUtils.trin(completeLabel.substring(2));
			} else if (completeLabel.startsWith("> ")) {
				this.linkArrow = LinkArrow.DIRECT_NORMAL;
				this.label = StringUtils.trin(completeLabel.substring(2));
			} else if (completeLabel.endsWith(" >")) {
				this.linkArrow = LinkArrow.DIRECT_NORMAL;
				this.label = StringUtils.trin(completeLabel.substring(0, completeLabel.length() - 2));
			} else if (completeLabel.endsWith(" <")) {
				this.linkArrow = LinkArrow.BACKWARD;
				this.label = StringUtils.trin(completeLabel.substring(0, completeLabel.length() - 2));
			} else {
				this.linkArrow = LinkArrow.NONE_OR_SEVERAL;
				this.label = completeLabel;
			}
		}
	}

	public final String getLabel() {
		return label;
	}

	public final LinkArrow getLinkArrow() {
		return linkArrow;
	}

	public final Display getDisplay() {
		return Display.getWithNewlines(label);
	}

	static public TextBlock addMagicArrow(TextBlock label, GuideLine guide, FontConfiguration font) {
		final TextBlock arrow = new TextBlockArrow2(guide, font);
		return TextBlockUtils.mergeLR(arrow, label, VerticalAlignment.CENTER);
	}

	static private TextBlock addMagicArrow2(TextBlock label, GuideLine guide, FontConfiguration font) {
		final TextBlock arrow = new TextBlockArrow2(guide, font);
		return TextBlockUtils.mergeLR(arrow, label, VerticalAlignment.CENTER);
	}

	public static TextBlock addSeveralMagicArrows(Display label, GuideLine guide, FontConfiguration font,
			HorizontalAlignment alignment, ISkinParam skinParam) {
		TextBlock result = TextBlockUtils.EMPTY_TEXT_BLOCK;
		for (CharSequence cs : label) {
			StringWithArrow tmp = new StringWithArrow(cs.toString());
			TextBlock block = tmp.getDisplay().create9(font, alignment, skinParam, skinParam.maxMessageSize());
			if (tmp.getLinkArrow() != LinkArrow.NONE_OR_SEVERAL)
				block = StringWithArrow.addMagicArrow2(block, tmp.getLinkArrow().mute(guide), font);

			result = TextBlockUtils.mergeTB(result, block, alignment);
		}
		return result;
	}

}
