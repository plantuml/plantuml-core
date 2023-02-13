package net.sourceforge.plantuml.salt.factory;

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.SaltDictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;

public abstract class AbstractElementFactoryComplex implements ElementFactory {

	final private DataSource dataSource;
	final private Collection<ElementFactory> factories = new ArrayList<>();
	final private SaltDictionary dictionary;
	

	public AbstractElementFactoryComplex(DataSource dataSource, SaltDictionary dictionary) {
		this.dataSource = dataSource;
		this.dictionary = dictionary;
	}

	final public void addFactory(ElementFactory factory) {
		factories.add(factory);
	}

	protected Terminated<Element> getNextElement() {
		for (ElementFactory factory : factories) {
			if (factory.ready()) {
				return factory.create();
			}
		}
		throw new IllegalStateException(dataSource.peek(0).getElement());
	}

	protected final DataSource getDataSource() {
		return dataSource;
	}

	protected final SaltDictionary getDictionary() {
		return dictionary;
	}

}
