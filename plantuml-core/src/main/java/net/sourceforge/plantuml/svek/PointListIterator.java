package net.sourceforge.plantuml.svek;

import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.klimt.geom.XPoint2D;

public interface PointListIterator extends Iterator<List<XPoint2D>> {

	public PointListIterator cloneMe();

}
