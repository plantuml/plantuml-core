package net.sourceforge.plantuml.klimt.creole;

import java.util.List;

import net.sourceforge.plantuml.klimt.creole.atom.Atom;

public interface Stripe {

	public Atom getLHeader();

	public List<Atom> getAtoms();

}
