package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;

public class SkinParamForceColor extends SkinParamDelegator {

	final private ColorParam colorParam;;
	final private HColor color;

	public SkinParamForceColor(ISkinParam skinParam, ColorParam colorParam, HColor color) {
		super(skinParam);
		this.color = color;
		this.colorParam = colorParam;
	}

	@Override
	public HColor getHtmlColor(ColorParam param, Stereotype stereotype, boolean clickable) {
		if (colorParam == param) {
			return color;
		}
		// return color;
		return super.getHtmlColor(param, stereotype, clickable);
	}

}
