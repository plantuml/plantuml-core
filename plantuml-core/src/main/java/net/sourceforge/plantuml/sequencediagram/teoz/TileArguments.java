package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.sequencediagram.Reference;
import net.sourceforge.plantuml.skin.SkinParamBackcolored;
import net.sourceforge.plantuml.skin.SkinParamBackcoloredReference;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.style.ISkinParam;

public class TileArguments implements Bordered {
	private final StringBounder stringBounder;
	private final Real xorigin;
	private final Real yorigin;
	private final LivingSpaces livingSpaces;
	private final Rose skin;
	private final ISkinParam skinParam;

	public TileArguments(StringBounder stringBounder, LivingSpaces livingSpaces, Rose skin, ISkinParam skinParam,
			Real xorigin, Real yorigin) {
		this.stringBounder = stringBounder;
		this.xorigin = xorigin;
		this.yorigin = yorigin;
		this.livingSpaces = livingSpaces;
		this.skin = skin;
		this.skinParam = skinParam;
	}

	public TileArguments withBackColorGeneral(HColor backColorElement, HColor backColorGeneral) {
		return new TileArguments(stringBounder, livingSpaces, skin,
				new SkinParamBackcolored(skinParam, backColorElement, backColorGeneral), xorigin, yorigin);
	}

	public TileArguments withBackColor(Reference reference) {
		final ISkinParam newSkinParam = new SkinParamBackcoloredReference(skinParam, reference.getBackColorElement(),
				reference.getBackColorGeneral());
		return new TileArguments(stringBounder, livingSpaces, skin, newSkinParam, xorigin, yorigin);
	}

	public final StringBounder getStringBounder() {
		return stringBounder;
	}

	public final Real getXOrigin() {
		return xorigin;
	}

	public final Real getYOrigin() {
		return yorigin;
	}

	public final LivingSpaces getLivingSpaces() {
		return livingSpaces;
	}

	public final Rose getSkin() {
		return skin;
	}

	public final ISkinParam getSkinParam() {
		return skinParam;
	}

	public LivingSpace getLivingSpace(Participant p) {
		return livingSpaces.get(p);
	}

	public LivingSpace getFirstLivingSpace() {
		return livingSpaces.values().iterator().next();
	}

	public LivingSpace getLastLivingSpace() {
		LivingSpace result = null;
		for (LivingSpace v : livingSpaces.values())
			result = v;

		return result;
	}

	private Bordered bordered;

	public void setBordered(Bordered bordered) {
		this.bordered = bordered;
	}

	public double getBorder1() {
		return bordered.getBorder1();
	}

	public double getBorder2() {
		return bordered.getBorder2();
	}

}
