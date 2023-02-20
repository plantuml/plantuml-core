package net.sourceforge.plantuml.activitydiagram3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.decoration.Rainbow;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;
import net.sourceforge.plantuml.style.ISkinParam;

public class InstructionSplit extends AbstractInstruction implements Instruction {

	private final List<InstructionList> splits = new ArrayList<>();
	private final Instruction parent;
	private final LinkRendering inlinkRendering;
	private final Swimlane swimlaneIn;
	private Swimlane swimlaneOut;

	public InstructionSplit(Instruction parent, LinkRendering inlinkRendering, Swimlane swimlane) {
		this.parent = parent;
		this.swimlaneIn = swimlane;

		this.splits.add(new InstructionList(swimlane));
		this.inlinkRendering = Objects.requireNonNull(inlinkRendering);
	}

	@Override
	public boolean containsBreak() {
		for (InstructionList split : splits)
			if (split.containsBreak())
				return true;

		return false;
	}

	private InstructionList getLast() {
		return splits.get(splits.size() - 1);
	}

	@Override
	public CommandExecutionResult add(Instruction ins) {
		return getLast().add(ins);
	}


	private Rainbow getInLinkRenderingColor(ISkinParam skinParam) {
		Rainbow color;
		color = Rainbow.build(skinParam);
		return color;
	}

	@Override
	public Ftile createFtile(FtileFactory factory) {
		final List<Ftile> all = new ArrayList<>();
		for (InstructionList list : splits)
			all.add(list.createFtile(factory));

		return factory.createParallel(all, ForkStyle.SPLIT, null, swimlaneIn, swimlaneOut);
	}

	public Instruction getParent() {
		return parent;
	}

	public void splitAgain(LinkRendering inlinkRendering) {
		if (inlinkRendering != null)
			getLast().setOutRendering(inlinkRendering);

		final InstructionList list = new InstructionList(swimlaneIn);
		this.splits.add(list);
	}

	public void endSplit(LinkRendering inlinkRendering, Swimlane endSwimlane) {
		if (inlinkRendering != null)
			getLast().setOutRendering(inlinkRendering);

		this.swimlaneOut = endSwimlane;

	}

	@Override
	final public boolean kill() {
		return getLast().kill();
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return inlinkRendering;
	}

	@Override
	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		return getLast().addNote(note, position, type, colors, swimlaneNote);
	}

	@Override
	public Set<Swimlane> getSwimlanes() {
		return InstructionList.getSwimlanes2(splits);
	}

	@Override
	public Swimlane getSwimlaneIn() {
		return parent.getSwimlaneOut();
	}

	@Override
	public Swimlane getSwimlaneOut() {
		return swimlaneOut;
		// return getLast().getSwimlaneOut();
	}

}
