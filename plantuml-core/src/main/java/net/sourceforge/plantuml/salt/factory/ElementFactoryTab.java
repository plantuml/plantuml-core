package net.sourceforge.plantuml.salt.factory;

import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.SaltDictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.Terminator;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementTabBar;

public class ElementFactoryTab extends AbstractElementFactoryComplex {

	public ElementFactoryTab(DataSource dataSource, SaltDictionary dictionary) {
		super(dataSource, dictionary);
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final String header = getDataSource().next().getElement();
		assert header.startsWith("{/");

		final UFont font = UFont.byDefault(12);
		final ElementTabBar result = new ElementTabBar(font, getDictionary());

		while (getDataSource().peek(0).getElement().equals("}") == false) {
			final Terminated<String> t = getDataSource().next();
			result.addTab(t.getElement());
			if (t.getTerminator() == Terminator.NEWLINE) {
				result.setVertical(true);
			}
		}
		final Terminated<String> next = getDataSource().next();
		return new Terminated<Element>(result, next.getTerminator());
	}

	public boolean ready() {
		final String text = getDataSource().peek(0).getElement();
		if (text.equals("{/")) {
			return true;
		}
		return false;
	}
}
