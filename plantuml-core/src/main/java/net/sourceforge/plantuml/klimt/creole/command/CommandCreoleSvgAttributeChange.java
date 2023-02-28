package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.SvgAttributes;
import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleSvgAttributeChange implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	public static final String fontPattern = Splitter.svgAttributePattern;

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + fontPattern + "(.*?)\\</text\\>)");
	private static final Pattern2 patternEol = MyPattern.cmpile("^(" + fontPattern + "(.*))$");

	private final Pattern2 mypattern;

	public static Command create() {
		return new CommandCreoleSvgAttributeChange(pattern);
	}

	public static Command createEol() {
		return new CommandCreoleSvgAttributeChange(patternEol);
	}

	private CommandCreoleSvgAttributeChange(Pattern2 p) {
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
		FontConfiguration fc2 = fc1;
		if (m.group(2) != null) {
			fc2 = fc2.changeAttributes(new SvgAttributes(m.group(2)));
		}

		stripe.setActualFontConfiguration(fc2);
		stripe.analyzeAndAdd(m.group(3));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}
}