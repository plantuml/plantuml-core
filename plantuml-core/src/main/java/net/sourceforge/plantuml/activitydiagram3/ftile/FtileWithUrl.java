package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Objects;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.url.Url;

public class FtileWithUrl extends FtileDecorate {

	private final Url url;

	public FtileWithUrl(Ftile ftile, Url url) {
		super(ftile);
		this.url = Objects.requireNonNull(url);
	}

	public void drawU(UGraphic ug) {
		ug.startUrl(url);
		getFtileDelegated().drawU(ug);
		ug.closeUrl();
	}

}
