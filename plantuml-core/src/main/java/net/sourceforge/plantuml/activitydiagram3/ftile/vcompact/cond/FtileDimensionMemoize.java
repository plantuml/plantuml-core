package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond;

import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.style.ISkinParam;

public abstract class FtileDimensionMemoize extends AbstractFtile {

	public FtileDimensionMemoize(ISkinParam skinParam) {
		super(skinParam);
	}

	private FtileGeometry calculateDimensionInternal;

	protected final FtileGeometry calculateDimensionInternal(StringBounder stringBounder) {
		if (calculateDimensionInternal == null) {
			calculateDimensionInternal = calculateDimensionInternalSlow(stringBounder);
		}
		return calculateDimensionInternal;
	}

	abstract protected FtileGeometry calculateDimensionInternalSlow(StringBounder stringBounder);
}
