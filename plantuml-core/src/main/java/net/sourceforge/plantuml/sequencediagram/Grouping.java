package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.style.WithStyle;

public abstract class Grouping extends AbstractEvent implements Event, WithStyle {

	private final String title;
	private final GroupingType type;
	private final String comment;
	private final HColor backColorElement;

	// private final StyleBuilder styleBuilder;

	final private Style style;
	final private Style styleHeader;

	public StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.group);
	}

	private StyleSignatureBasic getHeaderStyleDefinition() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.groupHeader);
	}

	public Style[] getUsedStyles() {
		return new Style[] {
				style == null ? null : style.eventuallyOverride(PName.BackGroundColor, getBackColorGeneral()),
				styleHeader == null ? null : styleHeader.eventuallyOverride(PName.BackGroundColor, backColorElement) };
	}

	public Grouping(String title, String comment, GroupingType type, HColor backColorElement,
			StyleBuilder styleBuilder) {
		this.title = title;
		// this.styleBuilder = styleBuilder;
		this.comment = comment;
		this.type = type;
		this.backColorElement = backColorElement;
		this.style = getStyleSignature().getMergedStyle(styleBuilder);
		this.styleHeader = getHeaderStyleDefinition().getMergedStyle(styleBuilder);
	}

	@Override
	public final String toString() {
		return super.toString() + " " + type + " " + title;
	}

	final public String getTitle() {
		return title;
	}

	final public GroupingType getType() {
		return type;
	}

	public abstract int getLevel();

	public abstract HColor getBackColorGeneral();

	final public String getComment() {
		return comment;
	}

	public final HColor getBackColorElement() {
		return backColorElement;
	}

	public abstract boolean isParallel();

}
