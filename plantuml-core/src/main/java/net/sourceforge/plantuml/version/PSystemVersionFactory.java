package net.sourceforge.plantuml.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.command.PSystemSingleLineFactory;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.utils.Log;

public class PSystemVersionFactory extends PSystemSingleLineFactory {

	@Override
	protected AbstractPSystem executeLine(UmlSource source, String line) {
		try {
			if (line.matches("(?i)^(authors?|about)\\s*$"))
				return PSystemVersion.createShowAuthors2(source);

			if (line.matches("(?i)^version\\s*$"))
				return PSystemVersion.createShowVersion2(source);

		} catch (Exception e) {
			Log.error("Error " + e);

		}
		return null;
	}
}
