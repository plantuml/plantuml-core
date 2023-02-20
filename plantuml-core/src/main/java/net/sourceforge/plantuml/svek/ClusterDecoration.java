package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.decoration.symbol.USymbol;
import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.ClusterPosition;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public class ClusterDecoration {

	private final UStroke defaultStroke;
	final private USymbol symbol;
	final private TextBlock title;
	final private TextBlock stereo;

	final private ClusterPosition clusterPosition;

	public ClusterDecoration(PackageStyle style, USymbol symbol, TextBlock title, TextBlock stereo,
			ClusterPosition clusterPosition, UStroke stroke) {
		this.symbol = guess(symbol, style);
		this.stereo = stereo;
		this.title = title;
		this.clusterPosition = clusterPosition;
		this.defaultStroke = stroke;
	}

	private static USymbol guess(USymbol symbol, PackageStyle style) {
		if (symbol != null)
			return symbol;

		return style.toUSymbol();
	}

	public void drawU(UGraphic ug, HColor backColor, HColor borderColor, double shadowing, double roundCorner,
			HorizontalAlignment titleAlignment, HorizontalAlignment stereoAlignment, double diagonalCorner) {
		final Fashion biColor = new Fashion(backColor, borderColor);
		if (symbol == null)
			throw new UnsupportedOperationException();

		final Fashion symbolContext = biColor.withShadow(shadowing).withStroke(defaultStroke).withCorner(roundCorner,
				diagonalCorner);
		symbol.asBig(title, titleAlignment, stereo, clusterPosition.getWidth(), clusterPosition.getHeight(),
				symbolContext, stereoAlignment).drawU(ug.apply(clusterPosition.getPosition()));
	}

}
