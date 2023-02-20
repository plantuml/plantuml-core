package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.creole.legacy.StripeSimple;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;

public class CommandCreoleUrl implements Command {

	@Override
	public String startingChars() {
		return "[";
	}

	private static final Pattern2 pattern = MyPattern.cmpile("^(" + UrlBuilder.getRegexp() + ")");

	public static Command create() {
		return new CommandCreoleUrl();
	}

	private CommandCreoleUrl() {
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

		final UrlBuilder urlBuilder = new UrlBuilder(stripe.getSkinParam().getValue("topurl"), UrlMode.STRICT);
		final Url url = urlBuilder.getUrl(m.group(1));
		stripe.addUrl(url);
		return line.substring(m.group(1).length());
	}
}
