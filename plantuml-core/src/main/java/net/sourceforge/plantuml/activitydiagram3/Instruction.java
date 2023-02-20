package net.sourceforge.plantuml.activitydiagram3;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimable;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;
import net.sourceforge.plantuml.style.ISkinParam;

public interface Instruction extends Swimable {

	public Ftile createFtile(FtileFactory factory);


	public CommandExecutionResult add(Instruction other);

	public boolean kill();

	public LinkRendering getInLinkRendering();

	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote);

	public boolean containsBreak();

}
