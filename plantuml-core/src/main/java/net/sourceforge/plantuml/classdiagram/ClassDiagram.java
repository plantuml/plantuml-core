package net.sourceforge.plantuml.classdiagram;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.UmlDiagramType;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.cucadiagram.Link;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.objectdiagram.AbstractClassOrObjectDiagram;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.svek.image.EntityImageClass;

public class ClassDiagram extends AbstractClassOrObjectDiagram {

	public ClassDiagram(UmlSource source, Map<String, String> skinParam) {
		super(source, UmlDiagramType.CLASS, skinParam);
	}

	private boolean allowMixing;

	public void setAllowMixing(boolean allowMixing) {
		this.allowMixing = allowMixing;
	}

	public boolean isAllowMixing() {
		return allowMixing;
	}

	private int useLayoutExplicit = 0;

	public void layoutNewLine() {
		useLayoutExplicit++;
		incRawLayout();
	}

	@Override
	final protected ImageData exportDiagramInternal(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {
		if (useLayoutExplicit != 0)
			return exportLayoutExplicit(os, index, fileFormatOption);

		return super.exportDiagramInternal(os, index, fileFormatOption);
	}

	final protected ImageData exportLayoutExplicit(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {
		final FullLayout fullLayout = new FullLayout();
		for (int i = 0; i <= useLayoutExplicit; i++) {
			final RowLayout rawLayout = getRawLayout(i);
			fullLayout.addRowLayout(rawLayout);
		}
		return createImageBuilder(fileFormatOption).annotations(false) // Backwards compatibility - this only applies
																		// when "layout_new_line" is used
				.drawable(fullLayout).write(os);
	}

	private RowLayout getRawLayout(int raw) {
		final RowLayout rawLayout = new RowLayout();
		for (Entity leaf : entityFactory.leafs())
			if (leaf.getRawLayout() == raw)
				rawLayout.addLeaf(getEntityImageClass(leaf));

		return rawLayout;
	}

	private TextBlock getEntityImageClass(Entity entity) {
		return new EntityImageClass(entity, getSkinParam(), this);
	}

	@Override
	public String checkFinalError() {
		for (Link link : this.getLinks()) {
			final int len = link.getLength();
			if (len == 1)
				for (Link link2 : this.getLinks())
					if (link2.sameConnections(link) && link2.getLength() != 1)
						link2.setLength(1);

		}
		this.applySingleStrategy();
		return super.checkFinalError();
	}

	public CommandExecutionResult checkIfPackageHierarchyIfOk(Entity entity) {
		Quark<Entity> current = entity.getQuark().getParent();
		while (current.isRoot() == false) {
			if (current.getData() != null && current.getData().isGroup() == false)
				return CommandExecutionResult.error("Bad hierarchy for class " + entity.getQuark().getQualifiedName());
			current = current.getParent();
		}
		return CommandExecutionResult.ok();
	}

}
