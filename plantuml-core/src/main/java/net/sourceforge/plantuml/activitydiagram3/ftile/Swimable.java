package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Set;

public interface Swimable {

	public Set<Swimlane> getSwimlanes();

	public Swimlane getSwimlaneIn();

	public Swimlane getSwimlaneOut();

}
