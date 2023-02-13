package net.sourceforge.plantuml.creole.command;

import net.sourceforge.plantuml.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleSpace implements Command {
	
	@Override
	public String startingChars() {
		return "<";
	}

	private static final Pattern2 pattern = MyPattern.cmpile("^(\\<space:(\\d+)/?\\>)");

	private CommandCreoleSpace() {
	}

	public static Command create() {
		return new CommandCreoleSpace();
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
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		// final int size = Integer.parseInt(m.group(2));
		// final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		// final FontConfiguration fc2 = fc1.changeSize(size);
		// stripe.setActualFontConfiguration(fc2);
		// stripe.analyzeAndAdd(m.group(3));
		final int size = Integer.parseInt(m.group(2));
		stripe.addSpace(size);
		// stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

}
