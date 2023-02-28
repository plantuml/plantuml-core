package net.sourceforge.plantuml.stereo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.sprite.Sprite;
import net.sourceforge.plantuml.klimt.sprite.SpriteContainer;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.svek.PackageStyle;
import net.sourceforge.plantuml.text.Guillemet;

public class Stereotype implements CharSequence {

	private final double radius;
	private final UFont circledFont;
	private final boolean automaticPackageStyle;
	private final StereotypeDecoration decoration;

	private Stereotype(boolean automaticPackageStyle, String label, StereotypeDecoration decoration, double radius,
			UFont circledFont) {
		this.automaticPackageStyle = automaticPackageStyle;
		this.radius = radius;
		this.circledFont = circledFont;
		this.decoration = Objects.requireNonNull(decoration);

	}

	private static void checkLabel(String label) {
		if (label.startsWith("<<") == false || label.endsWith(">>") == false)
			throw new IllegalArgumentException(label);

	}

	public static Stereotype build(String label) {
		return build(label, true);
	}

	public static Stereotype build(String label, boolean automaticPackageStyle) {
		checkLabel(label);
		final StereotypeDecoration decoration = StereotypeDecoration.buildSimple(label);
		return new Stereotype(automaticPackageStyle, label, decoration, 0, null);
	}

	public static Stereotype build(String label, double radius, UFont circledFont, HColorSet htmlColorSet)
			throws NoSuchColorException {
		checkLabel(label);
		final StereotypeDecoration decoration = StereotypeDecoration.buildComplex(label, htmlColorSet);
		return new Stereotype(true, label, decoration, radius, circledFont);
	}

	public HColor getHtmlColor() {
		return decoration.htmlColor;
	}

	public char getCharacter() {
		return decoration.character;
	}

	public final TextBlock getSprite(SpriteContainer container) {
		if (decoration.spriteName == null || container == null)
			return null;

		final Sprite tmp = container.getSprite(decoration.spriteName);
		if (tmp == null)
			return null;

		return tmp.asTextBlock(getHtmlColor(), decoration.spriteScale);
	}

	public boolean isWithOOSymbol() {
		return "<<O-O>>".equalsIgnoreCase(decoration.label);
	}

	public List<String> getMultipleLabels() {
		final List<String> result = new ArrayList<>();

		final Pattern p = Pattern.compile("\\<\\<\\s?((?:\\<&\\w+\\>|[^<>])+?)\\s?\\>\\>");
		final Matcher m = p.matcher(decoration.label);
		while (m.find())
			result.add(m.group(1));

		return Collections.unmodifiableList(result);
	}

	public boolean isSpotted() {
		return decoration.character != 0;
	}

	@Override
	public String toString() {
		if (decoration.character == 0)
			return decoration.label;

		return decoration.character + " " + decoration.label;
	}

	public char charAt(int arg0) {
		return toString().charAt(arg0);
	}

	public int length() {
		return toString().length();
	}

	public CharSequence subSequence(int arg0, int arg1) {
		return toString().subSequence(arg0, arg1);
	}

	public double getRadius() {
		return radius;
	}

	public final UFont getCircledFont() {
		return circledFont;
	}

	public String getLabel(Guillemet guillemet) {
		if (isWithOOSymbol())
			return null;

		if (decoration.spriteName != null && decoration.spriteName.startsWith("archimate/"))
			return guillemet.manageGuillemet("<<" + decoration.spriteName.substring("archimate/".length()) + ">>");

		return guillemet.manageGuillemet(decoration.label);
	}

	public List<String> getLabels(Guillemet guillemet) {
		final String labelLocal = getLabel(Guillemet.DOUBLE_COMPARATOR);
		if (labelLocal == null)
			return Collections.emptyList();

		return StereotypeDecoration.cutLabels(labelLocal, guillemet);
	}

	public List<Style> getStyles(StyleBuilder builder) {
		final List<Style> result = new ArrayList<>();
		for (String s : getStyleNames()) {
			final Style style = builder.createStyle(s);
			assert (style != null);
			result.add(style);
		}
		return Collections.unmodifiableList(result);
	}

	public List<String> getStyleNames() {
		return decoration.getStyleNames();
	}

	public PackageStyle getPackageStyle() {
		if (automaticPackageStyle == false)
			return null;

		for (PackageStyle p : EnumSet.allOf(PackageStyle.class))
			if (("<<" + p + ">>").equalsIgnoreCase(decoration.label))
				return p;

		return null;
	}

	public boolean isBiddableOrUncertain() {
		return decoration.label.equalsIgnoreCase("<<B>>") || decoration.label.equalsIgnoreCase("<<Biddable>>")
				|| decoration.label.equalsIgnoreCase("<<Uncertain>>");
	}

	public boolean isCausal() {
		return decoration.label.equalsIgnoreCase("<<C>>") || decoration.label.equalsIgnoreCase("<<Causal>>");
	}

	public boolean isLexicalOrGiven() {
		return decoration.label.equalsIgnoreCase("<<L>>") || decoration.label.equalsIgnoreCase("<<Lexical>>")
				|| decoration.label.equalsIgnoreCase("<<X>>") || decoration.label.equalsIgnoreCase("<<Given>>");
	}

	public boolean isDesignedOrSolved() {
		return decoration.label.equalsIgnoreCase("<<D>>") || decoration.label.equalsIgnoreCase("<<Designed>>")
				|| decoration.label.equalsIgnoreCase("<<Nested>>") || decoration.label.equalsIgnoreCase("<<Solved>>");
	}

	public boolean isMachineOrSpecification() {
		return decoration.label.equalsIgnoreCase("M") || decoration.label.equalsIgnoreCase("<<Machine>>")
				|| decoration.label.equalsIgnoreCase("<<S>>") || decoration.label.equalsIgnoreCase("<<Spec>>")
				|| decoration.label.equalsIgnoreCase("<<Specification>>");
	}

}