package net.sourceforge.plantuml.hcl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.PSystemAbstractFactory;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.jsondiagram.JsonDiagram;
import net.sourceforge.plantuml.jsondiagram.StyleExtractor;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.skin.UmlDiagramType;
import net.sourceforge.plantuml.yaml.Highlighted;

public class HclDiagramFactory extends PSystemAbstractFactory {

	public HclDiagramFactory() {
		super(DiagramType.HCL);
	}

	@Override
	public Diagram createSystem(UmlSource source, Map<String, String> skinParam) {
		final List<Highlighted> highlighted = new ArrayList<>();
		JsonValue data = null;
		StyleExtractor styleExtractor = null;
		try {
			final HclSource list = new HclSource();
			styleExtractor = new StyleExtractor(source.iterator2());
			final Iterator<String> it = styleExtractor.getIterator();
			it.next();
			while (true) {
				final String line = it.next();
				if (it.hasNext() == false)
					break;

				list.add(line);
			}
			HclParser parser = new HclParser(list);
			data = parser.parseMe();
		} catch (Exception e) {
			Logme.error(e);
		}
		final JsonDiagram result = new JsonDiagram(source, UmlDiagramType.HCL, data, highlighted, styleExtractor);
//		if (styleExtractor != null) {
//			styleExtractor.applyStyles(result.getSkinParam());
//			final String title = styleExtractor.getTitle();
//			if (title != null)
//				result.setTitle(DisplayPositioned.single(Display.getWithNewlines(title), HorizontalAlignment.CENTER,
//						VerticalAlignment.CENTER));
//		}
		return result;
	}

}
