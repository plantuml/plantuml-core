package net.sourceforge.plantuml.cucadiagram;

import java.util.List;
import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.style.Style;

public class BodierJSon implements Bodier {

	private Entity leaf;
	private JsonValue json;

	@Override
	public void muteClassToObject() {
		throw new UnsupportedOperationException();
	}

	public BodierJSon() {
	}

	@Override
	public void setLeaf(Entity leaf) {
		this.leaf = Objects.requireNonNull(leaf);

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
	public TextBlock getBody(ISkinParam skinParam, final boolean showMethods, final boolean showFields,
			Stereotype stereotype, Style style, FontConfiguration fontConfiguration) {
		return new TextBlockCucaJSon(fontConfiguration, skinParam, json, style.wrapWidth());
	}

	@Override
	public List<CharSequence> getRawBody() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addFieldOrMethod(String s) throws NoSuchColorException {
		throw new UnsupportedOperationException();
	}

	public void setJson(JsonValue json) {
		this.json = json;
	}

}
