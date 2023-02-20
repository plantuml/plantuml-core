package net.sourceforge.plantuml.baraye;

import net.atmp.Link;

public abstract class EntityUtils {

	private static boolean isParent(Entity groupToBeTested, Entity parentGroup) {
		if (groupToBeTested.isGroup() == false)
			// Very strange!
			return false;

		if (groupToBeTested.isGroup() == false)
			throw new IllegalArgumentException();

		while (groupToBeTested.isRoot() == false) {
			if (groupToBeTested == parentGroup)
				return true;

			groupToBeTested = groupToBeTested.getParentContainer();
			if (groupToBeTested.isGroup() == false)
				return false;
			// throw new IllegalStateException();

		}
		return false;
	}

	public static boolean isPureInnerLink12(Entity group, Link link) {
		if (group.isGroup() == false)
			throw new IllegalArgumentException();

		final Entity e1 = link.getEntity1();
		final Entity e2 = link.getEntity2();
		final Entity group1 = e1.getParentContainer();
		final Entity group2 = e2.getParentContainer();

		if (isParent(group1, group) && isParent(group2, group))
			return true;

		return false;
	}

	public static boolean isPureInnerLink3(Entity group, Link link) {
		if (group.isGroup() == false)
			throw new IllegalArgumentException();

		final Entity e1 = link.getEntity1();
		final Entity e2 = link.getEntity2();
		final Entity group1 = e1.getParentContainer();
		final Entity group2 = e2.getParentContainer();
		if (isParent(group2, group) == isParent(group1, group))
			return true;

		return false;
	}
}
