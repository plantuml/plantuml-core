package net.sourceforge.plantuml.creole.command;

import net.sourceforge.plantuml.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleMonospaced implements Command {

	@Override
	public String startingChars() {
		return "\"";
	}

	private final Pattern2 pattern;

	public static Command create() {
		return new CommandCreoleMonospaced("^(\"\"(.*?)\"\")");
	}

	private CommandCreoleMonospaced(String p) {
		this.pattern = MyPattern.cmpile(p);
	}

	public int matchingSize(String line) {
		final Matcher2 m = pattern.matcher(line);
		if (m.find() == false) {
			return 0;
		}
		return m.group(1).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) {
		final Matcher2 m = pattern.matcher(line);
		if (m.find() == false)
			throw new IllegalStateException();

		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		final FontConfiguration fc2 = fc1.changeFamily(stripe.getSkinParam().getMonospacedFamily());
		stripe.setActualFontConfiguration(fc2);
		stripe.analyzeAndAdd(m.group(2));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

}
