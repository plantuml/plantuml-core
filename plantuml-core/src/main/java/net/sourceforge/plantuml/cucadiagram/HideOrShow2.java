package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.baraye.Entity;

public class HideOrShow2 {

	private final String what;
	private final boolean show;

	@Override
	public String toString() {
		return what + " (" + show + ")";
	}

	private boolean isApplyable(Entity leaf) {
		if (what.startsWith("$"))
			return isApplyableTag(leaf, what.substring(1));

		if (what.startsWith("<<") && what.endsWith(">>"))
			return isApplyableStereotype(leaf.getStereotype(), what.substring(2, what.length() - 2).trim());

		if (isAboutUnlinked())
			return isApplyableUnlinked(leaf);

		final String fullName = leaf.getName();
		// System.err.println("fullName=" + fullName);
		return match(fullName, what);
	}

	private boolean isApplyable(Stereotype stereotype) {
		if (what.startsWith("<<") && what.endsWith(">>"))
			return isApplyableStereotype(stereotype, what.substring(2, what.length() - 2).trim());
		return false;
	}

	public boolean isAboutUnlinked() {
		return what.equalsIgnoreCase("@unlinked");
	}

	private boolean isApplyableUnlinked(Entity leaf) {
		if (leaf.isAloneAndUnlinked())
			return true;

		return false;
	}

	private boolean isApplyableStereotype(Stereotype stereotype, String pattern) {
		if (stereotype == null)
			return false;

		for (String label : stereotype.getMultipleLabels())
			if (match(label, pattern))
				return true;

		return false;
	}

	private boolean isApplyableTag(Entity leaf, String pattern) {
		for (Stereotag tag : leaf.stereotags())
			if (match(tag.getName(), pattern))
				return true;

		return false;
	}

	private boolean match(String s, String pattern) {
		if (pattern.contains("*")) {
			// System.err.println("f1=" + pattern);
			// System.err.println("f2=" + Pattern.quote(pattern));
			// System.err.println("f3=" + Matcher.quoteReplacement(pattern));
			String reg = "^" + pattern.replace("*", ".*") + "$";
			return s.matches(reg);

		}
		return s.equals(pattern);
	}

	public HideOrShow2(String what, boolean show) {
		this.what = what;
		this.show = show;
	}

	public boolean apply(boolean hidden, Entity leaf) {
		if (isApplyable(leaf))
			return !show;

		return hidden;
	}

	public boolean apply(boolean hidden, Stereotype stereotype) {
		if (isApplyable(stereotype))
			return !show;

		return hidden;
	}

}
