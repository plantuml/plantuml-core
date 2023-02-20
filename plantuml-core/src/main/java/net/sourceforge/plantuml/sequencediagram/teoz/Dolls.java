package net.sourceforge.plantuml.sequencediagram.teoz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.real.RealUtils;
import net.sourceforge.plantuml.sequencediagram.Doll;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.sequencediagram.ParticipantEnglober;
import net.sourceforge.plantuml.skin.Context2D;

public class Dolls {

	private final Map<ParticipantEnglober, Doll> alls = new LinkedHashMap<>();

	public Dolls(TileArguments tileArguments) {

		for (Participant p : tileArguments.getLivingSpaces().participants()) {
			final ParticipantEnglober englober = tileArguments.getLivingSpaces().get(p).getEnglober();
			if (englober != null)
				for (ParticipantEnglober pe : englober.getGenealogy())
					addParticipant(p, pe, tileArguments);

		}
	}

	private void addParticipant(Participant p, ParticipantEnglober englober, TileArguments tileArguments) {
		Doll already = alls.get(englober);
		if (already == null) {
			already = Doll.createTeoz(englober, tileArguments);
			alls.put(englober, already);
		}
		already.addParticipant(p);
	}

	private Doll getParent(Doll doll) {
		final ParticipantEnglober parent = doll.getParticipantEnglober().getParent();
		if (parent == null)
			return null;
		return alls.get(parent);
	}

	public int size() {
		return alls.size();
	}

	public double getOffsetForEnglobers(StringBounder stringBounder) {
		double result = 0;
		for (Doll doll : alls.values()) {
			double height = doll.getTitlePreferredHeight();
			final Doll group = getParent(doll);
			if (group != null)
				height += group.getTitlePreferredHeight();

			if (height > result)
				result = height;

		}
		return result;
	}

	public void addConstraints(StringBounder stringBounder) {
		for (Doll doll : alls.values()) {
			doll.addInternalConstraints(stringBounder);
		}

		for (Doll doll : alls.values()) {
			doll.addConstraintAfter(stringBounder);
		}

	}

	public void drawEnglobers(UGraphic ug, double height, Context2D context) {
		for (Doll doll : alls.values())
			doll.drawMe(ug, height, context, getParent(doll));

	}

	public Real getMinX(StringBounder stringBounder) {
		if (size() == 0)
			throw new IllegalStateException();

		final List<Real> result = new ArrayList<>();
		for (Doll doll : alls.values())
			result.add(doll.getMinX(stringBounder));

		return RealUtils.min(result);
	}

	public Real getMaxX(StringBounder stringBounder) {
		if (size() == 0)
			throw new IllegalStateException();

		final List<Real> result = new ArrayList<>();
		for (Doll doll : alls.values())
			result.add(doll.getMaxX(stringBounder));

		return RealUtils.max(result);
	}

}
