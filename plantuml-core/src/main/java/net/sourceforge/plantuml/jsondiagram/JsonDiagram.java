package net.sourceforge.plantuml.jsondiagram;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.atmp.InnerStrategy;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.ScaleSimple;
import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.json.JsonArray;
import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.hand.UGraphicHandwritten;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.skin.UmlDiagramType;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.yaml.Highlighted;

public class JsonDiagram extends TitledDiagram {

	private final JsonValue root;
	private final List<Highlighted> highlighted;
	private final boolean handwritten;

	public JsonDiagram(UmlSource source, UmlDiagramType type, JsonValue json, List<Highlighted> highlighted,
			StyleExtractor styleExtractor) {
		super(source, type, null);
		this.handwritten = styleExtractor.isHandwritten();
		if (json != null && (json.isString() || json.isBoolean() || json.isNumber())) {
			this.root = new JsonArray();
			((JsonArray) this.root).add(json);
		} else {
			this.root = json;
		}
		this.highlighted = highlighted;
		setScale(new ScaleSimple(styleExtractor.getScale()));
	}

	public DiagramDescription getDescription() {
		if (getUmlDiagramType() == UmlDiagramType.YAML)
			return new DiagramDescription("(Yaml)");

		if (getUmlDiagramType() == UmlDiagramType.HCL)
			return new DiagramDescription("(HCL)");

		return new DiagramDescription("(Json)");
	}

	@Override
	protected ImageData exportDiagramNow(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {

		return createImageBuilder(fileFormatOption).drawable(getTextBlock()).write(os);
	}

	private void drawInternal(UGraphic ug) {
		if (handwritten)
			ug = new UGraphicHandwritten(ug);
		if (root == null) {
			final Display display = Display
					.getWithNewlines("Your data does not sound like " + getUmlDiagramType() + " data");
			final FontConfiguration fontConfiguration = FontConfiguration.blackBlueTrue(UFont.courier(14));
			TextBlock result = display.create(fontConfiguration, HorizontalAlignment.LEFT, getSkinParam());
			result = TextBlockUtils.withMargin(result, 5, 2);
			result.drawU(ug);
		} else {
			new SmetanaForJson(ug, getSkinParam()).drawMe(root, highlighted);
		}
	}

	@Override
	protected TextBlockBackcolored getTextBlock() {
		return new TextBlockBackcolored() {

			public void drawU(UGraphic ug) {
				drawInternal(ug);
			}

			public MinMax getMinMax(StringBounder stringBounder) {
				return null;
			}

			public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
				return null;
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				return TextBlockUtils.getMinMax(getTextBlock(), stringBounder, true).getDimension();
			}

			public HColor getBackcolor() {
				return null;
			}
		};
	}

}
