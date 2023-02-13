package net.sourceforge.plantuml.error;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.text.StringLocated;

public class PSystemErrorUtils {

	public static PSystemError buildV2(UmlSource source, ErrorUml singleError, List<String> debugLines,
			List<StringLocated> list) {
//		if (source.isEmpty()) {
//			return new PSystemErrorEmpty(source, list, singleError);
//		}
		return new PSystemErrorV2(source, list, singleError);
	}

	public static PSystemError merge(Collection<PSystemError> ps) {
		if (ps.size() == 0)
			throw new IllegalStateException();

		UmlSource source = null;
		final List<ErrorUml> errors = new ArrayList<>();
		// final List<String> debugs = new ArrayList<>();
		final List<PSystemErrorV2> errorsV2 = new ArrayList<>();
		for (PSystemError system : ps) {
			if (system == null)
				continue;

			if (system.getSource() != null && source == null)
				source = system.getSource();

			errors.addAll(system.getErrorsUml());
			if (system instanceof PSystemErrorV2)
				errorsV2.add((PSystemErrorV2) system);
		}
		if (source == null)
			throw new IllegalStateException();

		if (errorsV2.size() > 0)
			return mergeV2(errorsV2);

		throw new IllegalStateException();
	}

	private static PSystemErrorV2 mergeV2(List<PSystemErrorV2> errorsV2) {
		PSystemErrorV2 result = null;
		for (PSystemErrorV2 err : errorsV2)
			if (result == null || result.score() < err.score())
				result = err;

		return result;
	}

	public static boolean isDiagramError(Class<? extends Diagram> type) {
		return PSystemError.class.isAssignableFrom(type);
	}

}
