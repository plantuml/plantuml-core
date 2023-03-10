// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.creole.command;

import java.util.EnumSet;

import net.sourceforge.plantuml.klimt.font.FontPosition;
import net.sourceforge.plantuml.klimt.font.FontStyle;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;

class HtmlCommandFactory {

	static final Pattern2 addStyle;
	static final Pattern2 removeStyle;

	static {
		final StringBuilder sbAddStyle = new StringBuilder();
		final StringBuilder sbRemoveStyle = new StringBuilder();

		for (FontStyle style : EnumSet.allOf(FontStyle.class)) {
			if (sbAddStyle.length() > 0) {
				sbAddStyle.append('|');
				sbRemoveStyle.append('|');
			}
			sbAddStyle.append(style.getActivationPattern());
			sbRemoveStyle.append(style.getDeactivationPattern());
		}

		addStyle = MyPattern.cmpile(sbAddStyle.toString());
		removeStyle = MyPattern.cmpile(sbRemoveStyle.toString());
	}

	private Pattern2 htmlTag = MyPattern.cmpile(Splitter.htmlTag);

	HtmlCommand getHtmlCommand(String s) {
		if (htmlTag.matcher(s).matches() == false)
			return new PlainText(s);

		if (MyPattern.mtches(s, Splitter.imgPattern))
			return Img.getInstance(s, true);

		if (MyPattern.mtches(s, Splitter.imgPatternNoSrcColon))
			return Img.getInstance(s, false);

		if (addStyle.matcher(s).matches())
			return AddStyle.fromString(s);

		if (removeStyle.matcher(s).matches())
			return new RemoveStyle(FontStyle.getStyle(s));

		if (MyPattern.mtches(s, Splitter.fontPattern))
			return new ColorAndSizeChange(s);

		if (MyPattern.mtches(s, Splitter.fontColorPattern2))
			return new ColorChange(s);

		if (MyPattern.mtches(s, Splitter.fontSizePattern2))
			return new SizeChange(s);

		if (MyPattern.mtches(s, Splitter.fontSup))
			return new ExposantChange(FontPosition.EXPOSANT);

		if (MyPattern.mtches(s, Splitter.fontSub))
			return new ExposantChange(FontPosition.INDICE);

		if (MyPattern.mtches(s, Splitter.endFontPattern))
			return new ResetFont();

		if (MyPattern.mtches(s, Splitter.endSupSub))
			return new ExposantChange(FontPosition.NORMAL);

		if (MyPattern.mtches(s, Splitter.fontFamilyPattern))
			return new FontFamilyChange(s);

		if (MyPattern.mtches(s, Splitter.spritePatternForMatch))
			return new SpriteCommand(s);

		if (MyPattern.mtches(s, Splitter.linkPattern)) {
			final UrlBuilder urlBuilder = new UrlBuilder(null, UrlMode.STRICT);
			final Url url = urlBuilder.getUrl(s);
			url.setMember(true);
			return new TextLink(url);
		}

		if (MyPattern.mtches(s, Splitter.svgAttributePattern))
			return new SvgAttributesChange(s);

		return null;
	}

}
