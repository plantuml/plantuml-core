package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.utils.Direction;

public class DirectionalTextBlock extends AbstractTextBlock implements TextBlock {
	private final TextBlock right;
	private final TextBlock left;
	private final TextBlock up;
	private final TextBlock down;
	private final GuideLine guideline;

	public DirectionalTextBlock(GuideLine guideline, TextBlock right, TextBlock left, TextBlock up, TextBlock down) {
		this.right = right;
		this.left = left;
		this.up = up;
		this.down = down;
		this.guideline = guideline;
	}

	public void drawU(UGraphic ug) {
		Direction dir = guideline.getArrowDirection();
		switch (dir) {
		case RIGHT:
			right.drawU(ug);
			break;
		case LEFT:
			left.drawU(ug);
			break;
		case UP:
			up.drawU(ug);
			break;
		case DOWN:
			down.drawU(ug);
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return right.calculateDimension(stringBounder);
	}

}
