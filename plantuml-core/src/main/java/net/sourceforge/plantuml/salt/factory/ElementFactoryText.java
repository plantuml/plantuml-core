package net.sourceforge.plantuml.salt.factory;

import java.util.Arrays;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementText;
import net.sourceforge.plantuml.style.ISkinSimple;

public class ElementFactoryText implements ElementFactory {

	final private DataSource dataSource;
	final private ISkinSimple spriteContainer;

	public ElementFactoryText(DataSource dataSource, ISkinSimple spriteContainer) {
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
		return new Terminated<Element>(new ElementText(Arrays.asList(text), font, spriteContainer),
				next.getTerminator());
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		// return text.startsWith("\"") && text.endsWith("\"");
		if (text.startsWith("{") || text.startsWith("}")) {
			return false;
		}
		return StringUtils.trin(text).length() > 0;
	}

}
