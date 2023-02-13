package net.sourceforge.plantuml.cucadiagram;

import java.io.IOException;
import java.util.Collection;

import net.sourceforge.plantuml.Annotated;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.Pragma;
import net.sourceforge.plantuml.UmlDiagramType;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.baraye.EntityFactory;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.ugraphic.ImageBuilder;

public interface ICucaDiagram extends GroupHierarchy, PortionShower, Annotated {

	ISkinParam getSkinParam();

	UmlDiagramType getUmlDiagramType();

	EntityFactory getEntityFactory();

	StyleBuilder getCurrentStyleBuilder();

	boolean isHideEmptyDescriptionForState();

	Collection<Link> getLinks();

	Pragma getPragma();

	long seed();

	String getMetadata();

	String getFlashData();

	ImageBuilder createImageBuilder(FileFormatOption fileFormatOption) throws IOException;

	String getNamespaceSeparator();

	UmlSource getSource();

	String[] getDotStringSkek();

	// boolean isAutarkic(Entity g);

	int getUniqueSequence();

}
