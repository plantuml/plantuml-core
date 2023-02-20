
package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.EntityPosition;
import net.sourceforge.plantuml.klimt.Shadowable;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.Bibliotekon;
import net.sourceforge.plantuml.svek.Cluster;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.svek.SvekNode;

public class EntityImagePort extends AbstractEntityImageBorder {

	private final SName sname;

	public EntityImagePort(Entity leaf, ISkinParam skinParam, Cluster parent, Bibliotekon bibliotekon, SName sname) {
		super(leaf, skinParam, parent, bibliotekon, FontParam.BOUNDARY);
		this.sname = sname;
	}

	@Override
	protected StyleSignatureBasic getSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, sname, SName.port);
	}

	private boolean upPosition() {
		final XPoint2D clusterCenter = parent.getClusterPosition().getPointCenter();
		final SvekNode node = bibliotekon.getNode(getEntity());
		return node.getMinY() < clusterCenter.getY();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		double sp = EntityPosition.RADIUS * 2;
		return new XDimension2D(sp, sp);
	}

	public double getMaxWidthFromLabelForEntryExit(StringBounder stringBounder) {
		final TextBlock desc = getDesc();
		final XDimension2D dimDesc = desc.calculateDimension(stringBounder);
		return dimDesc.getWidth();
	}

	private void drawSymbol(UGraphic ug) {
		final Shadowable rect = new URectangle(EntityPosition.RADIUS * 2, EntityPosition.RADIUS * 2);
		ug.draw(rect);
	}

	final public void drawU(UGraphic ug) {
		final TextBlock desc = getDesc();
		double y = 0;
		final XDimension2D dimDesc = desc.calculateDimension(ug.getStringBounder());
		final double x = 0 - (dimDesc.getWidth() - 2 * EntityPosition.RADIUS) / 2;

		if (upPosition())
			y -= 2 * EntityPosition.RADIUS + dimDesc.getHeight();
		else
			y += 2 * EntityPosition.RADIUS;

		desc.drawU(ug.apply(new UTranslate(x, y)));

		final Style style = getStyle();

		HColor backcolor = getEntity().getColors().getColor(ColorType.BACK);
		HColor borderColor = getEntity().getColors().getColor(ColorType.LINE);

		if (borderColor == null)
			borderColor = style.value(PName.LineColor).asColor(getSkinParam().getIHtmlColorSet());

		if (backcolor == null)
			backcolor = style.value(PName.BackGroundColor).asColor(getSkinParam().getIHtmlColorSet());

		ug = ug.apply(borderColor);
		ug = ug.apply(getUStroke()).apply(backcolor.bg());

		drawSymbol(ug);
	}

	private UStroke getUStroke() {
		return new UStroke(1.5);
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE_PORT;
	}
}
