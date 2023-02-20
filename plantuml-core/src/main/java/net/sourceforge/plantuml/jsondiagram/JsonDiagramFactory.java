package net.sourceforge.plantuml.jsondiagram;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.PSystemAbstractFactory;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.json.Json;
import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.json.ParseException;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.skin.UmlDiagramType;
import net.sourceforge.plantuml.style.parser.StyleParsingException;
import net.sourceforge.plantuml.text.BackSlash;
import net.sourceforge.plantuml.yaml.Highlighted;

public class JsonDiagramFactory extends PSystemAbstractFactory {

	public JsonDiagramFactory() {
		super(DiagramType.JSON);
	}

	@Override
	public Diagram createSystem(UmlSource source, Map<String, String> skinParam) {
		final List<Highlighted> highlighted = new ArrayList<>();
		StyleExtractor styleExtractor = null;
		JsonValue json;
		try {
			final StringBuilder sb = new StringBuilder();
			styleExtractor = new StyleExtractor(source.iterator2());
			final Iterator<String> it = styleExtractor.getIterator();
			it.next();
			while (true) {
				final String line = it.next();
				if (it.hasNext() == false)
					break;

				if (line.startsWith("#")) {
					if (Highlighted.matchesDefinition(line)) {
						highlighted.add(Highlighted.build(line));
						continue;
					}
				} else {
					sb.append(line);
					sb.append(BackSlash.CHAR_NEWLINE);
				}
			}
			json = Json.parse(sb.toString());
		} catch (ParseException e) {
			json = null;
		}
		final JsonDiagram result = new JsonDiagram(source, UmlDiagramType.JSON, json, highlighted, styleExtractor);
		if (styleExtractor != null)
			try {
				styleExtractor.applyStyles(result.getSkinParam());
			} catch (StyleParsingException e) {
				Logme.error(e);
			}

		return result;
	}

}
