package net.sourceforge.plantuml.creole;

import java.util.List;

import net.sourceforge.plantuml.creole.atom.Atom;

public interface Stripe {

	public Atom getLHeader();

	public List<Atom> getAtoms();

}
