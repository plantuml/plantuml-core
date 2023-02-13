package net.sourceforge.plantuml.creole.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.creole.Parser;
import net.sourceforge.plantuml.creole.atom.Atom;
import net.sourceforge.plantuml.creole.atom.AtomHorizontalTexts;
import net.sourceforge.plantuml.creole.atom.AtomImg;
import net.sourceforge.plantuml.creole.atom.AtomOpenIcon;
import net.sourceforge.plantuml.creole.atom.AtomSprite;
import net.sourceforge.plantuml.creole.atom.AtomVerticalTexts;
import net.sourceforge.plantuml.creole.legacy.AtomText.DelayedDouble;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.ImgValign;
import net.sourceforge.plantuml.graphic.Splitter;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.openiconic.OpenIcon;
import net.sourceforge.plantuml.sprite.Sprite;
import net.sourceforge.plantuml.url.Url;

public class AtomTextUtils {

	protected static DelayedDouble ZERO = new DelayedDouble() {
		public double getDouble(StringBounder stringBounder) {
			return 0;
		}
	};

	public static Atom createLegacy(String text, FontConfiguration fontConfiguration) {
		return new AtomText(text, fontConfiguration, null, ZERO, ZERO, true);
	}

	public static Atom create(String text, FontConfiguration fontConfiguration) {
		return new AtomText(text, fontConfiguration, null, ZERO, ZERO, false);
	}

	public static Atom createUrl(Url url, FontConfiguration fontConfiguration, ISkinSimple skinSimple) {
		fontConfiguration = fontConfiguration.hyperlink();
		final Display display = Display.getWithNewlines(url.getLabel());
		if (display.size() > 1) {
			final List<Atom> all = new ArrayList<>();
			for (CharSequence s : display.asList()) {
				all.add(createAtomText(s.toString(), url, fontConfiguration, skinSimple));
			}
			return new AtomVerticalTexts(all);

		}
		return createAtomText(url.getLabel(), url, fontConfiguration, skinSimple);
	}

	private static final Pattern p = Pattern.compile(Splitter.openiconPattern + "|" + Splitter.spritePattern2 + "|"
			+ Splitter.imgPatternNoSrcColon + "|" + Splitter.emojiPattern);

	private static Atom createAtomText(final String text, Url url, FontConfiguration fontConfiguration,
			ISkinSimple skinSimple) {
		final Matcher m = p.matcher(text);
		final List<Atom> result = new ArrayList<>();
		while (m.find()) {
			final StringBuffer sb = new StringBuffer();
			m.appendReplacement(sb, "");
			if (sb.length() > 0) {
				result.add(new AtomText(sb.toString(), fontConfiguration, url, ZERO, ZERO, true));
			}
			final String valOpenicon = m.group(1);
			final String valSprite = m.group(3);
			final String valImg = m.group(5);
			final String valEmoji = m.group(7);
			if (valEmoji != null)
				throw new UnsupportedOperationException();

			if (valOpenicon != null) {
				final OpenIcon openIcon = OpenIcon.retrieve(valOpenicon);
				if (openIcon != null) {
					final double scale = Parser.getScale(m.group(2), 1);
					result.add(new AtomOpenIcon(null, scale, openIcon, fontConfiguration, url));
				}
			} else if (valSprite != null) {
				final Sprite sprite = skinSimple.getSprite(valSprite);
				if (sprite != null) {
					final double scale = Parser.getScale(m.group(4), 1);
					result.add(new AtomSprite(null, scale, fontConfiguration, sprite, url));
				}
			} else if (valImg != null) {
				final double scale = Parser.getScale(m.group(6), 1);
				result.add(AtomImg.create(valImg, ImgValign.TOP, 0, scale, url));

			}
		}
		final StringBuffer sb = new StringBuffer();
		m.appendTail(sb);
		if (sb.length() > 0) {
			result.add(new AtomText(sb.toString(), fontConfiguration, url, ZERO, ZERO, true));
		}
		if (result.size() == 1) {
			return result.get(0);
		}
		return new AtomHorizontalTexts(result);
	}

	public static Atom createListNumber(final FontConfiguration fontConfiguration, final int order, int localNumber) {
		final DelayedDouble left = new DelayedDouble() {
			public double getDouble(StringBounder stringBounder) {
				final XDimension2D dim = stringBounder.calculateDimension(fontConfiguration.getFont(), "9. ");
				return dim.getWidth() * order;
			}
		};
		final DelayedDouble right = new DelayedDouble() {
			public double getDouble(StringBounder stringBounder) {
				final XDimension2D dim = stringBounder.calculateDimension(fontConfiguration.getFont(), ".");
				return dim.getWidth();
			}
		};
		return new AtomText("" + (localNumber + 1) + ".", fontConfiguration, null, left, right, true);
	}

}
