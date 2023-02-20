package net.sourceforge.plantuml.activitydiagram3;

import java.util.Objects;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.BoxStyle;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileKilled;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.FtileWithNoteOpale;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.decoration.Rainbow;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;
import net.sourceforge.plantuml.style.ISkinParam;

public class InstructionWhile extends WithNote implements Instruction, InstructionCollection {

	private final InstructionList repeatList = InstructionList.empty();
	private final Instruction parent;
	private final LinkRendering nextLinkRenderer;
	private final HColor color;
	private boolean killed = false;

	private final Display test;
	private Display yes;

	private boolean testCalled = false;

	private LinkRendering outColor = LinkRendering.none();
	private final Swimlane swimlane;
	private final ISkinParam skinParam;

	private Instruction specialOut;

	private BoxStyle boxStyle;
	private Swimlane swimlaneOut;
	private Display backward = Display.NULL;
	private LinkRendering incoming1 = LinkRendering.none();
	private LinkRendering incoming2 = LinkRendering.none();
	private boolean backwardCalled;

	public void overwriteYes(Display yes) {
		this.yes = yes;
	}

	public InstructionWhile(Swimlane swimlane, Instruction parent, Display test, LinkRendering nextLinkRenderer,
			Display yes, HColor color, ISkinParam skinParam) {
		this.parent = parent;
		this.test = Objects.requireNonNull(test);
		this.nextLinkRenderer = Objects.requireNonNull(nextLinkRenderer);
		this.yes = Objects.requireNonNull(yes);
		this.swimlane = swimlane;
		this.color = color;
		this.skinParam = skinParam;
	}

	@Override
	public CommandExecutionResult add(Instruction ins) {
		return repeatList.add(ins);
	}


	@Override
	public Ftile createFtile(FtileFactory factory) {
		final Ftile back = Display.isNull(backward) ? null
				: factory.activity(backward, swimlane, boxStyle, Colors.empty(), null);
		Ftile tmp = repeatList.createFtile(factory);
		tmp = factory.createWhile(outColor, swimlane, tmp, test, yes, color, specialOut, back, incoming1, incoming2);
		if (getPositionedNotes().size() > 0)
			tmp = FtileWithNoteOpale.create(tmp, getPositionedNotes(), skinParam, false, VerticalAlignment.CENTER);

		if (killed || specialOut != null)
			return new FtileKilled(tmp);

		return tmp;
	}

	public Instruction getParent() {
		return parent;
	}

	@Override
	final public boolean kill() {
		if (testCalled) {
			this.killed = true;
			return true;
		}
		return repeatList.kill();
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return nextLinkRenderer;
	}

	public void outDisplay(Display out) {
		this.outColor = outColor.withDisplay(Objects.requireNonNull(out));
	}

	public void outColor(Rainbow rainbow) {
		this.outColor = outColor.withRainbow(rainbow);
	}

	@Override
	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		if (repeatList.isEmpty())
			return super.addNote(note, position, type, colors, swimlaneNote);
		else
			return repeatList.addNote(note, position, type, colors, swimlaneNote);
	}

	@Override
	public Set<Swimlane> getSwimlanes() {
		return repeatList.getSwimlanes();
	}

	@Override
	public Swimlane getSwimlaneIn() {
		return parent.getSwimlaneIn();
	}

	@Override
	public Swimlane getSwimlaneOut() {
		return parent.getSwimlaneOut();
	}

	@Override
	public Instruction getLast() {
		return repeatList.getLast();
	}

	public void setSpecial(Instruction special) {
		this.specialOut = special;
	}

	@Override
	public boolean containsBreak() {
		return repeatList.containsBreak();
	}

	public void setBackward(Display label, Swimlane swimlaneOut, BoxStyle boxStyle, LinkRendering incoming1,
			LinkRendering incoming2) {
		this.backward = label;
		this.swimlaneOut = swimlaneOut;
		this.boxStyle = boxStyle;
		this.incoming1 = incoming1;
		this.incoming2 = incoming2;
		this.backwardCalled = true;
	}

	public void incoming(LinkRendering incoming) {
		if (backwardCalled == false) {
			this.incoming1 = incoming;
			this.incoming2 = incoming;
		}
		this.testCalled = true;
	}

}
