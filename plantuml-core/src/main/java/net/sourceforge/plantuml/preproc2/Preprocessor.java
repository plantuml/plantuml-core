package net.sourceforge.plantuml.preproc2;

import java.io.IOException;
import java.util.List;

import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.preproc.ReadLineNumbered;
import net.sourceforge.plantuml.text.StringLocated;

public class Preprocessor implements ReadLineNumbered {

	private final ReadLine source;

	public Preprocessor(List<String> config, ReadLine reader) throws IOException {
		final ReadFilterAnd filters = new ReadFilterAnd();
		// filters.add(new ReadLineQuoteComment(true));
		filters.add(new ReadFilterAddConfig(config));
		filters.add(new ReadFilterMergeLines());
		this.source = filters.applyFilter(reader);
	}

	public StringLocated readLine() throws IOException {
		return source.readLine();
	}

	public void close() throws IOException {
		this.source.close();
	}

//	public Set<FileWithSuffix> getFilesUsedTOBEREMOVED() {
//		// System.err.println("************************** WARNING **************************");
//		return Collections.emptySet();
//		// return Collections.unmodifiableSet(include.getFilesUsedGlobal());
//	}
}
