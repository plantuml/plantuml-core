package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public enum NoteStyle {

	NORMAL, HEXAGONAL, BOX;

	public static NoteStyle getNoteStyle(String s) {
		if (s.equalsIgnoreCase("hnote")) {
			return NoteStyle.HEXAGONAL;
		} else if (s.equalsIgnoreCase("rnote")) {
			return NoteStyle.BOX;
		}
		return NoteStyle.NORMAL;
	}

	public ComponentType getNoteComponentType() {
		if (this == NoteStyle.HEXAGONAL) {
			return ComponentType.NOTE_HEXAGONAL;
		}
		if (this == NoteStyle.BOX) {
			return ComponentType.NOTE_BOX;
		}
		return ComponentType.NOTE;
	}

	public StyleSignatureBasic getDefaultStyleDefinition() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.note);
	}

}
