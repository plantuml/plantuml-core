package net.sourceforge.plantuml.klimt.creole.legacy;

import net.sourceforge.plantuml.klimt.creole.Stripe;
import net.sourceforge.plantuml.klimt.creole.atom.Atom;

public interface StripeRaw extends Stripe, Atom {

	public boolean addAndCheckTermination(String line);

	public boolean isTerminated();

}