package net.sourceforge.plantuml.sequencediagram.teoz;

import java.util.List;
import java.util.Objects;

import net.sourceforge.plantuml.klimt.UClip;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.skin.SimpleContext2D;

public class PlayingSpaceWithParticipants extends AbstractTextBlock implements TextBlock {

	private final PlayingSpace playingSpace;
	private XDimension2D cacheDimension;
	private double ymin;
	private double ymax;

	public PlayingSpaceWithParticipants(PlayingSpace playingSpace) {
		this.playingSpace = Objects.requireNonNull(playingSpace);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		if (cacheDimension == null) {
			final double width = playingSpace.getMaxX(stringBounder).getCurrentValue()
					- playingSpace.getMinX(stringBounder).getCurrentValue();

			final int factor = playingSpace.isShowFootbox() ? 2 : 1;
			final double height = playingSpace.getPreferredHeight(stringBounder)
					+ factor * playingSpace.getLivingSpaces().getHeadHeight(stringBounder);

			cacheDimension = new XDimension2D(width, height);
		}
		return cacheDimension;
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();

		final Context2D context = new SimpleContext2D(false);
		final double height = playingSpace.getPreferredHeight(stringBounder);
		final LivingSpaces livingSpaces = playingSpace.getLivingSpaces();

		final double headHeight = livingSpaces.getHeadHeight(stringBounder);

		if (ymax == 0) {
			playingSpace.drawBackground(ug.apply(UTranslate.dy(headHeight)));
		} else {
			final UClip clip = new UClip(-1000, ymin, Double.MAX_VALUE, ymax - ymin + 1);
			playingSpace.drawBackground(ug.apply(UTranslate.dy(headHeight)).apply(clip));
		}

		livingSpaces.drawLifeLines(ug.apply(UTranslate.dy(headHeight)), height, context);

		livingSpaces.drawHeads(ug, context, VerticalAlignment.BOTTOM);
		if (playingSpace.isShowFootbox()) {
			livingSpaces.drawHeads(ug.apply(UTranslate.dy(height + headHeight)), context, VerticalAlignment.TOP);
		}
		if (ymax == 0) {
			playingSpace.drawForeground(ug.apply(UTranslate.dy(headHeight)));
		} else {
			final UClip clip = new UClip(-1000, ymin, Double.MAX_VALUE, ymax - ymin + 1);
			// playingSpace.drawForeground(new
			// UGraphicNewpages(ug.apply(UTranslate.dy(headHeight)), ymin, ymax));
			playingSpace.drawForeground(ug.apply(UTranslate.dy(headHeight)).apply(clip));
		}
		// drawNewPages(ug.apply(UTranslate.dy(headHeight)));
	}

	public Real getMinX(StringBounder stringBounder) {
		return playingSpace.getMinX(stringBounder);
	}

	public int getNbPages() {
		return playingSpace.getNbPages();
	}

	public void setIndex(int index) {
		final List<Double> yNewPages = playingSpace.yNewPages();
		this.ymin = yNewPages.get(index);
		this.ymax = yNewPages.get(index + 1);
	}

	private List<Double> yNewPages() {
		return playingSpace.yNewPages();
	}

	private void drawNewPages(UGraphic ug) {
		ug = ug.apply(HColors.BLUE);
		for (Double change : yNewPages()) {
			if (change == 0 || change == Double.MAX_VALUE) {
				continue;
			}
			ug.apply(UTranslate.dy(change)).draw(ULine.hline(100));
		}
	}

}
