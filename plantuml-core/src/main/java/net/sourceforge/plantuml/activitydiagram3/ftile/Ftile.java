package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;

public interface Ftile extends Swimable, TextBlock {

	public UStroke getThickness(Style style);

	public ISkinParam skinParam();

	public LinkRendering getInLinkRendering();

	public LinkRendering getOutLinkRendering();

	public FtileGeometry calculateDimension(StringBounder stringBounder);

	public UTranslate getTranslateFor(Ftile child, StringBounder stringBounder);

	public Collection<Ftile> getMyChildren();

	public Collection<Connection> getInnerConnections();

	public List<WeldingPoint> getWeldingPoints();

	public HorizontalAlignment arrowHorizontalAlignment();

}
