package net.sourceforge.plantuml.style.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleLoader;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.style.Value;

class Context {

	private final List<String> data = new ArrayList<String>();
	private final Map<PName, Value> map = new EnumMap<>(PName.class);
	private Context parent;

	public Context push(String newString) {
		if (newString.startsWith(":"))
			newString = newString.substring(1);
		final Context result = new Context();
		result.data.addAll(this.data);
		result.data.add(newString);
		result.parent = this;
		return result;
	}

	public Context pop() {
		if (size() == 0)
			throw new IllegalStateException();
		return this.parent;
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public int size() {
		return data.size();
	}

	public Collection<StyleSignatureBasic> toSignatures() {
		List<StyleSignatureBasic> results = new ArrayList<>(Collections.singletonList(StyleSignatureBasic.empty()));
		boolean star = false;
		for (Iterator<String> it = data.iterator(); it.hasNext();) {
			String s = it.next();
			if (s.endsWith("*")) {
				star = true;
				s = s.substring(0, s.length() - 1);
			}
			final String[] names = s.split(",");
			final List<StyleSignatureBasic> tmp = new ArrayList<>();
			for (StyleSignatureBasic ss : results)
				for (String name : names)
					tmp.add(ss.add(name.trim()));
			results = tmp;
		}

		if (star)
			for (ListIterator<StyleSignatureBasic> it = results.listIterator(); it.hasNext();) {
				final StyleSignatureBasic tmp = it.next().addStar();
				it.set(tmp);
			}

		return Collections.unmodifiableCollection(results);
	}

	public void putInContext(PName key, Value value) {
		map.put(key, value);
	}

	public Collection<Style> toStyles() {
		final Collection<Style> result = new ArrayList<>();
		final Collection<StyleSignatureBasic> signatures = toSignatures();
		for (StyleSignatureBasic signature : signatures) {
			Map<PName, Value> tmp = map;
			if (signature.isWithDot())
				tmp = StyleLoader.addPriorityForStereotype(tmp);
			if (tmp.size() > 0)
				result.add(new Style(signature, tmp));

		}
		return result;
	}

}
