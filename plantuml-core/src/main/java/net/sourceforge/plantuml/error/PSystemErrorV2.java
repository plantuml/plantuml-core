// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.error;

import java.util.List;

import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.text.StringLocated;

public class PSystemErrorV2 extends PSystemError {

	public PSystemErrorV2(UmlSource source, List<StringLocated> trace, ErrorUml singleError) {
		super(source);
		this.trace = trace;
		this.singleError = singleError;

	}

}
