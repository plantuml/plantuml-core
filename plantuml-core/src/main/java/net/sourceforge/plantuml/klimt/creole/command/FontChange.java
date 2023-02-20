package net.sourceforge.plantuml.klimt.creole.command;

import net.sourceforge.plantuml.klimt.font.FontConfiguration;

public interface FontChange extends HtmlCommand {

	FontConfiguration apply(FontConfiguration initial);
}
