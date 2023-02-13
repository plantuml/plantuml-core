package net.sourceforge.plantuml.salt.factory;

import java.util.Arrays;
import java.util.List;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementRadioCheckbox;

public class ElementFactoryRadioOn implements ElementFactory {

	final private DataSource dataSource;
	final private ISkinSimple spriteContainer;

	public ElementFactoryRadioOn(DataSource dataSource, ISkinSimple spriteContainer) {
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
		return new Terminated<Element>(new ElementRadioCheckbox(extracted(text), font, true, true, spriteContainer),
				next.getTerminator());
	}

	private List<String> extracted(final String text) {
		final int x = text.indexOf(')');
		return Arrays.asList(StringUtils.trin(text.substring(x + 1)));
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		return text.startsWith("(X)");
	}
}
