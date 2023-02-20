package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractConnection;
import net.sourceforge.plantuml.activitydiagram3.ftile.ConnectionTranslatable;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Snake;
import net.sourceforge.plantuml.decoration.Rainbow;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.ISkinParam;

public class ConnectionVerticalDown extends AbstractConnection implements ConnectionTranslatable {

	private final XPoint2D p1;
	private final XPoint2D p2;
	private final Rainbow color;
	private final TextBlock textBlock;

	public ConnectionVerticalDown(Ftile ftile1, Ftile ftile2, XPoint2D p1, XPoint2D p2, Rainbow color,
			TextBlock textBlock) {
		super(ftile1, ftile2);
		if (color.size() == 0)
			throw new IllegalArgumentException();

		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
		this.textBlock = textBlock;
	}

	public void drawU(UGraphic ug) {
		ug.draw(getSimpleSnake());
	}

	public double getMaxX(StringBounder stringBounder) {
		return getSimpleSnake().getMaxX(stringBounder);
	}

	private Snake getSimpleSnake() {
		final ISkinParam skinParam = getFtile1().skinParam();
		final Snake snake = Snake.create(skinParam, color, skinParam.arrows().asToDown()).withLabel(textBlock,
				arrowHorizontalAlignment());
		snake.addPoint(p1);
		snake.addPoint(p2);
		return snake;
	}

	@Override
	public void drawTranslate(UGraphic ug, UTranslate translate1, UTranslate translate2) {
		final ISkinParam skinParam = getFtile1().skinParam();
		final Snake snake = Snake.create(skinParam, color, skinParam.arrows().asToDown()).withLabel(textBlock,
				arrowHorizontalAlignment());
		final XPoint2D mp1a = translate1.getTranslated(p1);
		final XPoint2D mp2b = translate2.getTranslated(p2);
		final double middle = (mp1a.getY() + mp2b.getY()) / 2.0;
		snake.addPoint(mp1a);
		snake.addPoint(mp1a.getX(), middle);
		snake.addPoint(mp2b.getX(), middle);
		snake.addPoint(mp2b);
		ug.draw(snake);

	}

}
