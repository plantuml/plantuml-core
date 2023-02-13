
package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.EntityPosition;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.Rankdir;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.Bibliotekon;
import net.sourceforge.plantuml.svek.Cluster;
import net.sourceforge.plantuml.svek.ShapeType;

public abstract class AbstractEntityImageBorder extends AbstractEntityImage {
	public final EntityPosition entityPosition;
	protected final Cluster parent;
	protected final Bibliotekon bibliotekon;
	protected final Rankdir rankdir;

	protected abstract StyleSignatureBasic getSignature();

	final protected Style getStyle() {
		final Entity leaf = (Entity) getEntity();
		final Stereotype stereotype = leaf.getStereotype();
		return getSignature().withTOBECHANGED(stereotype).getMergedStyle(getSkinParam().getCurrentStyleBuilder());
	}

	AbstractEntityImageBorder(Entity leaf, ISkinParam skinParam, Cluster parent, Bibliotekon bibliotekon,
			FontParam fontParam) {
		super(leaf, skinParam);

		this.parent = parent;
		this.bibliotekon = bibliotekon;
		this.entityPosition = leaf.getEntityPosition();
		this.rankdir = skinParam.getRankdir();

		if (entityPosition == EntityPosition.NORMAL)
			throw new IllegalArgumentException();
	}

	protected final TextBlock getDesc() {
		final Entity leaf = (Entity) getEntity();
		final FontConfiguration fc = FontConfiguration.create(getSkinParam(), getStyle());
		return leaf.getDisplay().create(fc, HorizontalAlignment.CENTER, getSkinParam());
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return entityPosition.getDimension(rankdir);
	}

	public ShapeType getShapeType() {
		return entityPosition.getShapeType();
	}

}
