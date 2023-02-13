package net.sourceforge.plantuml.api;

import java.util.List;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.Diagram;

public class PlantumlUtils {

	public static boolean hasCMapData(String uml) {
		List<BlockUml> blocks = new SourceStringReader(uml).getBlocks();
		if (blocks.size() == 0) {
			uml = "@startuml\n" + uml + "\n@enduml";
			blocks = new SourceStringReader(uml).getBlocks();
			if (blocks.size() == 0) {
				return false;
			}
		}
		final BlockUml block = blocks.get(0);
		final Diagram diagram = block.getDiagram();
		return diagram.hasUrl();
	}

}
