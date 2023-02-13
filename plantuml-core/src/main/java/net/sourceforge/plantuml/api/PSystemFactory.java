package net.sourceforge.plantuml.api;

import java.util.Map;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public interface PSystemFactory {

	Diagram createSystem(UmlSource source, Map<String, String> skinParam);

	DiagramType getDiagramType();

}
