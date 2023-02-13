package net.sourceforge.plantuml.creole.command;

import net.sourceforge.plantuml.creole.Parser;
import net.sourceforge.plantuml.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.graphic.Splitter;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class CommandCreoleQrcode implements Command {

	@Override
	public String startingChars() {
		return "<";
	}

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + Splitter.qrcodePattern + ")");

	private CommandCreoleQrcode() {
	}

	public static Command create() {
		return new CommandCreoleQrcode();
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
		final String src = m.group(2);
		final double scale = Parser.getScale(m.group(3), 3);
		stripe.addQrcode(src, scale);
		return line.substring(m.group(1).length());
	}

}
