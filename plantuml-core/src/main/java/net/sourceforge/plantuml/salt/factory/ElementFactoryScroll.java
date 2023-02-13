package net.sourceforge.plantuml.salt.factory;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Positionner2;
import net.sourceforge.plantuml.salt.SaltDictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementPyramidScrolled;

public class ElementFactoryScroll extends AbstractElementFactoryComplex {

	public ElementFactoryScroll(DataSource dataSource, SaltDictionary dictionary) {
		super(dataSource, dictionary);
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final Terminated<String> tmp = getDataSource().next();
		final String header = tmp.getElement();
		assert header.startsWith("{");

		final Positionner2 positionner = new Positionner2();

		while (getDataSource().peek(0).getElement().equals("}") == false) {
			final Terminated<Element> next = getNextElement();
			positionner.add(next);
		}
		final Terminated<String> next = getDataSource().next();
		return new Terminated<Element>(new ElementPyramidScrolled(positionner, getDictionary(),
				ScrollStrategy.fromDesc(header)), next.getTerminator());
	}

	public boolean ready() {
		final String text = getDataSource().peek(0).getElement();
		if (text.equals("{S") || text.equals("{S-") || text.equals("{SI")) {
			return true;
		}
		return false;
	}
}
