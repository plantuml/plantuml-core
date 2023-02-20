package net.sourceforge.plantuml.activitydiagram3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;
import net.sourceforge.plantuml.style.ISkinParam;

public class InstructionSwitch extends WithNote implements Instruction, InstructionCollection {

	private final List<Branch> switches = new ArrayList<>();
	private final ISkinParam skinParam;

	private final Instruction parent;

	private Branch current;
	private final LinkRendering topInlinkRendering;
	private LinkRendering afterEndwhile = LinkRendering.none();
	private final Display labelTest;

	private final Swimlane swimlane;

	@Override
	public boolean containsBreak() {
		for (Branch branch : switches)
			if (branch.containsBreak())
				return true;

		return false;
	}

	public InstructionSwitch(Swimlane swimlane, Instruction parent, Display labelTest, LinkRendering inlinkRendering,
			HColor color, ISkinParam skinParam) {
		this.topInlinkRendering = Objects.requireNonNull(inlinkRendering);
		this.parent = parent;
		this.skinParam = skinParam;
		this.labelTest = labelTest;
		this.swimlane = swimlane;
	}

	@Override
	public CommandExecutionResult add(Instruction ins) {
		if (current == null)
			return CommandExecutionResult.error("No 'case' in this switch");

		return current.add(ins);
	}


	public Ftile createFtile(FtileFactory factory) {
		for (Branch branch : switches)
			branch.updateFtile(factory);

		Ftile result = factory.createSwitch(swimlane, switches, afterEndwhile, topInlinkRendering, labelTest);
		result = eventuallyAddNote(factory, result, getSwimlaneIn(), VerticalAlignment.TOP);
		return result;
	}

	@Override
	final public boolean kill() {
		return current.kill();
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return topInlinkRendering;
	}

	@Override
	public Set<Swimlane> getSwimlanes() {
		final Set<Swimlane> result = new HashSet<>();
		if (swimlane != null)
			result.add(swimlane);

		for (Branch branch : switches)
			result.addAll(branch.getSwimlanes());

		return Collections.unmodifiableSet(result);
	}

	@Override
	public Swimlane getSwimlaneIn() {
		return swimlane;
	}

	@Override
	public Swimlane getSwimlaneOut() {
		return swimlane;
	}

	@Override
	public Instruction getLast() {
		return switches.get(switches.size() - 1).getLast();
	}

	public boolean switchCase(Display labelCase, LinkRendering nextLinkRenderer) {
		if (this.current != null)
			this.current.setSpecial(nextLinkRenderer);
		this.current = new Branch(skinParam.getCurrentStyleBuilder(), swimlane,
				LinkRendering.none().withDisplay(labelCase), labelCase, null,
				LinkRendering.none().withDisplay(labelCase));
		this.switches.add(this.current);
		return true;
	}

	public Instruction getParent() {
		return parent;
	}

	public void endSwitch(LinkRendering nextLinkRenderer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		if (current == null || current.isEmpty())
			return super.addNote(note, position, type, colors, swimlaneNote);
		else
			return current.addNote(note, position, type, colors, swimlaneNote);

	}

}
