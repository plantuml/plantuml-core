package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.style.ISkinParam;

public class FtileEmpty extends AbstractFtile {

	private final double width;
	private final double height;
	private final Swimlane swimlane;

	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.emptyList();
	}

	public FtileEmpty(ISkinParam skinParam, double width, double height) {
		this(skinParam, width, height, null);
	}

	public FtileEmpty(ISkinParam skinParam, double width, double height, Swimlane swimlane) {
		super(skinParam);
		this.width = width;
		this.height = height;
		this.swimlane = swimlane;

	}

	public FtileEmpty(ISkinParam skinParam) {
		this(skinParam, 0, 0, null);
	}

	public FtileEmpty(ISkinParam skinParam, Swimlane swimlane) {
		this(skinParam, 0, 0, swimlane);
	}

	@Override
	public String toString() {
		return "FtileEmpty";
	}

	public void drawU(UGraphic ug) {
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		return calculateDimensionEmpty();
	}

	final protected FtileGeometry calculateDimensionEmpty() {
		return new FtileGeometry(width, height, width / 2, 0, height);
	}

	public Swimlane getSwimlaneIn() {
		return swimlane;
	}

	public Swimlane getSwimlaneOut() {
		return swimlane;
	}

	public Set<Swimlane> getSwimlanes() {
		final Set<Swimlane> result = new HashSet<>();
		if (swimlane != null) {
			result.add(swimlane);
		}
		return Collections.unmodifiableSet(result);
	}

}
