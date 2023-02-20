package net.sourceforge.plantuml.eggs;

import java.util.Map;

import net.sourceforge.plantuml.api.PSystemFactory;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.geom.GraphicPosition;

public class PSystemWelcomeFactory implements PSystemFactory {

	@Override
	public Diagram createSystem(UmlSource source, Map<String, String> skinParam) {
		if (source.getTotalLineCount() == 2)
			return new PSystemWelcome(source, GraphicPosition.BACKGROUND_CORNER_BOTTOM_RIGHT);

		return null;
	}

	public DiagramType getDiagramType() {
		return DiagramType.UML;
	}

}
