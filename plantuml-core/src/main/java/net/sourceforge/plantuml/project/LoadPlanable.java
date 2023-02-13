package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.project.time.Day;

public interface LoadPlanable {

	public int getLoadAt(Day instant);
}
