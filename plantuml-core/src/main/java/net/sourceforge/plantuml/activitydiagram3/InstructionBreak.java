package net.sourceforge.plantuml.activitydiagram3;

import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileBreak;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class InstructionBreak extends MonoSwimable implements Instruction {

	private final LinkRendering inlinkRendering;

	public InstructionBreak(Swimlane swimlane, LinkRendering inlinkRendering) {
		super(swimlane);
		this.inlinkRendering = Objects.requireNonNull(inlinkRendering);
	}

	@Override
	public Ftile createFtile(FtileFactory factory) {
		return new FtileBreak(factory.skinParam(), getSwimlaneIn());
	}


	@Override
	public CommandExecutionResult add(Instruction other) {
		throw new UnsupportedOperationException();
	}

	@Override
	final public boolean kill() {
		return false;
	}

	public LinkRendering getInLinkRendering() {
		return inlinkRendering;
	}

	public boolean containsBreak() {
		return true;
	}

}
