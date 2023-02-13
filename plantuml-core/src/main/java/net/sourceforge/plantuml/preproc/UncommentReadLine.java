package net.sourceforge.plantuml.preproc;

import java.io.IOException;

import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.StartUtils;

public class UncommentReadLine implements ReadLine {

	private static final Pattern2 unpause = MyPattern.cmpile(StartUtils.PAUSE_PATTERN);

	private final ReadLine raw;
	private String headerToRemove;
	private boolean paused;

	public UncommentReadLine(ReadLine source) {
		this.raw = source;
	}

	public StringLocated readLine() throws IOException {
		final StringLocated result = raw.readLine();

		if (result == null) {
			return null;
		}

		final String tmp = StartUtils.beforeStartUml(result.getString());
		if (tmp != null) {
			headerToRemove = tmp;
		}
		if (paused) {
			final Matcher2 m2 = unpause.matcher(result.getString());
			if (m2.find()) {
				headerToRemove = m2.group(1);
			}
		}
		if (headerToRemove != null && headerToRemove.startsWith(result.getString())) {
			return new StringLocated("", result.getLocation());
		}
		if (headerToRemove != null && result.getString().startsWith(headerToRemove)) {
			return result.substring(headerToRemove.length(), result.getString().length());
		}
		return result;
	}

	public void close() throws IOException {
		this.raw.close();
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}
