package net.sourceforge.plantuml;

import java.util.List;

import net.sourceforge.plantuml.preproc2.PreprocessorModeSet;

public interface DefinitionsContainer extends PreprocessorModeSet {

	public List<String> getDefinition(String name);

}
