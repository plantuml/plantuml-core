package net.sourceforge.plantuml.command;

import java.util.Map;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.ErrorUmlType;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.error.PSystemErrorUtils;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.LineLocation;
import net.sourceforge.plantuml.utils.StartUtils;
import net.sourceforge.plantuml.version.IteratorCounter2;

public abstract class PSystemSingleLineFactory extends PSystemAbstractFactory {

	protected abstract AbstractPSystem executeLine(UmlSource source, String line);

	protected PSystemSingleLineFactory() {
		super(DiagramType.UML);
	}

	@Override
	final public Diagram createSystem(UmlSource source, Map<String, String> skinParam) {

		if (source.getTotalLineCount() != 3)
			return null;

		final IteratorCounter2 it = source.iterator2();
		if (source.isEmpty()) {
			final LineLocation location = it.next().getLocation();
			return buildEmptyError(source, location, it.getTrace());
		}

		final StringLocated startLine = it.next();
		if (StartUtils.isArobaseStartDiagram(startLine.getString()) == false)
			throw new UnsupportedOperationException();

		if (it.hasNext() == false)
			return buildEmptyError(source, startLine.getLocation(), it.getTrace());

		final StringLocated s = it.next();
		if (StartUtils.isArobaseEndDiagram(s.getString()))
			return buildEmptyError(source, s.getLocation(), it.getTrace());

		final AbstractPSystem sys = executeLine(source, s.getString());
		if (sys == null) {
			final ErrorUml err = new ErrorUml(ErrorUmlType.SYNTAX_ERROR, "Syntax Error?", 0, s.getLocation());
			// return PSystemErrorUtils.buildV1(source, err, null);
			return PSystemErrorUtils.buildV2(source, err, null, it.getTrace());
		}
		return sys;

	}

}
