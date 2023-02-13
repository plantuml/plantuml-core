package net.sourceforge.plantuml.cucadiagram.entity;

import java.util.Collection;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.Stereotype;

public interface IEntityFactory {

	public boolean isRemoved(Stereotype stereotype);

	public Collection<Entity> leafs();

}
