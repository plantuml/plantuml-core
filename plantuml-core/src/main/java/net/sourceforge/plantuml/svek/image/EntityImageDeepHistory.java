package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.style.SName;

public class EntityImageDeepHistory extends EntityImagePseudoState {

	public EntityImageDeepHistory(Entity entity, ISkinParam skinParam, SName sname) {
		super(entity, skinParam, "H*", sname);

	}
}
