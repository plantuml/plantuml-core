package net.sourceforge.plantuml.activitydiagram3;

import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;

public class PositionedNote {

	private final Display display;
	private final NotePosition notePosition;
	private final NoteType type;
	private final Colors colors;
	private final Swimlane swimlaneNote;

	public PositionedNote(Display display, NotePosition position, NoteType type, Swimlane swimlaneNote, Colors colors) {
		this.display = display;
		this.notePosition = position;
		this.type = type;
		this.colors = colors;
		this.swimlaneNote = swimlaneNote;
	}

	
	public PositionedNote(Display note, NotePosition position, NoteType type, Swimlane swimlaneNote) {
		this(note, position, type, swimlaneNote, null);
	}

	@Override
	public String toString() {
		return "type=" + type + " notePosition=" + notePosition + " " + display;
	}

	public Display getDisplay() {
		return display;
	}

	public NotePosition getNotePosition() {
		return notePosition;
	}

	public NoteType getType() {
		return type;
	}

	public Colors getColors() {
		return colors;
	}

	public final Swimlane getSwimlaneNote() {
		return swimlaneNote;
	}

}
