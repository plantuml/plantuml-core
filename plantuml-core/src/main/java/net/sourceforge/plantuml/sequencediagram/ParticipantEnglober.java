package net.sourceforge.plantuml.sequencediagram;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.klimt.color.HColor;

public class ParticipantEnglober {

	final private ParticipantEnglober parent;
	final private Display title;
	final private HColor boxColor;
	final private Stereotype stereotype;

	@Override
	public String toString() {
		return title.toString();
	}

	public static ParticipantEnglober build(Display title, HColor boxColor, Stereotype stereotype) {
		return new ParticipantEnglober(null, title, boxColor, stereotype);
	}

	public ParticipantEnglober newChild(Display title, HColor boxColor, Stereotype stereotype) {
		return new ParticipantEnglober(this, title, boxColor, stereotype);
	}

	private ParticipantEnglober(ParticipantEnglober parent, Display title, HColor boxColor, Stereotype stereotype) {
		this.parent = parent;
		this.title = title;
		this.boxColor = boxColor;
		this.stereotype = stereotype;
	}

	public final Display getTitle() {
		return title;
	}

	public final HColor getBoxColor() {
		return boxColor;
	}

	public final Stereotype getStereotype() {
		return stereotype;
	}

	public final ParticipantEnglober getParent() {
		return parent;
	}

	public final List<ParticipantEnglober> getGenealogy() {
		final LinkedList<ParticipantEnglober> result = new LinkedList<>();
		ParticipantEnglober current = this;
		while (current != null) {
			result.addFirst(current);
			current = current.getParent();
		}
		return Collections.unmodifiableList(result);
	}

}
