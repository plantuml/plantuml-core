package net.sourceforge.plantuml.plasma;

class PEntry<DATA> {

	final Quark<DATA> first;
	int counter = 1;

	PEntry(Quark<DATA> first) {
		this.first = first;
	}

}
