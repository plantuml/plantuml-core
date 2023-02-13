package net.sourceforge.plantuml.ugraphic.comp;

import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.comp.CompressionMode;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class CompressionXorYBuilder {

	public static TextBlock build(CompressionMode mode, TextBlock textBlock, StringBounder stringBounder) {
		final PiecewiseAffineTransform affine = getPiecewiseAffineTransform(mode, textBlock, stringBounder);
		return PiecewiseAffineOnXorYBuilder.build(mode, textBlock, affine);
	}

	private static PiecewiseAffineTransform getPiecewiseAffineTransform(CompressionMode mode, TextBlock textBlock,
			StringBounder stringBounder) {
		final SlotFinder slotFinder = SlotFinder.create(mode, stringBounder);
		textBlock.drawU(slotFinder);
		final SlotSet ysSlotSet = slotFinder.getSlotSet().reverse().smaller(5.0);
		return new CompressionTransform(ysSlotSet);
	}

}
