package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.style.ISkinParam;

public class FtileLabel extends FtileEmpty {

	private final String name;

	public FtileLabel(ISkinParam skinParam, Swimlane swimlane, String name) {
		super(skinParam, swimlane);
		this.name = name;
	}

	public final String getName() {
		return name;
	}

}
