package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.creole.Parser;
import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleImg implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + Splitter.imgPatternNoSrcColon + ")");

	private CommandCreoleImg() {
	}

	public static Command create() {
		return new CommandCreoleImg();
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

		String src = m.group(2);
		final double scale = Parser.getScale(m.group(3), 1);
		if (src.toLowerCase().startsWith("src="))
			src = src.substring(4);

		src = StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(src, "\"");
		stripe.addImage(src, scale);
		return line.substring(m.group(1).length());
	}

}
