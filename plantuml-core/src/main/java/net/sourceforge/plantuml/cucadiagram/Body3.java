package net.sourceforge.plantuml.cucadiagram;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.svek.Ports;
import net.sourceforge.plantuml.svek.WithPorts;

public class Body3 extends AbstractTextBlock implements TextBlock, WithPorts {

	private final List<CharSequence> rawBody = new ArrayList<>();
	private final ISkinParam skinParam;
	private final Stereotype stereotype;
	private final Style style;

	public Body3(List<CharSequence> rawBody_, ISkinParam skinParam, Stereotype stereotype, Style style) {
		for (CharSequence s : rawBody_)
			this.rawBody.add(VisibilityModifier.replaceVisibilityModifierByUnicodeChar(s.toString(), true));

		this.skinParam = skinParam;
		this.stereotype = stereotype;
		this.style = style;
	}

	public void drawU(UGraphic ug) {
		getTextBlock().drawU(ug);

	}

	private TextBlock getTextBlock() {
		final Display display = Display.create(rawBody);
		final FontConfiguration config = FontConfiguration.create(skinParam, style);
		return display.create(config, HorizontalAlignment.LEFT, skinParam);
	}

	@Override
	public Ports getPorts(StringBounder stringBounder) {
		return new Ports();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return getTextBlock().calculateDimension(stringBounder);
	}

}
