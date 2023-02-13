package net.sourceforge.plantuml.plasma;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plasma<DATA> {

	private String separator;
	private final Quark<DATA> root;
	private final List<Quark<DATA>> quarks = new ArrayList<>();
	private final Map<String, PEntry<DATA>> stats = new HashMap<String, PEntry<DATA>>();

	public Plasma(String separator) {
		this.separator = separator;
		this.root = new Quark<DATA>(this, null, "");
	}

	protected void register(Quark<DATA> quark) {
		quarks.add(quark);
		PEntry<DATA> ent = stats.get(quark.getName());
		if (ent == null) {
			ent = new PEntry<DATA>(quark);
			stats.put(quark.getName(), ent);
		} else {
			ent.counter++;
		}
	}

	public Quark<DATA> root() {
		return root;
	}

	public final String getSeparator() {
		return separator;
	}

	public final void setSeparator(String separator) {
		if (separator == null)
			separator = "\u0000";
		this.separator = separator;
	}

	public final boolean hasSeparator() {
		return this.separator != null && this.separator != "\u0000";
	}

	public Collection<Quark<DATA>> quarks() {
		return Collections.unmodifiableCollection(quarks);
	}

	public Quark<DATA> firstWithName(String name) {
		final PEntry<DATA> ent = stats.get(name);
		if (ent == null)
			return null;
		return ent.first;
	}

	public int countByName(String name) {
		final PEntry<DATA> ent = stats.get(name);
		if (ent == null)
			return 0;
		return ent.counter;
	}

}
