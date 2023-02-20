package net.sourceforge.plantuml.skin;

import java.util.EnumMap;
import java.util.Map;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;

public class SkinParamBackcolored extends SkinParamDelegator {

	final private HColor backColorElement;
	final private HColor backColorGeneral;
	final private boolean forceClickage;

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement) {
		this(skinParam, backColorElement, null, false);
	}

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement, boolean forceClickage) {
		this(skinParam, backColorElement, null, forceClickage);
	}

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement, HColor backColorGeneral) {
		this(skinParam, backColorElement, backColorGeneral, false);
	}

	@Override
	public String toString() {
		return super.toString() + " " + backColorElement + " " + backColorGeneral;
	}

	public SkinParamBackcolored(ISkinParam skinParam, HColor backColorElement, HColor backColorGeneral,
			boolean forceClickage) {
		super(skinParam);
		this.forceClickage = forceClickage;
		this.backColorElement = backColorElement;
		this.backColorGeneral = backColorGeneral;
	}

	@Override
	public HColor getBackgroundColor() {
		if (backColorGeneral != null) {
			return backColorGeneral;
		}
		return super.getBackgroundColor();
	}

	@Override
	public HColor getHtmlColor(ColorParam param, Stereotype stereotype, boolean clickable) {
		if (param.isBackground() && backColorElement != null) {
			return backColorElement;
		}
		if (forceClickage) {
			final HColor c1 = super.getHtmlColor(param, stereotype, true);
			if (c1 != null) {
				return c1;
			}
			// clickable = true;
		}
		final HColor forcedColor = forced.get(param);
		if (forcedColor != null) {
			return forcedColor;
		}
		return super.getHtmlColor(param, stereotype, clickable);
	}

	private final Map<ColorParam, HColor> forced = new EnumMap<ColorParam, HColor>(ColorParam.class);

	public void forceColor(ColorParam param, HColor color) {
		forced.put(param, color);
	}

}
