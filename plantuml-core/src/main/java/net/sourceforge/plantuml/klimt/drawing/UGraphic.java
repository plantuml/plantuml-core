package net.sourceforge.plantuml.klimt.drawing;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UGroupType;
import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.url.Url;

public interface UGraphic {

	public StringBounder getStringBounder();

	public UParam getParam();

	public <SHAPE extends UShape> void draw(SHAPE shape);

	public UGraphic apply(UChange change);

	public ColorMapper getColorMapper();

	public void startUrl(Url url);

	public void closeUrl();

	public void startGroup(Map<UGroupType, String> typeIdents);

	public void closeGroup();

	public void flushUg();

	public boolean matchesProperty(String propertyName);

	public HColor getDefaultBackground();

	public void writeToStream(OutputStream os, String metadata, int dpi) throws IOException;
}