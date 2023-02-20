package net.sourceforge.plantuml.compositediagram;

import java.util.Map;

import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.skin.UmlDiagramType;

public class CompositeDiagram extends AbstractEntityDiagram {

	public CompositeDiagram(UmlSource source, Map<String, String> skinParam) {
		super(source, UmlDiagramType.COMPOSITE, skinParam);
	}

//	@Override
//	protected IEntity getOrCreateLeaf2(Quark ident, Quark code, LeafType type, USymbol symbol) {
//		Objects.requireNonNull(ident);
//		// final Ident idNewLong = buildLeafIdent(id);
//		if (type == null) {
//			if (isGroup(code.getName())) {
//				return getGroup(code.getName());
//			}
//			return reallyCreateLeaf(ident, Display.getWithNewlines(code.getName()), LeafType.BLOCK, symbol);
//		}
//		return reallyCreateLeaf(ident, Display.getWithNewlines(code.getName()), type, symbol);
//	}

}
