package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TextBlockSprited extends AbstractTextBlock {

	private final TextBlock parent;
	private final TextBlock sprite;

	public TextBlockSprited(TextBlock sprite, TextBlock parent) {
		this.sprite = sprite;
		this.parent = parent;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final double widthCircledCharacter = getCircledCharacterWithAndMargin(stringBounder);
		final double heightCircledCharacter = sprite.calculateDimension(stringBounder).getHeight();

		final XDimension2D dim = parent.calculateDimension(stringBounder);
		return new XDimension2D(dim.getWidth() + widthCircledCharacter,
				Math.max(heightCircledCharacter, dim.getHeight()));
	}

	private double getCircledCharacterWithAndMargin(StringBounder stringBounder) {
		return sprite.calculateDimension(stringBounder).getWidth() + 6.0;
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();

		sprite.drawU(ug);

		final double widthCircledCharacter = getCircledCharacterWithAndMargin(stringBounder);

		parent.drawU(ug.apply(UTranslate.dx(widthCircledCharacter)));
	}

}
