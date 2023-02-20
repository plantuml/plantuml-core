package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class EntityImageLegend {

	public static TextBlock create(Display note, ISkinParam skinParam) {

		final Style style = StyleSignatureBasic
				.of(SName.root, SName.root, SName.document, skinParam.getUmlDiagramType().getStyleName(), SName.legend)
				.getMergedStyle(skinParam.getCurrentStyleBuilder());
		return style.createTextBlockBordered(note, skinParam.getIHtmlColorSet(), skinParam, Style.ID_LEGEND);
	}

}
