package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.Positionable;

public interface Clusterable extends Positionable {

	public Cluster getParent();

}
