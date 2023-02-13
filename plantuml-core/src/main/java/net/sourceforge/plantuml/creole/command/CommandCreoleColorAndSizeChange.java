package net.sourceforge.plantuml.creole.command;

import net.sourceforge.plantuml.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.color.NoSuchColorRuntimeException;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleColorAndSizeChange implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private final Pattern2 mypattern;

	public static final String fontPattern = "\\<font(?:[%s]+size[%s]*=[%s]*[%g]?(\\d+)[%g]?|[%s]+color[%s]*=[%s]*[%g]?(#[0-9a-fA-F]{6}|\\w+)[%g]?)+[%s]*\\>";

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + fontPattern + "(.*?)\\</font\\>)");

	private static final Pattern2 patternEol = MyPattern.cmpile("^(" + fontPattern + "(.*))$");

	public static Command create() {
		return new CommandCreoleColorAndSizeChange(pattern);
	}

	public static Command createEol() {
		return new CommandCreoleColorAndSizeChange(patternEol);
	}

	private CommandCreoleColorAndSizeChange(Pattern2 pattern) {
		this.mypattern = pattern;
	}

	public int matchingSize(String line) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false)
			return 0;

		return m.group(1).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) throws NoSuchColorRuntimeException {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false)
			throw new IllegalStateException();

		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		FontConfiguration fc2 = fc1;
		if (m.group(2) != null)
			fc2 = fc2.changeSize(Integer.parseInt(m.group(2)));

		try {
			if (m.group(3) != null) {
				final String s = m.group(3);
				final HColor color = HColorSet.instance().getColor(s);
				fc2 = fc2.changeColor(color);
			}

			stripe.setActualFontConfiguration(fc2);
			stripe.analyzeAndAdd(m.group(4));
			stripe.setActualFontConfiguration(fc1);
			return line.substring(m.group(1).length());
		} catch (NoSuchColorException e) {
			throw new NoSuchColorRuntimeException();
		}
	}

}
