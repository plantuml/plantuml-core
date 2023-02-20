package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleEmoji implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + Splitter.emojiPattern + ")");

	private CommandCreoleEmoji() {
	}

	public static Command create() {
		return new CommandCreoleEmoji();
	}

	public int matchingSize(String line) {
		final Matcher2 m = pattern.matcher(line);
		if (m.find() == false)
			return 0;

		return m.group(1).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) {
		final Matcher2 m = pattern.matcher(line);
		if (m.find() == false)
			throw new IllegalStateException();

		final String color = m.group(2);
		final String emoji = m.group(3);
		stripe.addEmoji(emoji, color);
		return line.substring(m.group(1).length());
	}

}
