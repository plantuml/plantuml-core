package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.text.Guillemet;

public class EntityGenderUtils {

	static public EntityGender byEntityType(final LeafType type) {
		return new EntityGender() {
			public boolean contains(Entity test) {
				return test.getLeafType() == type;
			}
		};
	}

	static public EntityGender byEntityAlone(final Entity entity) {
		return new EntityGender() {
			public boolean contains(Entity test) {
				return test.getUid().equals(entity.getUid());
			}
		};
	}

	static public EntityGender byStereotype(final String stereotype) {
		return new EntityGender() {
			public boolean contains(Entity test) {
				if (test.getStereotype() == null) {
					return false;
				}
				return stereotype.equals(test.getStereotype().getLabel(Guillemet.DOUBLE_COMPARATOR));
			}
		};
	}

	static public EntityGender byPackage(final Entity group) {
		if (group.isRoot()) {
			throw new IllegalArgumentException();
		}
		return new EntityGender() {
			public boolean contains(Entity test) {
				if (test.getParentContainer().isRoot()) {
					return false;
				}
				if (group == test.getParentContainer()) {
					return true;
				}
				return false;
			}
		};
	}

	static public EntityGender and(final EntityGender g1, final EntityGender g2) {
		return new EntityGender() {
			public boolean contains(Entity test) {
				return g1.contains(test) && g2.contains(test);
			}
		};
	}

	static public EntityGender all() {
		return new EntityGender() {
			public boolean contains(Entity test) {
				return true;
			}
		};
	}

	static public EntityGender emptyMethods() {
		return new EntityGender() {
			public boolean contains(Entity test) {
				return test.getBodier().getMethodsToDisplay().size() == 0;
			}
		};
	}

	static public EntityGender emptyFields() {
		return new EntityGender() {
			public boolean contains(Entity test) {
				return test.getBodier().getFieldsToDisplay().size() == 0;
			}
		};
	}

}
