package net.sourceforge.plantuml.preproc2;

import net.sourceforge.plantuml.preproc.ImportedFiles;

public interface PreprocessorModeSet {

	public ImportedFiles getImportedFiles();

	public String getCharset();

}
