package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;

public class SkinParamForecolored extends SkinParamDelegator {

	final private HColor forecolor;

	public SkinParamForecolored(ISkinParam skinParam, HColor forecolor) {
		super(skinParam);
		this.forecolor = forecolor;
	}

	@Override
	public HColor getHtmlColor(ColorParam param, Stereotype stereotype, boolean clickable) {
		return forecolor;
	}

}
