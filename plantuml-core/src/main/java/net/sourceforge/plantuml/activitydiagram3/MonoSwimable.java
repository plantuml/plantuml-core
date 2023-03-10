// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.activitydiagram3;

import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Swimable;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;

abstract class MonoSwimable extends WithNote implements Swimable {

	private final Swimlane swimlane;

	public MonoSwimable(Swimlane swimlane) {
		this.swimlane = swimlane;
	}

	final public Set<Swimlane> getSwimlanes() {
		if (swimlane == null)
			return Collections.emptySet();
		return Collections.<Swimlane>singleton(swimlane);
	}

	final public Swimlane getSwimlaneIn() {
		return swimlane;
	}

	final public Swimlane getSwimlaneOut() {
		return swimlane;
	}

}
