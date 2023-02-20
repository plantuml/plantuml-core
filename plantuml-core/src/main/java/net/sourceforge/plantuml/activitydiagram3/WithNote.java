package net.sourceforge.plantuml.activitydiagram3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;

abstract class WithNote extends AbstractInstruction {

	private final Collection<PositionedNote> notes = new ArrayList<>();

	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		this.notes.add(new PositionedNote(note, position, type, swimlaneNote, colors));
		return true;
	}

	final protected Ftile eventuallyAddNote(FtileFactory factory, Ftile ftile, Swimlane swimlane,
			VerticalAlignment verticalAlignment) {
		if (notes.size() == 0)
			return ftile;

		return factory.addNote(ftile, swimlane, notes, verticalAlignment);
	}

	public Collection<PositionedNote> getPositionedNotes() {
		return Collections.unmodifiableCollection(notes);
	}

	public boolean hasNotes() {
		return notes.size() > 0;
	}

}
