package net.sourceforge.plantuml.activitydiagram3;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileLabel;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.style.ISkinParam;

public class InstructionLabel extends MonoSwimable implements Instruction {

	private final String name;

	public InstructionLabel(Swimlane swimlane, String name) {
		super(swimlane);
		this.name = name;
	}

	@Override
	public Ftile createFtile(FtileFactory factory) {
		return new FtileLabel(factory.skinParam(), getSwimlaneIn(), name);
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
		return LinkRendering.none();
	}

	@Override
	public boolean containsBreak() {
		return false;
	}

}
