package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.DisplayPositioned;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.svek.DecorateEntityImage;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;

public class AnnotatedWorker {

	private final Annotated annotated;
	private final ISkinParam skinParam;
	private final StringBounder stringBounder;
	private final AnnotatedBuilder builder;

	public AnnotatedWorker(Annotated annotated, ISkinParam skinParam, StringBounder stringBounder,
			AnnotatedBuilder builder) {
		this.annotated = annotated;
		this.skinParam = skinParam;
		this.stringBounder = stringBounder;
		this.builder = builder;
	}

	public TextBlockBackcolored addAdd(TextBlock result) {
		result = builder.decoreWithFrame(result);
		result = addLegend(result);
		result = addTitle(result);
		result = addCaption(result);
		result = builder.addHeaderAndFooter(result);
		return (TextBlockBackcolored) result;
	}

	public TextBlock addLegend(TextBlock original) {
		final DisplayPositioned legend = annotated.getLegend();
		if (legend.isNull())
			return original;

		return DecorateEntityImage.add(original, builder.getLegend(), legend.getHorizontalAlignment(),
				legend.getVerticalAlignment());
	}

	public TextBlock addTitle(TextBlock original) {
		final DisplayPositioned title = (DisplayPositioned) annotated.getTitle();
		if (title.isNull())
			return original;

		return DecorateEntityImage.addTop(original, builder.getTitle(), HorizontalAlignment.CENTER);
	}

	private TextBlock addCaption(TextBlock original) {
		final DisplayPositioned caption = annotated.getCaption();
		if (caption.isNull())
			return original;

		return DecorateEntityImage.addBottom(original, builder.getCaption(), HorizontalAlignment.CENTER);
	}

}
