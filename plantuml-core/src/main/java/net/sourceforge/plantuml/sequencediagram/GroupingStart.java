package net.sourceforge.plantuml.sequencediagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.url.Url;

public class GroupingStart extends Grouping {

	private final List<GroupingLeaf> children = new ArrayList<>();
	private final HColor backColorGeneral;

	final private GroupingStart parent;
	private boolean parallel = false;

	public GroupingStart(String title, String comment, HColor backColorGeneral, HColor backColorElement,
			GroupingStart parent, StyleBuilder styleBuilder) {
		super(title, comment, GroupingType.START, backColorElement, styleBuilder);
		this.backColorGeneral = backColorGeneral;
		this.parent = parent;
	}

	public Style[] getUsedStyles() {
		final Style[] result = super.getUsedStyles();
		if (result[0] != null)
			result[0] = result[0].eventuallyOverride(PName.BackGroundColor, backColorGeneral);

		return result;
	}

	List<GroupingLeaf> getChildren() {
		return Collections.unmodifiableList(children);
	}

	public void addChildren(GroupingLeaf g) {
		children.add(g);
	}

	public int getLevel() {
		if (parent == null)
			return 0;

		return parent.getLevel() + 1;
	}

	@Override
	public HColor getBackColorGeneral() {
		return backColorGeneral;
	}

	public boolean dealWith(Participant someone) {
		return false;
	}

	public Url getUrl() {
		return null;
	}

	public boolean hasUrl() {
		return false;
	}

	@Override
	public boolean isParallel() {
		return parallel || getTitle().equals("par2");
	}

	public void goParallel() {
		this.parallel = true;
	}

}
