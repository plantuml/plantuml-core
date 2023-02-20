package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontStyle;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.UText;

public class TitleBox extends AbstractTextBlock {

	private final String value;
	private final FontConfiguration fc;
	private final UText utext;

	public TitleBox(String value, FontConfiguration fc) {
		this.value = value;
		this.fc = fc.add(FontStyle.BOLD);
		this.utext = new UText(value, this.fc);
	}

	@Override
	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return stringBounder.calculateDimension(fc.getFont(), value);
	}

	@Override
	public void drawU(UGraphic ug) {
		final XDimension2D dimText = calculateDimension(ug.getStringBounder());

		ug.apply(new UTranslate(0, dimText.getHeight() - utext.getDescent(ug.getStringBounder()))).draw(utext);
	}

}
