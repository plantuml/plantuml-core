package net.sourceforge.plantuml.cucadiagram;

import java.util.List;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;

public interface Bodier {

	public void setLeaf(Entity leaf);

	public Display getFieldsToDisplay();

	public Display getMethodsToDisplay();

	public boolean addFieldOrMethod(String s) throws NoSuchColorException;

	public TextBlock getBody(ISkinParam skinParam, boolean showMethods, boolean showFields, Stereotype stereotype,
			Style style, FontConfiguration fontConfiguration);

	public List<CharSequence> getRawBody();

	public void muteClassToObject();

	public boolean hasUrl();
}
