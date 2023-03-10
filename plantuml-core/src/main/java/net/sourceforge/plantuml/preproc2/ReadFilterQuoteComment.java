// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.preproc2;

import java.io.IOException;

import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.text.StringLocated;

public class ReadFilterQuoteComment implements ReadFilter {

	public ReadLine applyFilter(final ReadLine source) {
		return new ReadLine() {
			public void close() throws IOException {
				source.close();
			}

			public StringLocated readLine() throws IOException {
				boolean longComment = false;
				while (true) {
					final StringLocated result = source.readLine();
					if (result == null) {
						return null;
					}
					final String trim = result.getString().replace('\t', ' ').trim();
					if (longComment && trim.endsWith("'/")) {
						longComment = false;
						continue;
					}
					if (longComment) {
						continue;
					}
					if (trim.startsWith("'")) {
						continue;
					}
					if (trim.startsWith("/'") && trim.endsWith("'/")) {
						continue;
					}
					if (trim.startsWith("/'") && trim.contains("'/") == false) {
						longComment = true;
						continue;
					}
					return ((StringLocated) result).removeInnerComment();
				}
			}
		};
	}

}
