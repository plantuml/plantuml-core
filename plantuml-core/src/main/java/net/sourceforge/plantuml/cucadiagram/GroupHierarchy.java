package net.sourceforge.plantuml.cucadiagram;

import java.util.Collection;

import net.sourceforge.plantuml.baraye.Entity;

public interface GroupHierarchy {

	public Entity getRootGroup();

	public Collection<Entity> getChildrenGroups(Entity parent);

	public boolean isEmpty(Entity g);
}
