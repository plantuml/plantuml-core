package net.sourceforge.plantuml;

import static net.sourceforge.plantuml.klimt.shape.GraphicStrings.createBlackOnWhite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.geom.GraphicPosition;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public abstract class PlainStringsDiagram extends PlainDiagram {

	protected BufferedImage image = null;
	protected GraphicPosition imagePosition = null;

	protected final List<String> strings = new ArrayList<>();

	public PlainStringsDiagram(UmlSource source) {
		super(source);
	}

	@Override
	public UDrawable getRootDrawable(FileFormatOption fileFormatOption) {
		return createBlackOnWhite(strings, image, imagePosition);
	}
}
