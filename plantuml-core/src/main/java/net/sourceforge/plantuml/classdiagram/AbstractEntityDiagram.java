package net.sourceforge.plantuml.classdiagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.baraye.CucaDiagram;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.skin.UmlDiagramType;

public abstract class AbstractEntityDiagram extends CucaDiagram {

	public AbstractEntityDiagram(UmlSource source, UmlDiagramType type, Map<String, String> orig) {
		super(source, type, orig);
	}

	final protected List<String> getDotStrings() {
		final List<String> def = Arrays.asList("nodesep=.35;", "ranksep=0.8;", "edge [fontsize=11,labelfontsize=11];",
				"node [fontsize=11,height=.35,width=.55];");
		if (getPragma().isDefine("graphattributes") == false) {
			return def;
		}
		final String attribute = getPragma().getValue("graphattributes");
		final List<String> result = new ArrayList<>(def);
		result.add(attribute);
		return Collections.unmodifiableList(result);
	}

	final public DiagramDescription getDescription() {
		final StringBuilder result = new StringBuilder("(" + getEntityFactory().leafs().size() + " entities");
		if (getSource() != null) {
			final String id = getSource().getId();
			if (id != null) {
				result.append(", ");
				result.append(id);
			}
		}
		result.append(")");
		return new DiagramDescription(result.toString());
	}

}
