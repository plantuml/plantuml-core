package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.klimt.color.HColor;

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
