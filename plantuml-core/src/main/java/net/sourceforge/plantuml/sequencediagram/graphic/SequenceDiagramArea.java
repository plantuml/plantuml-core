package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.png.PngTitler;
import net.sourceforge.plantuml.utils.MathUtils;

public class SequenceDiagramArea {

	private final double sequenceWidth;
	private final double sequenceHeight;

	private double headerWidth;
	private double headerHeight;
	private double headerMargin;

	private double titleWidth;
	private double titleHeight;

	private double captionWidth;
	private double captionHeight;

	private double footerWidth;
	private double footerHeight;
	private double footerMargin;

	private double legendWidth;
	private double legendHeight;
	private boolean isLegendTop;
	private HorizontalAlignment legendHorizontalAlignment;

	public void setLegend(XDimension2D dimLegend, boolean isLegendTop, HorizontalAlignment horizontalAlignment) {
		this.legendHorizontalAlignment = horizontalAlignment;
		this.legendWidth = dimLegend.getWidth();
		this.legendHeight = dimLegend.getHeight();
		this.isLegendTop = isLegendTop;
	}

	public double getLegendWidth() {
		return legendWidth;
	}

	public boolean hasLegend() {
		return legendHeight > 0 && legendWidth > 0;
	}

	public double getLegendX() {
		if (legendHorizontalAlignment == HorizontalAlignment.LEFT)
			return 0;
		else if (legendHorizontalAlignment == HorizontalAlignment.RIGHT)
			return Math.max(0, getWidth() - legendWidth);
		else
			return Math.max(0, getWidth() - legendWidth) / 2;

	}

	public SequenceDiagramArea(double width, double height) {
		this.sequenceWidth = width;
		this.sequenceHeight = height;
	}

	public void setTitleArea(double width, double height) {
		this.titleWidth = width;
		this.titleHeight = height;
	}

	private void setCaptionArea(double width, double height) {
		this.captionWidth = width;
		this.captionHeight = height;
	}

	public void setCaptionArea(XDimension2D dim) {
		setCaptionArea(dim.getWidth(), dim.getHeight());
	}

	public void setHeaderArea(double headerWidth, double headerHeight, double headerMargin) {
		this.headerWidth = headerWidth;
		this.headerHeight = headerHeight;
		this.headerMargin = headerMargin;
	}

	public void setFooterArea(double footerWidth, double footerHeight, double footerMargin) {
		this.footerWidth = footerWidth;
		this.footerHeight = footerHeight;
		this.footerMargin = footerMargin;
	}

	public double getWidth() {
		return MathUtils.max(sequenceWidth, headerWidth, titleWidth, footerWidth, captionWidth);
	}

	public double getHeight() {
		return sequenceHeight + headerHeight + headerMargin + titleHeight + footerMargin + footerHeight + captionHeight
				+ legendHeight;
	}

	public double getFooterY() {
		return sequenceHeight + headerHeight + headerMargin + titleHeight + footerMargin + captionHeight + legendHeight;
	}

	public double getCaptionY() {
		return sequenceHeight + headerHeight + headerMargin + titleHeight + legendHeight;
	}

	public double getLegendY() {
		if (isLegendTop)
			return titleHeight + headerHeight + headerMargin;

		return sequenceHeight + headerHeight + headerMargin + titleHeight;

	}

	public double getTitleX() {
		return (getWidth() - titleWidth) / 2;
	}

	public double getTitleY() {
		return headerHeight + headerMargin;
	}

	public double getHeaderHeightMargin() {
		return headerHeight + headerMargin;
	}

	public double getCaptionX() {
		return (getWidth() - captionWidth) / 2;
	}

	public double getSequenceAreaX() {
		return (getWidth() - sequenceWidth) / 2;
	}

	public double getSequenceAreaY() {
		if (isLegendTop)
			return getTitleY() + titleHeight + legendHeight;

		return getTitleY() + titleHeight;
	}

	public double getHeaderY() {
		return 0;
	}

	public double getFooterX(HorizontalAlignment align) {
		if (align == HorizontalAlignment.LEFT)
			return 0;

		if (align == HorizontalAlignment.RIGHT)
			return getWidth() - footerWidth;

		if (align == HorizontalAlignment.CENTER)
			return (getWidth() - footerWidth) / 2;

		throw new IllegalStateException();
	}

	public double getHeaderX(HorizontalAlignment align) {
		if (align == HorizontalAlignment.LEFT)
			return 0;

		if (align == HorizontalAlignment.RIGHT)
			return getWidth() - headerWidth;

		if (align == HorizontalAlignment.CENTER)
			return (getWidth() - headerWidth) / 2;

		throw new IllegalStateException();
	}

	public void initFooter(PngTitler pngTitler, StringBounder stringBounder) {
		final XDimension2D dim = pngTitler.getTextDimension(stringBounder);
		if (dim != null)
			setFooterArea(dim.getWidth(), dim.getHeight(), 0);

	}

	public void initHeader(PngTitler pngTitler, StringBounder stringBounder) {
		final XDimension2D dim = pngTitler.getTextDimension(stringBounder);
		if (dim != null)
			setHeaderArea(dim.getWidth(), dim.getHeight(), 0);

	}

}
