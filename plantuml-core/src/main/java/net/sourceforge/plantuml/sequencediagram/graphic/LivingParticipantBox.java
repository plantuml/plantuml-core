package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.InGroupable;

public class LivingParticipantBox implements InGroupable {

	private final ParticipantBox participantBox;
	private final LifeLine lifeLine;

	public LivingParticipantBox(ParticipantBox participantBox, LifeLine lifeLine) {
		this.participantBox = participantBox;
		this.lifeLine = lifeLine;
	}

	/**
	 * @deprecated to be deleted
	 */
	@Deprecated
	public ParticipantBox getParticipantBox() {
		return participantBox;
	}

	/**
	 * @deprecated to be deleted
	 */
	@Deprecated
	public LifeLine getLifeLine() {
		return lifeLine;
	}

	public SegmentColored getLiveThicknessAt(StringBounder stringBounder, double y) {
		final double left = lifeLine.getLeftShift(y);
		assert left >= 0;
		final double right = lifeLine.getRightShift(y);
		assert right >= 0 : "right=" + right;
		final double centerX = participantBox.getCenterX(stringBounder);
		// Log.println("AZERTY " + y + " centerX=" + centerX + " left=" + left + "
		// right=" + right);
		// Log.println("Attention, null for segment");
		final Fashion colors = lifeLine.getColors();
		return SegmentColored.create(centerX - left, centerX + right, colors, lifeLine.shadowing());
	}

	public void drawLineU22(UGraphic ug, double startingY, double endingY, boolean showTail, double myDelta) {
		if (endingY <= startingY) {
			return;
		}
		final double destroy = lifeLine.getDestroy();
		if (destroy != 0 && destroy > startingY && destroy < endingY) {
			endingY = destroy;
		}
		participantBox.drawLineU22(ug, startingY, endingY, showTail, myDelta);
	}

	public double magicMargin(StringBounder stringBounder) {
		return participantBox.magicMargin(stringBounder);
	}

	public void create(double ypos) {
		lifeLine.setCreate(ypos);
	}

	public double getCreate() {
		return lifeLine.getCreate();
	}

	public double getMaxX(StringBounder stringBounder) {
		return participantBox.getMaxX(stringBounder);
	}

	public double getMinX(StringBounder stringBounder) {
		return participantBox.getStartingX();
	}

	public String toString(StringBounder stringBounder) {
		return toString();
	}

}
