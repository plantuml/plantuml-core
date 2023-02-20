package net.sourceforge.plantuml.svek;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.atmp.PixelImage;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.eggs.QuoteUtils;
import net.sourceforge.plantuml.fun.IconLoader;
import net.sourceforge.plantuml.klimt.AffineTransformType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.GraphicPosition;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.GraphicStrings;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.klimt.shape.UImage;
import net.sourceforge.plantuml.text.BackSlash;
import net.sourceforge.plantuml.version.PSystemVersion;
import net.sourceforge.plantuml.version.Version;

public class GraphvizCrash extends AbstractTextBlock implements IEntityImage {

	private final TextBlock text1;
	private final String text;

	public GraphvizCrash(String text, boolean graphviz244onWindows, Throwable rootCause) {
		this.text = text;
		this.text1 = GraphicStrings.createBlackOnWhite(init(rootCause), IconLoader.getRandom(),
				GraphicPosition.BACKGROUND_CORNER_TOP_RIGHT);
	}

	public static List<String> anErrorHasOccured(Throwable exception, String text) {
		final List<String> strings = new ArrayList<>();
		if (exception == null)
			strings.add("An error has occured!");
		else
			strings.add("An error has occured : " + exception);

		final String quote = StringUtils.rot(QuoteUtils.getSomeQuote());
		strings.add("<i>" + quote);
		strings.add(" ");
		strings.add("Diagram size: " + lines(text) + " lines / " + text.length() + " characters.");
		strings.add(" ");
		return strings;
	}

	private static int lines(String text) {
		int result = 0;
		for (int i = 0; i < text.length(); i++)
			if (text.charAt(i) == BackSlash.CHAR_NEWLINE)
				result++;

		return result;
	}

	public static void checkOldVersionWarning(List<String> strings) {
		final long days = (System.currentTimeMillis() - Version.compileTime()) / 1000L / 3600 / 24;
		if (days >= 90) {
			strings.add(" ");
			strings.add("<b>This version of PlantUML is " + days + " days old, so you should");
			strings.add("<b>consider upgrading from https://plantuml.com/download");
		}
	}

	public static void pleaseGoTo(List<String> strings) {
		strings.add(" ");
		strings.add("Please go to https://plantuml.com/graphviz-dot to check your GraphViz version.");
		strings.add(" ");
	}

	public static void youShouldSendThisDiagram(List<String> strings) {
		strings.add("You should send this diagram and this image to <b>plantuml@gmail.com</b> or");
		strings.add("post to <b>https://plantuml.com/qa</b> to solve this issue.");
		strings.add("You can try to turn around this issue by simplifing your diagram.");
	}

	public static void thisMayBeCaused(final List<String> strings) {
		strings.add("This may be caused by :");
		strings.add(" - a bug in PlantUML");
		strings.add(" - a problem in GraphViz");
	}

	private List<String> init(Throwable rootCause) {
		final List<String> strings = anErrorHasOccured(null, text);
		strings.add("For some reason, dot/GraphViz has crashed.");
		strings.add("");
		strings.add("RootCause " + rootCause);
		if (rootCause != null)
			strings.addAll(CommandExecutionResult.getStackTrace(rootCause));

		strings.add("");
		strings.add("This has been generated with PlantUML (" + Version.versionString() + ").");
		checkOldVersionWarning(strings);
		strings.add(" ");
		addProperties(strings);
		strings.add(" ");
		pleaseGoTo(strings);
		youShouldSendThisDiagram(strings);

		return strings;
	}

	private List<String> getText2() {
		final List<String> strings = new ArrayList<>();
		strings.add(" ");
		strings.add("<b>It looks like you are running GraphViz 2.44 under Windows.");
		strings.add("If you have just installed GraphViz, you <i>may</i> have to execute");
		strings.add("the post-install command <b>dot -c</b> like in the following example:");
		return strings;
	}

	private List<String> getText3() {
		final List<String> strings = new ArrayList<>();
		strings.add(" ");
		strings.add("You may have to have <i>Administrator rights</i> to avoid the following error message:");
		return strings;
	}

	public static void addDecodeHint(final List<String> strings) {
		strings.add(" ");
		strings.add(" Diagram source: (Use http://zxing.org/w/decode.jspx to decode the qrcode)");
	}

	public static void addProperties(final List<String> strings) {
	}

	public boolean isHidden() {
		return false;
	}

	public HColor getBackcolor() {
		return HColors.WHITE;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return getMain().calculateDimension(stringBounder);
	}

	public void drawU(UGraphic ug) {
		getMain().drawU(ug);
	}

	private TextBlock getMain() {
		TextBlock result = text1;

		return result;
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

	public Margins getShield(StringBounder stringBounder) {
		return Margins.NONE;
	}

	public double getOverscanX(StringBounder stringBounder) {
		return 0;
	}

}
