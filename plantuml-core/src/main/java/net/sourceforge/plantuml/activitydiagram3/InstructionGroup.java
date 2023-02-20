package net.sourceforge.plantuml.activitydiagram3;

import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.FtileWithNotes;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.decoration.symbol.USymbol;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;

public class InstructionGroup extends AbstractInstruction implements Instruction, InstructionCollection {

	private final InstructionList list;
	private final Instruction parent;
	private final HColor backColor;
	private final LinkRendering linkRendering;
	private final USymbol type;

	private final Display title;
	private PositionedNote note = null;
	private final Style style;

	@Override
	public boolean containsBreak() {
		return list.containsBreak();
	}

	public InstructionGroup(Instruction parent, Display title, HColor backColor, Swimlane swimlane,
			LinkRendering linkRendering, USymbol type, Style style) {
		this.list = new InstructionList(swimlane);
		this.type = type;
		this.linkRendering = linkRendering;
		this.parent = parent;
		this.title = title;
		this.style = style;
		this.backColor = backColor;
	}

	@Override
	public CommandExecutionResult add(Instruction ins) {
		return list.add(ins);
	}


	@Override
	public Ftile createFtile(FtileFactory factory) {
		Ftile tmp = list.createFtile(factory);
		if (note != null)
			tmp = new FtileWithNotes(tmp, Collections.singleton(note), factory.skinParam(), VerticalAlignment.CENTER);

		return factory.createGroup(tmp, title, backColor, null, type, style);
	}

	public Instruction getParent() {
		return parent;
	}

	@Override
	final public boolean kill() {
		return list.kill();
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return linkRendering;
	}

	@Override
	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		if (list.isEmpty()) {
			this.note = new PositionedNote(note, position, type, swimlaneNote, colors);
			return true;
		}
		return list.addNote(note, position, type, colors, swimlaneNote);
	}

	@Override
	public Set<Swimlane> getSwimlanes() {
		return list.getSwimlanes();
	}

	@Override
	public Swimlane getSwimlaneIn() {
		return list.getSwimlaneIn();
	}

	@Override
	public Swimlane getSwimlaneOut() {
		return list.getSwimlaneOut();
	}

	@Override
	public Instruction getLast() {
		return list.getLast();
	}

}
