package net.sourceforge.plantuml.klimt.creole.command;

import java.util.Objects;

import net.sourceforge.plantuml.url.Url;

public class TextLink implements HtmlCommand {

	private final Url url;

	TextLink(Url url) {
		this.url = Objects.requireNonNull(url);
	}

	public String getText() {
		return url.getLabel();
	}

	public Url getUrl() {
		return url;
	}

}
