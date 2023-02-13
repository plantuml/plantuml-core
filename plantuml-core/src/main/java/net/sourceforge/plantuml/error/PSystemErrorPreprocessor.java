package net.sourceforge.plantuml.error;

import java.util.List;

import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.ErrorUmlType;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.text.StringLocated;

public class PSystemErrorPreprocessor extends PSystemError {

	public PSystemErrorPreprocessor(List<StringLocated> input, List<StringLocated> trace) {
		super(UmlSource.create(input, DiagramType.getTypeFromArobaseStart(input.get(0).getString()) == DiagramType.UML));
		this.trace = trace;
		this.singleError = new ErrorUml(ErrorUmlType.SYNTAX_ERROR, getLastLine().getPreprocessorError(), 0,
				getLastLine().getLocation());

	}

}
