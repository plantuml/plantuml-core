package net.sourceforge.plantuml.preproc2;

import net.sourceforge.plantuml.preproc.ReadLine;

public interface ReadFilter {

	public ReadLine applyFilter(ReadLine source);

}
