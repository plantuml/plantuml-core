package net.sourceforge.plantuml.klimt.creole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public class Sheet implements Iterable<Stripe> {

	private final List<Stripe> stripes = new ArrayList<>();
	private final HorizontalAlignment horizontalAlignment;

	public Sheet(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	@Override
	public String toString() {
		return stripes.toString();
	}

	public void add(Stripe stripe) {
		stripes.add(stripe);
	}

	public Iterator<Stripe> iterator() {
		return stripes.iterator();
	}

	public Stripe getLastStripe() {
		final int size = stripes.size();
		if (size == 0) {
			return null;
		}
		return stripes.get(size - 1);
	}

	public final HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}
}