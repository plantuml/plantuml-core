package net.sourceforge.plantuml.creole.command;

import net.sourceforge.plantuml.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.graphic.Splitter;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.color.NoSuchColorRuntimeException;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleColorChange implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + Splitter.fontColorPattern2 + "(.*?)\\</color\\>)");

	private static final Pattern2 patternEol = MyPattern.cmpile("^(" + Splitter.fontColorPattern2 + "(.*)$)");

	private final Pattern2 mypattern;

	public static Command create() {
		return new CommandCreoleColorChange(pattern);
	}

	public static Command createEol() {
		return new CommandCreoleColorChange(patternEol);
	}

	private CommandCreoleColorChange(Pattern2 pattern) {
		this.mypattern = pattern;
	}

	public int matchingSize(String line) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false)
			return 0;

		return m.group(2).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) throws NoSuchColorRuntimeException {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false)
			throw new IllegalStateException();

		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		final String s = m.group(2);
		try {
			final HColor color = HColorSet.instance().getColor(s);
			final FontConfiguration fc2 = fc1.changeColor(color);
			stripe.setActualFontConfiguration(fc2);
		} catch (NoSuchColorException e) {
			// Too late for parsing error
			// So we just ignore
		}
		stripe.analyzeAndAdd(m.group(3));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

}
