package net.sourceforge.plantuml;

import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.Objects;

import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.text.SvgCharSizeHack;

/**
 * A FileFormat with some parameters.
 * 
 * 
 * @author Arnaud Roques
 * 
 */
public final class FileFormatOption implements Serializable {

	private final FileFormat fileFormat;
	private boolean withMetadata;
	private final boolean useRedForError;
	private final String svgLinkTarget;
	private final String hoverColor;
	private final TikzFontDistortion tikzFontDistortion;
	private final double scale;
	private final String preserveAspectRatio;
	private final String watermark;
	private final ColorMapper colorMapper;

	public double getScaleCoef() {
		return scale;
	}

	public FileFormatOption(FileFormat fileFormat) {
		this(fileFormat, true, false, null, false, null, TikzFontDistortion.getDefault(), 1.0, null, null,
				ColorMapper.IDENTITY);
	}

	public FileFormatOption(FileFormat fileFormat, boolean withMetadata) {
		this(fileFormat, withMetadata, false, null, false, null, TikzFontDistortion.getDefault(), 1.0, null, null,
				ColorMapper.IDENTITY);
	}

	private FileFormatOption(FileFormat fileFormat, boolean withMetadata, boolean useRedForError, String svgLinkTarget,
			boolean debugsvek, String hoverColor, TikzFontDistortion tikzFontDistortion, double scale,
			String preserveAspectRatio, String watermark, ColorMapper colorMapper) {
		this.hoverColor = hoverColor;
		this.watermark = watermark;
		this.fileFormat = fileFormat;
		this.withMetadata = withMetadata;
		this.useRedForError = useRedForError;
		this.svgLinkTarget = svgLinkTarget;
		this.debugsvek = debugsvek;
		this.tikzFontDistortion = Objects.requireNonNull(tikzFontDistortion);
		this.scale = scale;
		this.preserveAspectRatio = preserveAspectRatio;
		this.colorMapper = colorMapper;
	}

	public StringBounder getDefaultStringBounder(SvgCharSizeHack charSizeHack) {
		return fileFormat.getDefaultStringBounder(tikzFontDistortion, charSizeHack);
	}

	public String getSvgLinkTarget() {
		return svgLinkTarget;
	}

	public final boolean isWithMetadata() {
		return withMetadata;
	}

	public final String getPreserveAspectRatio() {
		return preserveAspectRatio;
	}

	public FileFormatOption withUseRedForError() {
		return new FileFormatOption(fileFormat, withMetadata, true, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	public FileFormatOption withTikzFontDistortion(TikzFontDistortion tikzFontDistortion) {
		return new FileFormatOption(fileFormat, withMetadata, true, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	public FileFormatOption withSvgLinkTarget(String svgLinkTarget) {
		return new FileFormatOption(fileFormat, withMetadata, useRedForError, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	public FileFormatOption withPreserveAspectRatio(String preserveAspectRatio) {
		return new FileFormatOption(fileFormat, withMetadata, useRedForError, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	public FileFormatOption withHoverColor(String hoverColor) {
		return new FileFormatOption(fileFormat, withMetadata, useRedForError, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	public FileFormatOption withScale(double scale) {
		return new FileFormatOption(fileFormat, withMetadata, useRedForError, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	public FileFormatOption withWartermark(String watermark) {
		return new FileFormatOption(fileFormat, withMetadata, useRedForError, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	public FileFormatOption withColorMapper(ColorMapper colorMapper) {
		return new FileFormatOption(fileFormat, withMetadata, useRedForError, svgLinkTarget, debugsvek, hoverColor,
				tikzFontDistortion, scale, preserveAspectRatio, watermark, colorMapper);
	}

	@Override
	public String toString() {
		return fileFormat.toString();
	}

	public final FileFormat getFileFormat() {
		return fileFormat;
	}

	@Deprecated
	public AffineTransform getAffineTransform() {
		return null;
	}

	public final boolean isUseRedForError() {
		return useRedForError;
	}

	private boolean debugsvek = false;

	public void setDebugSvek(boolean debugsvek) {
		this.debugsvek = debugsvek;
	}

	public boolean isDebugSvek() {
		return debugsvek;
	}

	public final String getHoverColor() {
		return hoverColor;
	}

	public void hideMetadata() {
		this.withMetadata = false;
	}

	public final TikzFontDistortion getTikzFontDistortion() {
		return tikzFontDistortion;
	}

	public final String getWatermark() {
		return watermark;
	}

	public ColorMapper getColorMapper() {
		return colorMapper;
	}

}
