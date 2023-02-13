package net.sourceforge.plantuml.salt.factory;

import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;

public interface ElementFactory {

	Terminated<Element> create();
	
	boolean ready();

}
