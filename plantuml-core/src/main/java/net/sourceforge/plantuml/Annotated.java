package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.DisplayPositioned;
import net.sourceforge.plantuml.cucadiagram.DisplayPositionned;
import net.sourceforge.plantuml.cucadiagram.DisplaySection;
import net.sourceforge.plantuml.klimt.creole.Display;

public interface Annotated {

	public DisplayPositionned getTitle();

	public DisplayPositioned getCaption();

	public DisplayPositioned getLegend();

	public DisplaySection getHeader();

	public DisplaySection getFooter();

	public Display getMainFrame();

}
