package net.sourceforge.plantuml.creole.legacy;

import net.sourceforge.plantuml.creole.Stripe;
import net.sourceforge.plantuml.creole.atom.Atom;

public interface StripeRaw extends Stripe, Atom {

	public boolean addAndCheckTermination(String line);

	public boolean isTerminated();

}
