package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.BodyFactory;
import net.sourceforge.plantuml.decoration.symbol.USymbol;
import net.sourceforge.plantuml.decoration.symbol.USymbols;
import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.url.Url;

public class EntityImageState2 extends AbstractEntityImage {

	final private Url url;
	private final SName sname;

	private final TextBlock asSmall;

	public EntityImageState2(Entity entity, ISkinParam skinParam, SName sname) {
		super(entity, skinParam);
		this.sname = sname;
		final Stereotype stereotype = entity.getStereotype();

		final USymbol symbol = USymbols.FRAME;
		final Fashion ctx = getStyle().getSymbolContext(skinParam.getIHtmlColorSet());

		this.url = entity.getUrl99();
		TextBlock stereo = TextBlockUtils.empty(0, 0);

		final TextBlock desc = BodyFactory.create2(skinParam.getDefaultTextAlignment(HorizontalAlignment.CENTER),
				entity.getDisplay(), skinParam, stereotype, entity, getStyle());

		asSmall = symbol.asSmall(null, desc, stereo, ctx, skinParam.getStereotypeAlignment());

	}

	private Style getStyle() {
		return getStyleSignature().getMergedStyle(getSkinParam().getCurrentStyleBuilder());
	}

	private StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, sname, SName.state);
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return asSmall.calculateDimension(stringBounder);
	}

	final public void drawU(UGraphic ug) {
		if (url != null)
			ug.startUrl(url);

		asSmall.drawU(ug);

		if (url != null)
			ug.closeUrl();

	}

}
