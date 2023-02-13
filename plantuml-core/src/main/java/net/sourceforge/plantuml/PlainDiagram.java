package net.sourceforge.plantuml;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.ugraphic.ImageBuilder;

// This class doesnt feel like a wonderful idea, just a stepping stone towards something
public abstract class PlainDiagram extends AbstractPSystem {
	
	public PlainDiagram(UmlSource source) {
		super(source);
	}

	@Override
	public ImageBuilder createImageBuilder(FileFormatOption fileFormatOption) throws IOException {
		return super.createImageBuilder(fileFormatOption)
				.margin(getDefaultMargins())
				.metadata(fileFormatOption.isWithMetadata() ? getMetadata() : null)
				.seed(seed());
	}

	@Override
	protected ImageData exportDiagramNow(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {

		return createImageBuilder(fileFormatOption)
				.drawable(getRootDrawable(fileFormatOption))
				.write(os);
	}

	protected abstract UDrawable getRootDrawable(FileFormatOption fileFormatOption) throws IOException;
}
