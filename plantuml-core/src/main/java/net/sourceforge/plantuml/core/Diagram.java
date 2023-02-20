package net.sourceforge.plantuml.core;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.api.ApiStable;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;

/**
 * Represents a single diagram. A Diagram could be a UML (sequence diagram,
 * class diagram...) or an non-UML diagram.
 * 
 * @author Arnaud Roques
 */
@ApiStable
public interface Diagram {

	/**
	 * Export the diagram as an image to some format. Note that a diagram could be
	 * drawn as several images (think about <code>new page</code> for sequence
	 * diagram for example).
	 * 
	 * @param os         where to write the image
	 * @param num        usually 0 (index of the image to be exported for this
	 *                   diagram).
	 * @param fileFormat file format to use
	 * 
	 * @return a description of the generated image
	 * 
	 * @throws IOException
	 */
	ImageData exportDiagram(OutputStream os, int num, FileFormatOption fileFormat) throws IOException;

	void exportDiagramGraphic(UGraphic ug);

	/**
	 * Number of images in this diagram (usually, 1)
	 * 
	 * @return usually 1
	 */
	int getNbImages();

	int getSplitPagesHorizontal();

	int getSplitPagesVertical();

	DiagramDescription getDescription();

	String getMetadata();

	String getWarningOrError();

	/**
	 * The original source of the diagram
	 */
	UmlSource getSource();

	/**
	 * Check if the Diagram have some links.
	 */
	public boolean hasUrl();

	public Display getTitleDisplay();

}
