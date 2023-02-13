package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface ConnectionTranslatable extends Connection {

	public void drawTranslate(UGraphic ug, UTranslate translate1, UTranslate translate2);
	
}
