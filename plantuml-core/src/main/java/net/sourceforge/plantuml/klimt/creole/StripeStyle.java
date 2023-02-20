package net.sourceforge.plantuml.klimt.creole;

import net.sourceforge.plantuml.klimt.creole.atom.Atom;
import net.sourceforge.plantuml.klimt.creole.atom.Bullet;
import net.sourceforge.plantuml.klimt.creole.legacy.AtomTextUtils;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;

public class StripeStyle {

	private final StripeStyleType type;
	private final int order;
	private final char style;

	public StripeStyle(StripeStyleType type, int order, char style) {
		this.type = type;
		this.order = order;
		this.style = style;
	}

	public final StripeStyleType getType() {
		return type;
	}

	public Atom getHeader(FontConfiguration fontConfiguration, CreoleContext context) {
		if (type == StripeStyleType.LIST_WITHOUT_NUMBER) {
			return new Bullet(fontConfiguration, order);
		}
		if (type == StripeStyleType.LIST_WITH_NUMBER) {
			final int localNumber = context.getLocalNumber(order);
			return AtomTextUtils.createListNumber(fontConfiguration, order, localNumber);
		}
		return null;
	}

	public final int getOrder() {
		return order;
	}

	public char getStyle() {
		return style;
	}
}
