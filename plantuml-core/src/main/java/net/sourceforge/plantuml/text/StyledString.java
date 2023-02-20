package net.sourceforge.plantuml.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.klimt.font.FontStyle;

public class StyledString {

	private final String text;
	private final FontStyle style;

	private StyledString(String text, FontStyle style) {
		this.text = text;
		this.style = style;
	}

	@Override
	public String toString() {
		return style + "[" + text + "]";
	}

	public final String getText() {
		return text;
	}

	public final FontStyle getStyle() {
		return style;
	}

	public static List<StyledString> build(String s) {
		final List<StyledString> result = new ArrayList<>();
		while (s.length() > 0) {
			final int i1 = s.indexOf(StringUtils.BOLD_START);
			if (i1 == -1) {
				result.add(new StyledString(s, FontStyle.PLAIN));
				s = "";
				break;
			}
			final int i2 = s.indexOf(StringUtils.BOLD_END);
			if (i1 > 0)
				result.add(new StyledString(s.substring(0, i1), FontStyle.PLAIN));

			if (i2 == -1) {
				result.add(new StyledString(s.substring(i1 + 1), FontStyle.BOLD));
				s = "";
			} else {
				result.add(new StyledString(s.substring(i1 + 1, i2), FontStyle.BOLD));
				s = s.substring(i2 + 1);
			}
		}
		return Collections.unmodifiableList(result);

	}

}
