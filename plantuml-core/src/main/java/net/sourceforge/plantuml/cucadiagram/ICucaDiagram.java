package net.sourceforge.plantuml.cucadiagram;

import java.io.IOException;
import java.util.Collection;

import net.atmp.ImageBuilder;
import net.atmp.Link;
import net.sourceforge.plantuml.Annotated;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.baraye.EntityFactory;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.skin.Pragma;
import net.sourceforge.plantuml.skin.UmlDiagramType;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.StyleBuilder;

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
