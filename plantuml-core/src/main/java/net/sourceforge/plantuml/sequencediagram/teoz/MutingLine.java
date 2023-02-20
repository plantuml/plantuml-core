package net.sourceforge.plantuml.sequencediagram.teoz;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.sequencediagram.Delay;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.ComponentType;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;

public class MutingLine {

	private final Rose skin;
	private final ISkinParam skinParam;
	private final boolean useContinueLineBecauseOfDelay;
	private final Map<Double, Double> delays = new TreeMap<Double, Double>();
	private final StyleBuilder styleBuilder;
	private final Participant participant;

	public MutingLine(Rose skin, ISkinParam skinParam, List<Event> events, Participant participant) {
		this.participant = participant;
		this.skin = skin;
		this.skinParam = skinParam;
		this.useContinueLineBecauseOfDelay = useContinueLineBecauseOfDelay(events);
		this.styleBuilder = skinParam.getCurrentStyleBuilder();
	}

	private boolean useContinueLineBecauseOfDelay(List<Event> events) {
		final String strategy = skinParam.getValue("lifelineStrategy");
		if ("nosolid".equalsIgnoreCase(strategy))
			return false;

		for (Event ev : events)
			if (ev instanceof Delay)
				return true;

		return false;
	}

	public void drawLine(UGraphic ug, Context2D context, double createY, double endY) {
		final ComponentType defaultLineType = useContinueLineBecauseOfDelay ? ComponentType.CONTINUE_LINE
				: ComponentType.PARTICIPANT_LINE;
		if (delays.size() > 0) {
			double y = createY;
			for (Map.Entry<Double, Double> ent : delays.entrySet()) {
				if (ent.getKey() >= createY) {
					drawInternal(ug, context, y, ent.getKey(), defaultLineType);
					drawInternal(ug, context, ent.getKey(), ent.getKey() + ent.getValue(), ComponentType.DELAY_LINE);
					y = ent.getKey() + ent.getValue();
				}
			}
			drawInternal(ug, context, y, endY, defaultLineType);
		} else {
			drawInternal(ug, context, createY, endY, defaultLineType);
		}
	}

	private void drawInternal(UGraphic ug, Context2D context, double y1, double y2,
			final ComponentType defaultLineType) {
		if (y2 == y1)
			return;

		if (y2 < y1)
			throw new IllegalArgumentException();

		final Style style = defaultLineType.getStyleSignature().getMergedStyle(styleBuilder);
		final Component comp = skin.createComponent(new Style[] { style }, defaultLineType, null, skinParam,
				participant.getDisplay(skinParam.forceSequenceParticipantUnderlined()));
		final XDimension2D dim = comp.getPreferredDimension(ug.getStringBounder());
		final Area area = Area.create(dim.getWidth(), y2 - y1);
		comp.drawU(ug.apply(UTranslate.dy(y1)), area, context);
	}

	public void delayOn(double y, double height) {
		delays.put(y, height);
	}

}
