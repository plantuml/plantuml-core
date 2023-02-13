package net.sourceforge.plantuml.gitlog;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.UmlDiagramType;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.graphic.InnerStrategy;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class GitDiagram extends UmlDiagram {

	private final Collection<GNode> gnodes;

	public GitDiagram(UmlSource source, GitTextArea textArea) {
		super(source, UmlDiagramType.GIT, null);
		this.gnodes = new GNodeBuilder(textArea.getAllCommits()).getAllNodes();
		new GNodeBuilder(textArea.getAllCommits());
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(Git)");
	}

	@Override
	protected ImageData exportDiagramInternal(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {

		return createImageBuilder(fileFormatOption).drawable(getTextBlock()).write(os);
	}

	private void drawInternal(UGraphic ug) {

		new SmetanaForGit(ug, getSkinParam()).drawMe(gnodes);

//		final Display display = Display.getWithNewlines("Your data does not sound like GIT data");
//		final FontConfiguration fontConfiguration = FontConfiguration.blackBlueTrue(UFont.courier(14));
//		TextBlock result = display.create(fontConfiguration, HorizontalAlignment.LEFT, getSkinParam());
//		result = TextBlockUtils.withMargin(result, 5, 2);
//		result.drawU(ug);

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
				return null;
			}

			public HColor getBackcolor() {
				return null;
			}
		};
	}

}
