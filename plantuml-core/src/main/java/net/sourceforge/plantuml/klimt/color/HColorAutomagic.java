package net.sourceforge.plantuml.klimt.color;

class HColorAutomagic extends HColor {

	@Override
	public HColor getAppropriateColor(HColor back) {
		return back.opposite();
	}

}
