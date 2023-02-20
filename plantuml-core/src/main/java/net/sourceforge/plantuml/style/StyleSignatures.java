package net.sourceforge.plantuml.style;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.stereo.Stereostyles;
import net.sourceforge.plantuml.stereo.Stereotype;

public class StyleSignatures implements StyleSignature {

	private final List<StyleSignature> all = new ArrayList<StyleSignature>();

	public void add(StyleSignature signature) {
		all.add(signature);

	}

	@Override
	public String toString() {
		return all.toString();
	}

	@Override
	public Style getMergedStyle(StyleBuilder currentStyleBuilder) {
		if (all.size() == 0)
			throw new UnsupportedOperationException();
		Style result = null;
		for (StyleSignature basic : all) {
			final Style tmp = basic.getMergedStyle(currentStyleBuilder);
			if (result == null)
				result = tmp;
			else
				result = result.mergeWith(tmp, MergeStrategy.KEEP_EXISTING_VALUE_OF_STEREOTYPE);
		}
		return result;

	}

	@Override
	public StyleSignature withTOBECHANGED(Stereotype stereotype) {
		if (all.size() == 0)
			throw new UnsupportedOperationException();
		throw new UnsupportedOperationException();
	}

	@Override
	public StyleSignature with(Stereostyles stereostyles) {
		if (all.size() == 0)
			throw new UnsupportedOperationException();
		final StyleSignatures result = new StyleSignatures();
		for (StyleSignature basic : all)
			result.add(basic.with(stereostyles));

		return result;
	}

}
