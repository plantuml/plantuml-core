package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.klimt.color.HColor;

public class SkinParamBackcoloredReference extends SkinParamDelegator {

	final private HColor sequenceReferenceHeaderBackground;
	final private HColor sequenceReferenceBackground;

	public SkinParamBackcoloredReference(ISkinParam skinParam, HColor sequenceReferenceHeaderBackground,
			HColor sequenceReferenceBackground) {
		super(skinParam);
		this.sequenceReferenceBackground = sequenceReferenceBackground;
		this.sequenceReferenceHeaderBackground = sequenceReferenceHeaderBackground;
	}

	@Override
	public HColor getHtmlColor(ColorParam param, Stereotype stereotype, boolean clickable) {
		if (param == ColorParam.sequenceReferenceHeaderBackground && sequenceReferenceHeaderBackground != null) {
			return sequenceReferenceHeaderBackground;
		}
		if (param == ColorParam.sequenceReferenceBackground && sequenceReferenceBackground != null) {
			return sequenceReferenceBackground;
		}
		return super.getHtmlColor(param, stereotype, clickable);
	}

}
