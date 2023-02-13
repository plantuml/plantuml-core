package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.Collection;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.PositionedNote;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactoryDelegator;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.sequencediagram.NoteType;

public class FtileFactoryDelegatorAddNote extends FtileFactoryDelegator {

	public FtileFactoryDelegatorAddNote(FtileFactory factory) {
		super(factory);
	}

	@Override
	public Ftile addNote(Ftile ftile, Swimlane swimlane, Collection<PositionedNote> notes,
			VerticalAlignment verticalAlignment) {
		if (notes.size() == 0)
			throw new IllegalArgumentException();

		// if (notes.size() > 1)
		// throw new IllegalArgumentException();

		ISkinParam skinParam = skinParam();
		if (ftile == null) {
			final PositionedNote note = notes.iterator().next();
			if (note.getColors() != null)
				skinParam = note.getColors().mute(skinParam);

			return new FtileNoteAlone(skinParam.shadowing(null), note.getDisplay(), skinParam,
					note.getType() == NoteType.NOTE, swimlane);
		}
		return FtileWithNoteOpale.create(ftile, notes, skinParam, true, verticalAlignment);
	}
}
