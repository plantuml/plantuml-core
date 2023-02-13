package net.sourceforge.plantuml.creole.legacy;

import java.util.Collections;
import java.util.List;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.creole.CreoleContext;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.creole.Stripe;
import net.sourceforge.plantuml.creole.StripeStyle;
import net.sourceforge.plantuml.creole.StripeStyleType;
import net.sourceforge.plantuml.creole.atom.Atom;
import net.sourceforge.plantuml.creole.atom.AtomTree;
import net.sourceforge.plantuml.creole.atom.AtomWithMargin;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;

public class StripeTree implements Stripe {

	private FontConfiguration fontConfiguration;
	final private ISkinSimple skinParam;
	final private AtomTree tree;
	final private Atom marged;
	final private StripeStyle stripeStyle = new StripeStyle(StripeStyleType.TREE, 0, '\0');

	public StripeTree(FontConfiguration fontConfiguration, ISkinSimple skinParam, String line) {
		this.fontConfiguration = fontConfiguration;
		this.skinParam = skinParam;
		this.tree = new AtomTree(fontConfiguration.getColor());
		this.marged = new AtomWithMargin(tree, 2, 2);
		analyzeAndAdd(line);
	}

	public List<Atom> getAtoms() {
		return Collections.<Atom>singletonList(marged);
	}

	public Atom getLHeader() {
		return null;
	}

	public void analyzeAndAdd(String line) {
		final List<String> lines = StripeTable.getWithNewlinesInternal(line);
		for (String s : lines) {
			final StripeSimple cell = new StripeSimple(fontConfiguration, stripeStyle, new CreoleContext(), skinParam,
					CreoleMode.FULL);
			final String text = s.replaceFirst("^\\s*\\|_", "");
			final int level = computeLevel(s);
			cell.analyzeAndAdd(text);
			this.tree.addCell(StripeTable.asAtom(Collections.singletonList(cell), 0), level);
		}

	}

	private int computeLevel(String s) {
		int result = 1;
		while (s.length() > 0) {
			if (s.startsWith("  ")) {
				result++;
				s = s.substring(2);
				continue;
			} else if (s.startsWith("\t")) {
				result++;
				s = s.substring(1);
				continue;
			}
			return result;
		}
		return result;
	}

}
