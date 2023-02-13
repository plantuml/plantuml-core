package net.sourceforge.plantuml.creole.command;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.creole.Parser;
import net.sourceforge.plantuml.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.graphic.Splitter;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleOpenIcon implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + Splitter.openiconPattern + ")");

	private CommandCreoleOpenIcon() {
	}

	public static Command create() {
		return new CommandCreoleOpenIcon();
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

		final String src = m.group(2);
		final double scale = Parser.getScale(m.group(3), 1);
		final String colorName = Parser.getColor(m.group(3));
		HColor color = null;
		if (colorName != null) {
			final ISkinSimple skinParam = stripe.getSkinParam();
			color = skinParam.getIHtmlColorSet().getColorOrWhite(colorName);
		}
		stripe.addOpenIcon(src, scale, color);
		return line.substring(m.group(1).length());
	}

}
