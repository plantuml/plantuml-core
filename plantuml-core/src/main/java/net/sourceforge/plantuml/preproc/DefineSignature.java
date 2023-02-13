package net.sourceforge.plantuml.preproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class DefineSignature {

	private final String key;
	private final String fonctionName;
	private final List<Variables> variables = new ArrayList<>();
	private final boolean isMethod;

	public DefineSignature(String key, String definitionQuoted) {
		this.key = key;
		this.isMethod = key.contains("(");

		final StringTokenizer st = new StringTokenizer(key, "(),");
		this.fonctionName = st.nextToken().trim();
		final Variables master = new Variables(fonctionName, definitionQuoted);

		while (st.hasMoreTokens()) {
			final String var1 = st.nextToken().trim();
			master.add(new DefineVariable(var1));
		}

		final int count = master.countDefaultValue();
		for (int i = 0; i <= count; i++) {
			variables.add(master.removeSomeDefaultValues(i));
		}
	}

	@Override
	public String toString() {
		return key + "/" + fonctionName;
	}

	public boolean isMethod() {
		return isMethod;
	}

	public String getKey() {
		return key;
	}

	public List<Variables> getVariationVariables() {
		return Collections.unmodifiableList(variables);
	}

	public final String getFonctionName() {
		return fonctionName;
	}

}
