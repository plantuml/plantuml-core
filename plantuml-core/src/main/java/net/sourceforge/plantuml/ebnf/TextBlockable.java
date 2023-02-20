package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.ISkinParam;

public interface TextBlockable {

	public TextBlock getUDrawable(ISkinParam skinParam);

}
