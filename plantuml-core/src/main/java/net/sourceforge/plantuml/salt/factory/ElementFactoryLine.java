package net.sourceforge.plantuml.salt.factory;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementLine;

public class ElementFactoryLine implements ElementFactory {

	final private DataSource dataSource;

	public ElementFactoryLine(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final Terminated<String> next = dataSource.next();
		final String text = next.getElement();
		return new Terminated<Element>(new ElementLine(text.charAt(0)), next.getTerminator());
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		if (isLine(text, '-')) {
			return true;
		}
		if (isLine(text, '=')) {
			return true;
		}
		if (isLine(text, '~')) {
			return true;
		}
		if (isLine(text, '.')) {
			return true;
		}
		return false;
	}

	private boolean isLine(String text, char c) {
		final String s = "" + c + c;
		return text.startsWith(s) && text.endsWith(s);
	}
}
