package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public interface Connection extends UDrawable, UShape {

	public Ftile getFtile1();

	public Ftile getFtile2();

}
