package net.sourceforge.plantuml.cucadiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.style.Style;

public class BodierSimple implements Bodier {

	private final List<CharSequence> rawBody = new ArrayList<>();
	private Entity leaf;

	@Override
	public void muteClassToObject() {
		throw new UnsupportedOperationException();
	}

	BodierSimple() {
	}

	@Override
	public void setLeaf(Entity leaf) {
		this.leaf = Objects.requireNonNull(leaf);
	}

	@Override
	public boolean addFieldOrMethod(String s) throws NoSuchColorException {
		final Display display = Display.getWithNewlines2(s);
		rawBody.addAll(display.asList());
		return true;
	}

	@Override
	public Display getMethodsToDisplay() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Display getFieldsToDisplay() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasUrl() {
		return false;
	}

	@Override
	public List<CharSequence> getRawBody() {
		return Collections.unmodifiableList(rawBody);
	}

	@Override
	public TextBlock getBody(ISkinParam skinParam, boolean showMethods, boolean showFields, Stereotype stereotype,
			Style style, FontConfiguration fontConfiguration) {
		return BodyFactory.create1(skinParam.getDefaultTextAlignment(HorizontalAlignment.LEFT), rawBody, skinParam,
				stereotype, leaf, style);
	}

}
