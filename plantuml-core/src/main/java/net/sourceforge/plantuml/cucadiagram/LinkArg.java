package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.skin.VisibilityModifier;

public class LinkArg {

	private final Display label;
	private final String quantifier1;
	private final String quantifier2;
	private final String labeldistance;
	private final String labelangle;

	private final String kal1;
	private final String kal2;

	private VisibilityModifier visibilityModifier;
	private int length;

	public static LinkArg build(final Display label, int length) {
		return build(label, length, true);
	}

	public static LinkArg noDisplay(int length) {
		return build(Display.NULL, length, true);
	}

	public static LinkArg build(final Display label, int length, boolean manageVisibilityModifier) {
		VisibilityModifier visibilityModifier = null;
		final Display newLabel;
		if (Display.isNull(label)) {
			newLabel = Display.NULL;
		} else {
			newLabel = label.manageGuillemet(manageVisibilityModifier);
			if (manageVisibilityModifier && VisibilityModifier.isVisibilityCharacter(label.get(0)))
				visibilityModifier = VisibilityModifier.getVisibilityModifier(label.get(0), false);
		}
		return new LinkArg(newLabel, length, null, null, null, null, visibilityModifier, null, null);
	}

	public LinkArg withQuantifier(String quantifier1, String quantifier2) {
		return new LinkArg(label, length, quantifier1, quantifier2, labeldistance, labelangle, visibilityModifier, kal1,
				kal2);
	}

	public LinkArg withKal(String kal1, String kal2) {
		return new LinkArg(label, length, quantifier1, quantifier2, labeldistance, labelangle, visibilityModifier, kal1,
				kal2);
	}

	public LinkArg withDistanceAngle(String labeldistance, String labelangle) {
		return new LinkArg(label, length, quantifier1, quantifier2, labeldistance, labelangle, visibilityModifier, kal1,
				kal2);
	}

	private LinkArg(Display label, int length, String quantifier1, String quantifier2, String labeldistance,
			String labelangle, VisibilityModifier visibilityModifier, String kal1, String kal2) {

		this.label = label;
		this.visibilityModifier = visibilityModifier;
		this.length = length;
		this.quantifier1 = quantifier1;
		this.quantifier2 = quantifier2;
		this.labeldistance = labeldistance;
		this.labelangle = labelangle;
		this.kal1 = kal1;
		this.kal2 = kal2;
	}

	public LinkArg getInv() {
		return new LinkArg(label, length, quantifier2, quantifier1, labeldistance, labelangle, visibilityModifier, kal1,
				kal2);
	}

	public final Display getLabel() {
		return label;
	}

	public final int getLength() {
		return length;
	}

	public final String getQuantifier1() {
		return quantifier1;
	}

	public final String getQuantifier2() {
		return quantifier2;
	}

	public final String getLabeldistance() {
		return labeldistance;
	}

	public final String getLabelangle() {
		return labelangle;
	}

	public final VisibilityModifier getVisibilityModifier() {
		return visibilityModifier;
	}

	public final void setVisibilityModifier(VisibilityModifier visibilityModifier) {
		this.visibilityModifier = visibilityModifier;
	}

	public final void setLength(int length) {
		this.length = length;
	}

	public final String getKal1() {
		return kal1;
	}

	public final String getKal2() {
		return kal2;
	}

}
