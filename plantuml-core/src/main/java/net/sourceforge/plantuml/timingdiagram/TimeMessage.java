package net.sourceforge.plantuml.timingdiagram;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.LinkDecor;
import net.sourceforge.plantuml.cucadiagram.LinkType;
import net.sourceforge.plantuml.cucadiagram.WithLinkType;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class TimeMessage extends WithLinkType {

	private final TickInPlayer tickInPlayer1;
	private final TickInPlayer tickInPlayer2;
	private final Display label;
	private final ISkinParam skinParam;
	private final StyleBuilder styleBuilder;

	public TimeMessage(TickInPlayer tickInPlayer1, TickInPlayer tickInPlayer2, String label, ISkinParam skinParam) {
		this.skinParam = skinParam;
		this.styleBuilder = skinParam.getCurrentStyleBuilder();
		this.tickInPlayer1 = tickInPlayer1;
		this.tickInPlayer2 = tickInPlayer2;
		this.label = Display.getWithNewlines(label);
		this.setSpecificColor(getColor());
		this.type = new LinkType(LinkDecor.NONE, LinkDecor.NONE);
	}

	@Override
	public UStroke getUStroke() {
		if (styleBuilder == null) {
			return new UStroke(1.5);
		}
		return getStyle().getStroke();
	}

	private HColor getColor() {
//		return HColorUtils.BLUE;
		if (styleBuilder == null) {
			return HColors.MY_RED;
		}
		return getStyle().value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
	}

	private Style getStyle() {
		return getStyleSignature().getMergedStyle(styleBuilder);
	}

	private StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.timingDiagram, SName.arrow);
	}

	public final Player getPlayer1() {
		return tickInPlayer1.getPlayer();
	}

	public final Player getPlayer2() {
		return tickInPlayer2.getPlayer();
	}

	public final TimeTick getTick1() {
		return tickInPlayer1.getTick();
	}

	public final TimeTick getTick2() {
		return tickInPlayer2.getTick();
	}

	public final Display getLabel() {
		return label;
	}

	@Override
	public void goNorank() {
		// Nothing to do
	}

}
