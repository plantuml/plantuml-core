package net.sourceforge.plantuml.klimt.creole.legacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.Neutron;
import net.sourceforge.plantuml.klimt.creole.Parser;
import net.sourceforge.plantuml.klimt.creole.atom.Atom;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UText;

public class StripeCode implements StripeRaw {

	final private FontConfiguration fontConfiguration;
	private final List<String> raw = new ArrayList<>();

	private boolean terminated;

	public StripeCode(FontConfiguration fontConfiguration) {
		this.fontConfiguration = fontConfiguration;
	}

	public List<Atom> getAtoms() {
		return Collections.<Atom>singletonList(this);
	}

	public Atom getLHeader() {
		return null;
	}

	@Override
	public boolean addAndCheckTermination(String line) {
		if (Parser.isCodeEnd(line)) {
			this.terminated = true;
			return true;
		}
		this.raw.add(line);
		return false;
	}

	@Override
	public final boolean isTerminated() {
		return terminated;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		double width = 0;
		double height = 0;
		for (String s : raw) {
			final XDimension2D dim = stringBounder.calculateDimension(fontConfiguration.getFont(), s);
			width = Math.max(width, dim.getWidth());
			height += dim.getHeight();
		}
		return new XDimension2D(width, height);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return 0;
	}

	public void drawU(UGraphic ug) {
		double y = 0;
		for (String s : raw) {
			final UText shape = new UText(s, fontConfiguration);
			final StringBounder stringBounder = ug.getStringBounder();
			final XDimension2D dim = stringBounder.calculateDimension(fontConfiguration.getFont(), s);
			y += dim.getHeight();
			ug.apply(UTranslate.dy(y - shape.getDescent(stringBounder))).draw(shape);
		}
	}

	@Override
	public List<Neutron> getNeutrons() {
		return Arrays.asList(Neutron.create(this));
	}

}