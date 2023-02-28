package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;

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