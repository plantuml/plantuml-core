package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.style.ISkinParam;

public class FtileBreak extends FtileEmpty implements WeldingPoint {

	public FtileBreak(ISkinParam skinParam, Swimlane swimlane) {
		super(skinParam, swimlane);
	}

	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.emptyList();
	}

	@Override
	public String toString() {
		return "FtileBreak";
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		return calculateDimensionEmpty().withoutPointOut();
	}

	@Override
	public List<WeldingPoint> getWeldingPoints() {
		return Collections.singletonList((WeldingPoint) this);
	}

}
