package net.sourceforge.plantuml.sequencediagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.url.Url;

final public class GroupingLeaf extends Grouping implements EventWithDeactivate {

	private final GroupingStart start;
	private final HColor backColorGeneral;

	public GroupingLeaf(String title, String comment, GroupingType type, HColor backColorGeneral,
			HColor backColorElement, GroupingStart start, StyleBuilder styleBuilder) {
		super(title, comment, type, backColorElement, styleBuilder);
		this.backColorGeneral = backColorGeneral;
		this.start = Objects.requireNonNull(start);
		start.addChildren(this);
	}

	public Grouping getJustAfter() {
		final int idx = start.getChildren().indexOf(this);
		if (idx == -1) {
			throw new IllegalStateException();
		}
		if (idx + 1 >= start.getChildren().size()) {
			return null;
		}
		return start.getChildren().get(idx + 1);
	}

	public GroupingStart getGroupingStart() {
		return start;
	}

	@Override
	public int getLevel() {
		return start.getLevel();
	}

	@Override
	public final HColor getBackColorGeneral() {
		if (backColorGeneral == null)
			return start.getBackColorGeneral();

		return backColorGeneral;
	}

	public boolean dealWith(Participant someone) {
		return false;
	}

	public Url getUrl() {
		return null;
	}

	public boolean hasUrl() {
		return false;
	}

	@Override
	public boolean isParallel() {
		return start.isParallel();
	}

	private double posYendLevel;

	public void setPosYendLevel(double posYendLevel) {
		this.posYendLevel = posYendLevel;
	}

	public double getPosYendLevel() {
		return posYendLevel;
	}

	public boolean addLifeEvent(LifeEvent lifeEvent) {
		return true;
	}

	private List<Note> noteOnMessages = new ArrayList<>();

	public final void setNote(Note note) {
		if (note.getPosition() != NotePosition.LEFT && note.getPosition() != NotePosition.RIGHT) {
			throw new IllegalArgumentException();
		}
		this.noteOnMessages.add(note);
	}

	public final List<Note> getNoteOnMessages() {
		return noteOnMessages;
	}

}
