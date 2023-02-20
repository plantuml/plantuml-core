package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.skin.AlignmentParam;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;

public abstract class AbstractFtile extends AbstractTextBlock implements Ftile {

	private final ISkinParam skinParam;

	public AbstractFtile(ISkinParam skinParam) {
		this.skinParam = skinParam;
	}

	final public ISkinParam skinParam() {
		if (skinParam == null)
			throw new IllegalStateException();

		return skinParam;
	}

	final public HColorSet getIHtmlColorSet() {
		return skinParam.getIHtmlColorSet();
	}

	public LinkRendering getInLinkRendering() {
		return LinkRendering.none();
	}

	public LinkRendering getOutLinkRendering() {
		return LinkRendering.none();
	}

	public Collection<Connection> getInnerConnections() {
		return Collections.emptyList();
	}

	public UTranslate getTranslateFor(Ftile child, StringBounder stringBounder) {
		throw new UnsupportedOperationException("" + getClass());
	}

	public final UStroke getThickness(Style style) {
		return style.getStroke();
	}

	public List<WeldingPoint> getWeldingPoints() {
		return Collections.emptyList();
	}

	public Collection<Ftile> getMyChildren() {
		throw new UnsupportedOperationException("" + getClass());
	}

	public HorizontalAlignment arrowHorizontalAlignment() {
		return skinParam.getHorizontalAlignment(AlignmentParam.arrowMessageAlignment, null, false, null);
	}

	private FtileGeometry cachedGeometry;

	final public FtileGeometry calculateDimension(StringBounder stringBounder) {
		if (cachedGeometry == null) {
			cachedGeometry = calculateDimensionFtile(stringBounder);
		}
		return cachedGeometry;
	}

	abstract protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder);

	@Override
	final public MinMax getMinMax(StringBounder stringBounder) {
		throw new UnsupportedOperationException();
		// return getMinMaxFtile(stringBounder);
	}

	// protected MinMax getMinMaxFtile(StringBounder stringBounder) {
	// throw new UnsupportedOperationException();
	// }

}
