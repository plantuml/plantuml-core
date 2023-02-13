package net.sourceforge.plantuml.ugraphic;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.graphic.InnerStrategy;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class ULayoutGroup {

	private final PlacementStrategy placementStrategy;

	public ULayoutGroup(PlacementStrategy placementStrategy) {
		this.placementStrategy = placementStrategy;
	}

	public void drawU(UGraphic ug, double width, double height) {
		for (Map.Entry<TextBlock, XPoint2D> ent : placementStrategy.getPositions(width, height).entrySet()) {
			final TextBlock block = ent.getKey();
			final XPoint2D pos = ent.getValue();
			block.drawU(ug.apply(new UTranslate(pos)));
		}
	}

	public void add(TextBlock block) {
		placementStrategy.add(block);

	}

	public XRectangle2D getInnerPosition(String member, double width, double height, StringBounder stringBounder) {
		final Set<Entry<TextBlock, XPoint2D>> all = placementStrategy.getPositions(width, height).entrySet();
		XRectangle2D result = tryOne(all, member, stringBounder, InnerStrategy.STRICT);
		if (result == null)
			result = tryOne(all, member, stringBounder, InnerStrategy.LAZZY);

		return result;
	}

	private XRectangle2D tryOne(final Set<Entry<TextBlock, XPoint2D>> all, String member, StringBounder stringBounder,
			InnerStrategy mode) {
		for (Map.Entry<TextBlock, XPoint2D> ent : all) {
			final TextBlock block = ent.getKey();
			final XRectangle2D result = block.getInnerPosition(member, stringBounder, mode);
			if (result != null) {
				final UTranslate translate = new UTranslate(ent.getValue());
				return translate.apply(result);
			}
		}
		return null;
	}

}
