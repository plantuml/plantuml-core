package net.sourceforge.plantuml.preproc2;

import java.io.IOException;
import java.util.List;

import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.preproc.ReadLineList;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.StartUtils;

public class ReadFilterAddConfig implements ReadFilter {

	private final List<String> config;

	public ReadFilterAddConfig(List<String> config) {
		this.config = config;
	}

	public ReadLine applyFilter(final ReadLine raw) {

		return new ReadLine() {

			private ReadLine inserted;

			public void close() throws IOException {
				raw.close();
			}

			public StringLocated readLine() throws IOException {
				StringLocated result = null;
				if (inserted != null) {
					result = inserted.readLine();
					if (result == null) {
						inserted.close();
						inserted = null;
					} else {
						return result;
					}
				}
				result = raw.readLine();
				if (result != null && StartUtils.isArobaseStartDiagram(result.getString()) && config.size() > 0) {
					inserted = new ReadFilterQuoteComment().applyFilter(new ReadLineList(config, result.getLocation()));
				}
				return result;
			}
		};
	}

}
