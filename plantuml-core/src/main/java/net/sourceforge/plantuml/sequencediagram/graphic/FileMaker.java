package net.sourceforge.plantuml.sequencediagram.graphic;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface FileMaker {

	ImageData createOne(OutputStream os, int index, boolean isWithMetadata) throws IOException;

	void createOneGraphic(UGraphic ug);

	public int getNbPages();
}
