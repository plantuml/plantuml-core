package net.sourceforge.plantuml.creole.atom;

import java.util.Arrays;
import java.util.List;

import net.sourceforge.plantuml.creole.Neutron;

public abstract class AbstractAtom implements Atom {

	public List<Neutron> getNeutrons() {
		return Arrays.asList(Neutron.create(this));
	}

}
