package net.sourceforge.plantuml.gitlog;

import java.util.Iterator;
import java.util.Map;

import net.sourceforge.plantuml.command.PSystemAbstractFactory;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.text.StringLocated;

public class GitDiagramFactory extends PSystemAbstractFactory {

	public GitDiagramFactory() {
		super(DiagramType.GIT);
	}

	@Override
	public Diagram createSystem(UmlSource source, Map<String, String> skinParam) {
		final GitTextArea textArea = new GitTextArea();

		final Iterator<StringLocated> it = source.iterator2();
		it.next();
		while (true) {
			final String line = it.next().getString();
			if (it.hasNext() == false)
				break;

			textArea.add(line);
		}
		return new GitDiagram(source, textArea);
	}

}
