package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.decoration.LinkDecor;
import net.sourceforge.plantuml.decoration.LinkType;
import net.sourceforge.plantuml.decoration.WithLinkType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.core.TaskInstant;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.timescale.TimeScale;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class GanttConstraint extends WithLinkType {

	private final TaskInstant source;
	private final TaskInstant dest;
	private final StyleBuilder styleBuilder;
	private final HColorSet colorSet;

	public GanttConstraint(HColorSet colorSet, StyleBuilder styleBuilder, TaskInstant source, TaskInstant dest,
			HColor forcedColor) {
		this.styleBuilder = styleBuilder;
		this.colorSet = colorSet;
		this.source = source;
		this.dest = dest;
		this.type = new LinkType(LinkDecor.NONE, LinkDecor.NONE);
		this.setSpecificColor(forcedColor);
	}

	public GanttConstraint(HColorSet colorSet, StyleBuilder styleBuilder, TaskInstant source, TaskInstant dest) {
		this(colorSet, styleBuilder, source, dest, null);
	}

	public boolean isOn(Task task) {
		return source.getMoment() == task || dest.getMoment() == task;
	}

	public boolean isThereRightArrow(Task task) {
		if (dest.getMoment() == task && dest.getAttribute() == TaskAttribute.END)
			return true;

		if (source.getMoment() == task && dest.getAttribute() == TaskAttribute.END
				&& source.getAttribute() == TaskAttribute.END)
			return true;

		return false;
	}

	@Override
	public String toString() {
		return source.toString() + " --> " + dest.toString();
	}

	final public StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.ganttDiagram, SName.arrow);
	}

	public UDrawable getUDrawable(TimeScale timeScale, ToTaskDraw toTaskDraw) {
		Style style = getStyleSignature().getMergedStyle(styleBuilder).eventuallyOverride(PName.LineColor,
				getSpecificColor());
		style = style.eventuallyOverride(getType().getStroke3(style.getStroke()));
		return new GanttArrow(colorSet, style, timeScale, source, dest, toTaskDraw, styleBuilder);
	}

	public boolean isHidden(Day min, Day max) {
		if (isHidden(source.getInstantPrecise(), min, max))
			return true;

		if (isHidden(dest.getInstantPrecise(), min, max))
			return true;

		return false;
	}

	private boolean isHidden(Day now, Day min, Day max) {
		if (now.compareTo(min) < 0)
			return true;

		if (now.compareTo(max) > 0)
			return true;

		return false;
	}

	@Override
	public void goNorank() {
	}

}
