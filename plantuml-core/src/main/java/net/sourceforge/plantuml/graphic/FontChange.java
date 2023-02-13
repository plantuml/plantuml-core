package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;

public interface FontChange extends HtmlCommand {

	FontConfiguration apply(FontConfiguration initial);
}
