package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontPosition;
import net.sourceforge.plantuml.regex.Matcher2;

public class CommandCreoleExposantChange extends CommandCreoleCache implements Command {

	private final FontPosition position;

	@Override
	public String startingChars() {
		return "<";
	}

	private CommandCreoleExposantChange(String p, FontPosition position) {
		super(p);
		this.position = position;
	}

	public static Command create(FontPosition position) {
		return new CommandCreoleExposantChange(
				"^(" + "\\<" + position.getHtmlTag() + "\\>" + "(.*?)\\</" + position.getHtmlTag() + "\\>)", position);
	}

	public int matchingSize(String line) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false) {
			return 0;
		}
		return m.group(2).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		final FontConfiguration fc2 = fc1.changeFontPosition(position);

		stripe.setActualFontConfiguration(fc2);
		stripe.analyzeAndAdd(m.group(2));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

}