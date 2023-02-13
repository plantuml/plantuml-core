package net.sourceforge.plantuml.api;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.url.CMapData;

public class ImageDataComplex extends ImageDataAbstract {

	private final CMapData cmap;
	private final String warningOrError;

	@SuppressWarnings("unused")  // available publicly so retained for backwards compatibility
	
	public ImageDataComplex(XDimension2D info, CMapData cmap, String warningOrError) {
		super(info);
		this.cmap = cmap;
		this.warningOrError = warningOrError;
	}

	public ImageDataComplex(XDimension2D info, CMapData cmap, String warningOrError, int status) {
		super(info);
		this.cmap = cmap;
		this.warningOrError = warningOrError;
		setStatus(status);
	}

	public boolean containsCMapData() {
		return cmap != null && cmap.containsData();
	}

	public String getCMapData(String nameId) {
		return cmap.asString(nameId);
	}

	public String getWarningOrError() {
		return warningOrError;
	}

}
