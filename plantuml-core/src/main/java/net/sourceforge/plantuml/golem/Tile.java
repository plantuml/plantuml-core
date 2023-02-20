package net.sourceforge.plantuml.golem;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import net.sourceforge.plantuml.SpriteContainerEmpty;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public class Tile extends AbstractTextBlock implements TextBlock {

	private static double SIZE = 40;
	private final int num;

	private final UFont numberFont = UFont.monospaced(11);
	private final FontConfiguration fc = FontConfiguration.blackBlueTrue(numberFont);
	private final Map<TileGeometry, TileArea> geometries;

	Tile(int num) {
		this.num = num;
		final Map<TileGeometry, TileArea> tmp = new EnumMap<TileGeometry, TileArea>(TileGeometry.class);
		for (TileGeometry g : TileGeometry.values()) {
			tmp.put(g, new TileArea(this, g));
		}
		this.geometries = Collections.unmodifiableMap(tmp);
	}

	public TileArea getArea(TileGeometry geometry) {
		return this.geometries.get(geometry);
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(HColors.BLACK);
		final TextBlock n = Display.create("" + num).create(fc, HorizontalAlignment.LEFT, new SpriteContainerEmpty());
		final XDimension2D dimNum = n.calculateDimension(ug.getStringBounder());
		final XDimension2D dimTotal = calculateDimension(ug.getStringBounder());
		final double diffx = dimTotal.getWidth() - dimNum.getWidth();
		final double diffy = dimTotal.getHeight() - dimNum.getHeight();
		final double radius = Math.max(dimNum.getWidth(), dimNum.getHeight());
		final double diffx2 = dimTotal.getWidth() - radius;
		final double diffy2 = dimTotal.getHeight() - radius;
		n.drawU(ug.apply(new UTranslate((diffx / 2), (diffy / 2))));
		ug.draw(new URectangle(SIZE, SIZE));
		ug.apply(new UTranslate(diffx2 / 2, diffy2 / 2)).draw(new UEllipse(radius, radius));
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(SIZE, SIZE);
	}
}
