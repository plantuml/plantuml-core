package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.Month;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementDate implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexOr(toRegexA(suffix), toRegexB(suffix), toRegexC(suffix), toRegexD(suffix), toRegexE(suffix));
	}

	private IRegex toRegexA(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("ADAY" + suffix, "([\\d]+)"), //
				new RegexLeaf("[\\w, ]*?"), //
				new RegexLeaf("AMONTH" + suffix, "(" + Month.getRegexString() + ")"), //
				new RegexLeaf("[\\w, ]*?"), //
				new RegexLeaf("AYEAR" + suffix, "([\\d]{4})"));
	}

	private IRegex toRegexB(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("BYEAR" + suffix, "([\\d]{4})"), //
				new RegexLeaf("\\D"), //
				new RegexLeaf("BMONTH" + suffix, "([\\d]{1,2})"), //
				new RegexLeaf("\\D"), //
				new RegexLeaf("BDAY" + suffix, "([\\d]{1,2})"));
	}

	private IRegex toRegexC(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("CMONTH" + suffix, "(" + Month.getRegexString() + ")"), //
				new RegexLeaf("[\\w, ]*?"), //
				new RegexLeaf("CDAY" + suffix, "([\\d]+)"), //
				new RegexLeaf("[\\w, ]*?"), //
				new RegexLeaf("CYEAR" + suffix, "([\\d]{4})"));
	}

	private IRegex toRegexD(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("DCOUNT" + suffix, "([\\d]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("days?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("after"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("start") //
		);
	}

	private IRegex toRegexE(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("[dD]\\+"), //
				new RegexLeaf("ECOUNT" + suffix, "([\\d]+)") //
		);
	}

	public Failable<Day> getMe(GanttDiagram system, RegexResult arg, String suffix) {
		if (arg.get("ADAY" + suffix, 0) != null) {
			return Failable.ok(resultA(arg, suffix));
		}
		if (arg.get("BDAY" + suffix, 0) != null) {
			return Failable.ok(resultB(arg, suffix));
		}
		if (arg.get("CDAY" + suffix, 0) != null) {
			return Failable.ok(resultC(arg, suffix));
		}
		if (arg.get("DCOUNT" + suffix, 0) != null) {
			return Failable.ok(resultD(system, arg, suffix));
		}
		if (arg.get("ECOUNT" + suffix, 0) != null) {
			return Failable.ok(resultE(system, arg, suffix));
		}
		throw new IllegalStateException();
	}

	private Day resultA(RegexResult arg, String suffix) {
		final int day = Integer.parseInt(arg.get("ADAY" + suffix, 0));
		final String month = arg.get("AMONTH" + suffix, 0);
		final int year = Integer.parseInt(arg.get("AYEAR" + suffix, 0));
		return Day.create(year, month, day);
	}

	private Day resultB(RegexResult arg, String suffix) {
		final int day = Integer.parseInt(arg.get("BDAY" + suffix, 0));
		final int month = Integer.parseInt(arg.get("BMONTH" + suffix, 0));
		final int year = Integer.parseInt(arg.get("BYEAR" + suffix, 0));
		return Day.create(year, month, day);
	}

	private Day resultC(RegexResult arg, String suffix) {
		final int day = Integer.parseInt(arg.get("CDAY" + suffix, 0));
		final String month = arg.get("CMONTH" + suffix, 0);
		final int year = Integer.parseInt(arg.get("CYEAR" + suffix, 0));
		return Day.create(year, month, day);
	}

	private Day resultD(GanttDiagram system, RegexResult arg, String suffix) {
		final int day = Integer.parseInt(arg.get("DCOUNT" + suffix, 0));
		return system.getStartingDate().addDays(day);
	}

	private Day resultE(GanttDiagram system, RegexResult arg, String suffix) {
		final int day = Integer.parseInt(arg.get("ECOUNT" + suffix, 0));
		return system.getStartingDate().addDays(day);
	}

}
