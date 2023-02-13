package net.sourceforge.plantuml.activitydiagram3;

import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class InstructionEnd extends MonoSwimable implements Instruction {

	private final LinkRendering inlinkRendering;

	public InstructionEnd(Swimlane swimlane, LinkRendering inlinkRendering) {
		super(swimlane);
		this.inlinkRendering = Objects.requireNonNull(inlinkRendering);
	}


	@Override
	public Ftile createFtile(FtileFactory factory) {
		Ftile result = factory.end(getSwimlaneIn());
		result = eventuallyAddNote(factory, result, result.getSwimlaneIn(), VerticalAlignment.CENTER);
		return result;
	}

	@Override
	public CommandExecutionResult add(Instruction other) {
		throw new UnsupportedOperationException();
	}

	@Override
	final public boolean kill() {
		return false;
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return inlinkRendering;
	}

	@Override
	public boolean containsBreak() {
		return false;
	}

}
