package net.sourceforge.plantuml.klimt.drawing;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import net.sourceforge.plantuml.klimt.UGroupType;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.url.Url;

public abstract class UGraphicDelegator implements UGraphic {

	final private UGraphic ug;

	@Override
	public String toString() {
		return super.toString() + " " + getUg().toString();
	}

	public final boolean matchesProperty(String propertyName) {
		return ug.matchesProperty(propertyName);
	}

	public UGraphicDelegator(UGraphic ug) {
		this.ug = ug;
	}

	public StringBounder getStringBounder() {
		return ug.getStringBounder();
	}

	public UParam getParam() {
		return ug.getParam();
	}

	public void draw(UShape shape) {
		ug.draw(shape);
	}

	public ColorMapper getColorMapper() {
		return ug.getColorMapper();
	}

	@Override
	public void startUrl(Url url) {
		ug.startUrl(url);
	}

	@Override
	public void closeUrl() {
		ug.closeUrl();
	}

	public void startGroup(Map<UGroupType, String> typeIdents) {
		ug.startGroup(typeIdents);
	}

	public void closeGroup() {
		ug.closeGroup();
	}

	protected UGraphic getUg() {
		return ug;
	}

	public void flushUg() {
		ug.flushUg();
	}

	@Override
	public HColor getDefaultBackground() {
		return ug.getDefaultBackground();
	}

	@Override
	public void writeToStream(OutputStream os, String metadata, int dpi) throws IOException {
		ug.writeToStream(os, metadata, dpi);
	}
}