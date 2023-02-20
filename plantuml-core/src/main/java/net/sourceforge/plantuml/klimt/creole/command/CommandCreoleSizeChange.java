package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleSizeChange implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private final Pattern2 mypattern;

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + Splitter.fontSizePattern2 + "(.*?)\\</size\\>)");
	private static final Pattern2 patternEol = MyPattern.cmpile("^(" + Splitter.fontSizePattern2 + "(.*)$)");

	public static Command create() {
		return new CommandCreoleSizeChange(pattern);
	}

	public static Command createEol() {
		return new CommandCreoleSizeChange(patternEol);
	}

	private CommandCreoleSizeChange(Pattern2 p) {
		this.mypattern = p;
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
		final int size = Integer.parseInt(m.group(2));
		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		final FontConfiguration fc2 = fc1.changeSize(size);
		// final FontConfiguration fc2 = new AddStyle(style, null).apply(fc1);
		stripe.setActualFontConfiguration(fc2);
		stripe.analyzeAndAdd(m.group(3));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

}
