// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontStyle;
import net.sourceforge.plantuml.regex.Matcher2;

public class CommandCreoleStyle extends CommandCreoleCache implements Command {

	@Override
	public String startingChars() {
		return "</*_~-";
	}

	private final FontStyle style;
	private final boolean tryExtendedColor;

	public static Command createCreole(FontStyle style) {
		return new CommandCreoleStyle("^(" + style.getCreoleSyntax() + "(.+?)" + style.getCreoleSyntax() + ")", style,
				false);
	}

	public static Command createLegacy(FontStyle style) {
		return new CommandCreoleStyle(
				"^((" + style.getActivationPattern() + ")(.+?)" + style.getDeactivationPattern() + ")", style,
				style.canHaveExtendedColor());
	}

	public static Command createLegacyEol(FontStyle style) {
		return new CommandCreoleStyle("^((" + style.getActivationPattern() + ")(.+))$", style,
				style.canHaveExtendedColor());
	}

	private CommandCreoleStyle(String p, FontStyle style, boolean tryExtendedColor) {
		super(p);
		this.style = style;
		this.tryExtendedColor = tryExtendedColor;
	}

	private HColor getExtendedColor(Matcher2 m) {
		if (tryExtendedColor) {
			return style.getExtendedColor(m.group(2));
		}
		return null;
	}

	public String executeAndGetRemaining(final String line, StripeSimple stripe) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		final FontConfiguration fc2 = new AddStyle(style, getExtendedColor(m)).apply(fc1);
		stripe.setActualFontConfiguration(fc2);
		final int groupCount = m.groupCount();
		stripe.analyzeAndAdd(m.group(groupCount));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

	public int matchingSize(String line) {
		final Matcher2 m = mypattern.matcher(line);
		if (m.find() == false) {
			return 0;
		}
		return m.group(1).length();
	}

}
