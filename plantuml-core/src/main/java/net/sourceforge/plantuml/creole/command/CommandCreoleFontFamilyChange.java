package net.sourceforge.plantuml.creole.command;

import net.sourceforge.plantuml.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.graphic.Splitter;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleFontFamilyChange implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private static final Pattern2 pattern = MyPattern
			.cmpile("^(" + Splitter.fontFamilyPattern + "(.*?)\\</font\\>)");

	private static final Pattern2 patternEol = MyPattern.cmpile("^(" + Splitter.fontFamilyPattern + "(.*)$)");

	private final Pattern2 mypattern;

	public static Command create() {
		return new CommandCreoleFontFamilyChange(pattern);
	}

	public static Command createEol() {
		return new CommandCreoleFontFamilyChange(patternEol);
	}

	private CommandCreoleFontFamilyChange(Pattern2 p) {
		this.mypattern = p;

	}

	public int matchingSize(String line) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false) {
			return 0;
		}
		return m.group(1).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		final String family = m.group(2);
		final FontConfiguration fc2 = fc1.changeFamily(family);
		stripe.setActualFontConfiguration(fc2);
		stripe.analyzeAndAdd(m.group(3));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

}
