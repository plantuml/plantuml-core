package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Objects;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.ArrowComponent;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.url.Url;

class MessageArrow extends Arrow {

	private final LivingParticipantBox p1;
	private final LivingParticipantBox p2;
	private final Component compAliveBox;

	public MessageArrow(double startingY, Rose skin, ArrowComponent arrow, LivingParticipantBox p1, LivingParticipantBox p2,
			Url url, Component compAliveBox) {
		super(startingY, skin, arrow, url);

		if (p1 == p2) {
			throw new IllegalArgumentException();
		}
		this.p1 = Objects.requireNonNull(p1);
		this.p2 = Objects.requireNonNull(p2);
		this.compAliveBox = compAliveBox;
	}

	@Override
	public double getActualWidth(StringBounder stringBounder) {
		final double r = getRightEndInternal(stringBounder) - getLeftStartInternal(stringBounder);
		assert r > 0;
		return r;
	}

	private double getLeftStartInternal(StringBounder stringBounder) {
		return getParticipantAt(stringBounder, NotePosition.LEFT)
				.getLiveThicknessAt(stringBounder, getArrowYStartLevel(stringBounder)).getSegment().getPos2();
	}

	private double getRightEndInternal(StringBounder stringBounder) {
		final Segment segment = getParticipantAt(stringBounder, NotePosition.RIGHT).getLiveThicknessAt(stringBounder,
				getArrowYStartLevel(stringBounder)).getSegment();
		if (segment.getLength() == 0) {
			return segment.getPos1();
		}
		final double rectWidth = compAliveBox.getPreferredWidth(stringBounder);
		return segment.getPos2() - rectWidth;
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return getArrowComponent().getPreferredHeight(stringBounder);
	}

	@Override
	public double getStartingX(StringBounder stringBounder) {
		return getLeftStartInternal(stringBounder);
	}

	@Override
	public int getDirection(StringBounder stringBounder) {
		final double x1 = p1.getParticipantBox().getCenterX(stringBounder);
		final double x2 = p2.getParticipantBox().getCenterX(stringBounder);
		if (x1 < x2) {
			return 1;
		}
		return -1;
	}

	public LivingParticipantBox getParticipantAt(StringBounder stringBounder, NotePosition position) {
		final int direction = getDirection(stringBounder);
		if (direction == 1 && position == NotePosition.RIGHT) {
			return p2;
		}
		if (direction == 1 && position == NotePosition.LEFT) {
			return p1;
		}
		if (direction == -1 && position == NotePosition.RIGHT) {
			return p1;
		}
		if (direction == -1 && position == NotePosition.LEFT) {
			return p2;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return getArrowComponent().getPreferredWidth(stringBounder);
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
		final StringBounder stringBounder = ug.getStringBounder();
		ug = ug.apply(new UTranslate(getStartingX(stringBounder), getStartingY()));
		startUrl(ug);
		getArrowComponent().drawU(ug, new Area(getActualDimension(stringBounder)), context);
		endUrl(ug);
	}

	private XDimension2D getActualDimension(StringBounder stringBounder) {
		return new XDimension2D(getActualWidth(stringBounder) - getPaddingArrowHead(), getArrowComponent()
				.getPreferredHeight(stringBounder));
	}

	@Override
	public double getArrowYStartLevel(StringBounder stringBounder) {
		if (getArrowComponent() instanceof ArrowComponent) {
			final ArrowComponent arrowComponent = (ArrowComponent) getArrowComponent();
			final XDimension2D dim = new XDimension2D(arrowComponent.getPreferredWidth(stringBounder),
					arrowComponent.getPreferredHeight(stringBounder));
			return getStartingY() + arrowComponent.getStartPoint(stringBounder, dim).getY();
		}
		return getStartingY();
	}

	@Override
	public double getArrowYEndLevel(StringBounder stringBounder) {
		if (getArrowComponent() instanceof ArrowComponent) {
			final ArrowComponent arrowComponent = (ArrowComponent) getArrowComponent();
			final XDimension2D dim = new XDimension2D(arrowComponent.getPreferredWidth(stringBounder),
					arrowComponent.getPreferredHeight(stringBounder));
			return getStartingY() + arrowComponent.getEndPoint(stringBounder, dim).getY();
		}
		return getStartingY() + getArrowComponent().getPreferredHeight(stringBounder);
	}

	public double getMaxX(StringBounder stringBounder) {
		return getRightEndInternal(stringBounder);
	}

	public double getMinX(StringBounder stringBounder) {
		return getLeftStartInternal(stringBounder);
	}

	public String toString(StringBounder stringBounder) {
		return getMinX(stringBounder) + "-" + getMaxX(stringBounder);
	}

}
