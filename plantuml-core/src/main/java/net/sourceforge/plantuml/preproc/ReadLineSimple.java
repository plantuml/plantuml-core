package net.sourceforge.plantuml.preproc;

import net.sourceforge.plantuml.text.StringLocated;

public class ReadLineSimple implements ReadLine {

	private final StringLocated data;
	private final String error;
	private int current = 0;

	public ReadLineSimple(StringLocated s2, String error) {
		this.data = s2;
		this.error = error;
	}

	public void close() {
	}

	public StringLocated readLine() {
		if (current > 0) {
			return null;
		}
		current++;
		// return new CharSequence2Impl(line, null);
		// return CharSequence2Impl.errorPreprocessor(data, error);
		return data.withErrorPreprocessor(error);
	}

}
