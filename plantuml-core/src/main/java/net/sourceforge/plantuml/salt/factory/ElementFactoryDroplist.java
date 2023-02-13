package net.sourceforge.plantuml.salt.factory;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementDroplist;

public class ElementFactoryDroplist implements ElementFactory {

	final private DataSource dataSource;
	final private ISkinSimple spriteContainer;

	public ElementFactoryDroplist(DataSource dataSource, ISkinSimple spriteContainer) {
		this.dataSource = dataSource;
		this.spriteContainer = spriteContainer; 
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final Terminated<String> next = dataSource.next();
		final String text = next.getElement();
		final UFont font = UFont.byDefault(12);
		return new Terminated<Element>(new ElementDroplist(text.substring(1, text.length() - 1), font, spriteContainer),
				next.getTerminator());
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		return text.startsWith("^") && text.endsWith("^");
	}
}
