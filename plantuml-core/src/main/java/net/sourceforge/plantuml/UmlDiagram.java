package net.sourceforge.plantuml;

import static net.atmp.ImageBuilder.plainImageBuilder;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import net.atmp.PixelImage;
import net.sourceforge.plantuml.api.ImageDataSimple;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.cucadiagram.DisplaySection;
import net.sourceforge.plantuml.cucadiagram.UnparsableGraphvizException;
import net.sourceforge.plantuml.fun.IconLoader;
import net.sourceforge.plantuml.klimt.AffineTransformType;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.geom.GraphicPosition;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.GraphicStrings;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.UImage;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SImageIO;
import net.sourceforge.plantuml.security.SecurityUtils;
import net.sourceforge.plantuml.skin.UmlDiagramType;
import net.sourceforge.plantuml.style.NoStyleAvailableException;
import net.sourceforge.plantuml.svek.EmptySvgException;
import net.sourceforge.plantuml.svek.GraphvizCrash;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.utils.Log;
import net.sourceforge.plantuml.version.Version;

public abstract class UmlDiagram extends TitledDiagram implements Diagram, Annotated, WithSprite {

	private boolean rotation;

	private int minwidth = Integer.MAX_VALUE;

//	public UmlDiagram(ThemeStyle style, UmlSource source, UmlDiagramType type) {
//		super(style, source, type);
//	}

	public UmlDiagram(UmlSource source, UmlDiagramType type, Map<String, String> orig) {
		super(source, type, orig);
	}

	final public int getMinwidth() {
		return minwidth;
	}

	final public void setMinwidth(int minwidth) {
		this.minwidth = minwidth;
	}

	final public boolean isRotation() {
		return rotation;
	}

	final public void setRotation(boolean rotation) {
		this.rotation = rotation;
	}

	public final DisplaySection getFooterOrHeaderTeoz(FontParam param) {
		if (param == FontParam.FOOTER)
			return getFooter();

		if (param == FontParam.HEADER)
			return getHeader();

		throw new IllegalArgumentException();
	}

	@Override
	final protected ImageData exportDiagramNow(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {

		fileFormatOption = fileFormatOption.withTikzFontDistortion(getSkinParam().getTikzFontDistortion());


		try {
			final ImageData imageData = exportDiagramInternal(os, index, fileFormatOption);
			this.lastInfo = new XDimension2D(imageData.getWidth(), imageData.getHeight());
			return imageData;
		} catch (NoStyleAvailableException e) {
			// Logme.error(e);
			exportDiagramError(os, e, fileFormatOption, null);
		} catch (UnparsableGraphvizException e) {
			Logme.error(e);
			exportDiagramError(os, e.getCause(), fileFormatOption, e.getGraphvizVersion());
		} catch (Throwable e) {
			// Logme.error(e);
			exportDiagramError(os, e, fileFormatOption, null);
		}
		return ImageDataSimple.error();
	}

	private void exportDiagramError(OutputStream os, Throwable exception, FileFormatOption fileFormat,
			String graphvizVersion) throws IOException {
		exportDiagramError(os, exception, fileFormat, seed(), getMetadata(), getFlashData(),
				getFailureText1(exception, graphvizVersion, getFlashData()));
	}

	public static void exportDiagramError(OutputStream os, Throwable exception, FileFormatOption fileFormat, long seed,
			String metadata, String flash, List<String> strings) throws IOException {


		strings.addAll(CommandExecutionResult.getStackTrace(exception));

		BufferedImage im2 = null;

		final BufferedImage im = im2;
		final TextBlockBackcolored graphicStrings = GraphicStrings.createBlackOnWhite(strings, IconLoader.getRandom(),
				GraphicPosition.BACKGROUND_CORNER_TOP_RIGHT);

		final UDrawable drawable = (im == null) ? graphicStrings : new UDrawable() {
			public void drawU(UGraphic ug) {
				graphicStrings.drawU(ug);
				final double height = graphicStrings.calculateDimension(ug.getStringBounder()).getHeight();
				ug = ug.apply(UTranslate.dy(height));
				ug.draw(new UImage(new PixelImage(im, AffineTransformType.TYPE_NEAREST_NEIGHBOR)).scale(3));
			}
		};

		plainImageBuilder(drawable, fileFormat).metadata(metadata).seed(seed).write(os);
	}


	public String getFlashData() {
		final UmlSource source = getSource();
		if (source == null)
			return "";

		return source.getPlainString();
	}

	static private List<String> getFailureText1(Throwable exception, String graphvizVersion, String textDiagram) {
		final List<String> strings = GraphvizCrash.anErrorHasOccured(exception, textDiagram);
		strings.add("PlantUML (" + Version.versionString() + ") cannot parse result from dot/GraphViz.");
		if (exception instanceof EmptySvgException)
			strings.add("Because dot/GraphViz returns an empty string.");

		GraphvizCrash.checkOldVersionWarning(strings);
		if (graphvizVersion != null) {
			strings.add(" ");
			strings.add("GraphViz version used : " + graphvizVersion);
		}
		GraphvizCrash.pleaseGoTo(strings);
		GraphvizCrash.addProperties(strings);
		strings.add(" ");
		GraphvizCrash.thisMayBeCaused(strings);
		strings.add(" ");
		GraphvizCrash.youShouldSendThisDiagram(strings);
		strings.add(" ");
		return strings;
	}

	public static List<String> getFailureText2(Throwable exception, String textDiagram) {
		final List<String> strings = GraphvizCrash.anErrorHasOccured(exception, textDiagram);
		strings.add("PlantUML (" + Version.versionString() + ") has crashed.");
		GraphvizCrash.checkOldVersionWarning(strings);
		strings.add(" ");
		GraphvizCrash.youShouldSendThisDiagram(strings);
		strings.add(" ");
		return strings;
	}


	private XDimension2D lastInfo;

	protected abstract ImageData exportDiagramInternal(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException;

	@Override
	public String getWarningOrError() {
		if (lastInfo == null)
			return null;

		final double actualWidth = lastInfo.getWidth();
		if (actualWidth == 0)
			return null;

		final String value = getSkinParam().getValue("widthwarning");
		if (value == null)
			return null;

		if (value.matches("\\d+") == false)
			return null;

		final int widthwarning = Integer.parseInt(value);
		if (actualWidth > widthwarning)
			return "The image is " + ((int) actualWidth) + " pixel width. (Warning limit is " + widthwarning + ")";

		return null;
	}

	public void setHideEmptyDescription(boolean hideEmptyDescription) {
	}

}
