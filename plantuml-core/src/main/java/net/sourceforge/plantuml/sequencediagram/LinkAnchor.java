package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.activitydiagram3.ftile.Snake;
import net.sourceforge.plantuml.decoration.Rainbow;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.sequencediagram.teoz.CommonTile;
import net.sourceforge.plantuml.sequencediagram.teoz.YGauge;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class LinkAnchor {

	private final String anchor1;
	private final String anchor2;
	private final String message;

	public LinkAnchor(String anchor1, String anchor2, String message) {
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		this.message = message;
	}

	@Override
	public String toString() {
		return anchor1 + "<->" + anchor2 + " " + message;
	}

	public final String getAnchor1() {
		return anchor1;
	}

	public final String getAnchor2() {
		return anchor2;
	}

	public final String getMessage() {
		return message;
	}

	public void drawAnchor(UGraphic ug, CommonTile tile1, CommonTile tile2, ISkinParam skinParam) {

		final double y1;
		final double y2;
		if (YGauge.USE_ME) {
			y1 = (tile1.getYGauge().getMin().getCurrentValue() + tile1.getYGauge().getMax().getCurrentValue()) / 2
					+ tile1.getContactPointRelative();
			y2 = (tile2.getYGauge().getMin().getCurrentValue() + tile2.getYGauge().getMax().getCurrentValue()) / 2
					+ tile2.getContactPointRelative();
		} else {
			y1 = tile1.getTimeHook().getValue() + tile1.getContactPointRelative();
			y2 = tile2.getTimeHook().getValue() + tile2.getContactPointRelative();
		}
		final double xx1 = tile1.getMiddleX();
		final double xx2 = tile2.getMiddleX();
		final double x = (xx1 + xx2) / 2;
		final double ymin = Math.min(y1, y2);
		final double ymax = Math.max(y1, y2);

		final StyleSignatureBasic signature = StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram,
				SName.arrow);
		final Style style = signature.getMergedStyle(skinParam.getCurrentStyleBuilder());

		final HColor color = style.value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		final FontConfiguration fontConfiguration = FontConfiguration.create(skinParam, style);

		final Rainbow rainbow = Rainbow.fromColor(color, null);

		final Display display = Display.getWithNewlines(message);
		final TextBlock title = display.create(fontConfiguration, HorizontalAlignment.CENTER, skinParam);
		final Snake snake = Snake.create(skinParam, skinParam.arrows().asToUp(), rainbow, skinParam.arrows().asToDown())
				.withLabel(title, HorizontalAlignment.CENTER);

		snake.addPoint(x, ymin + 2);
		snake.addPoint(x, ymax - 2);
		snake.drawInternal(ug);
	}

}
