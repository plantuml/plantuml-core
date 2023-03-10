// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.project.timescale;

import net.sourceforge.plantuml.project.time.Day;

public interface TimeScale {

	public double getStartingPosition(Day instant);

	public double getEndingPosition(Day instant);

	public double getWidth(Day instant);

	public boolean isBreaking(Day instant);

}
