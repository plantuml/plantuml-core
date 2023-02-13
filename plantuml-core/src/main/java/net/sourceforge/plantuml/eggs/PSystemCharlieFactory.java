package net.sourceforge.plantuml.eggs;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.command.PSystemSingleLineFactory;
import net.sourceforge.plantuml.core.UmlSource;

public class PSystemCharlieFactory extends PSystemSingleLineFactory {

	@Override
	protected AbstractPSystem executeLine(UmlSource source, String line) {
		if (line.equalsIgnoreCase("charlie") || line.equalsIgnoreCase("jesuischarlie")) {
			return new PSystemCharlie(source);
		}
		return null;
	}

}
