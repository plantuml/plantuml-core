package net.sourceforge.plantuml.salt.factory;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.SaltDictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementImage;

public class ElementFactoryImage implements ElementFactory {

	final private DataSource dataSource;
	final private SaltDictionary dictionary;

	public ElementFactoryImage(DataSource dataSource, SaltDictionary dictionary) {
		this.dataSource = dataSource;
		this.dictionary = dictionary;
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final String header = dataSource.next().getElement();
		final String name = header.length() > 2 ? header.substring(2) : null;
		final List<String> img = new ArrayList<>();
		while (dataSource.peek(0).getElement().equals(">>") == false) {
			img.add(dataSource.next().getElement());
		}
		final Terminated<String> next = dataSource.next();
		final ElementImage element = new ElementImage(img);
		if (name != null) {
			dictionary.put(name, element);
		}
		return new Terminated<Element>(element, next.getTerminator());
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		return text.equals("<<") || text.matches("\\<\\<\\w+");
	}
}
