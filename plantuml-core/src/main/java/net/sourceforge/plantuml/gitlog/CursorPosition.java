package net.sourceforge.plantuml.gitlog;

public class CursorPosition {

	private final int x;
	private final int y;
	private final GitTextArea source;

	public CursorPosition(GitTextArea source, int x, int y) {
		this.source = source;
		this.x = x;
		this.y = y;
	}

	private String getCurrentLine() {
		return source.getLine(y);
	}

	@Override
	public String toString() {
		return "(" + (x + 1) + "," + (y + 1) + ")";
	}

	public CursorPosition move(int dx, int dy) {
		return new CursorPosition(source, x + dx, y + dy);
	}

	public boolean matches(String prefix) {
		if (x < 0) {
			return false;
		}
		final String line = getCurrentLine();
		if (x > line.length()) {
			return false;
		}
		return line.substring(x).startsWith(prefix);
	}

	public String getCommentInLine() {
		final String line = getCurrentLine();
		final int x = line.indexOf("*");
		if (x == -1) {
			return null;
		}
		final int y = line.indexOf("(", x);
		if (y == -1) {
			return null;
		}
		final int z = line.indexOf(")", y);
		if (z == -1) {
			return null;
		}
		return line.substring(y + 1, z);
	}

	public static String getCommitNameInLine(String s) {
		final int x = s.indexOf("*");
		if (x == -1) {
			return null;
		}
		s = s.replaceAll("[-.*|/\\\\]", "").trim();
		final int space = s.indexOf(" ");
		if (space == -1) {
			return s;
		}
		final String name = s.substring(0, space);
		return name;
	}

	public String getCommitDefinition() {
		return getCommitNameInLine(getCurrentLine().substring(x));
	}

	public Commit getCommit() {
		return new Commit(getCommitDefinition(), this);
	}

	public CursorPosition getDownFromHere() {
		return getDownFromInternal(this);
	}

	private static CursorPosition getDownFromInternal(CursorPosition current) {
		while (true) {
			if (current.matches("* ")) {
				return current;
			}
			if (current.matches("/") && current.move(-2, 0).matches("_|/")) {
				current = current.move(-2, 0);
				continue;
			}
			if (current.matches("_") && current.move(-2, 0).matches("_|_")) {
				current = current.move(-2, 0);
				continue;
			}
			if (current.matches("_") && current.move(-2, 1).matches("/")) {
				current = current.move(-2, 1);
				continue;
			}
			if (current.matches("|") && current.move(0, 1).matches("* ")) {
				current = current.move(0, 1);
				continue;
			}
			if (current.matches("|") && current.move(0, 1).matches("|")) {
				current = current.move(0, 1);
				continue;
			}
			if (current.matches("| ") && current.move(0, 1).matches(" \\") && current.move(2, 2).matches("|")) {
				current = current.move(2, 2);
				continue;
			}
			if (current.matches("| ") && current.move(0, 1).matches(" \\") && current.move(2, 2).matches("\\")) {
				current = current.move(2, 2);
				continue;
			}
			if (current.matches("| ") && current.move(-1, 1).matches("/ ") && current.move(-2, 2).matches("* ")) {
				current = current.move(-2, 2);
				continue;
			}
			if (current.matches("| ") && current.move(-3, 1).matches("_|/ ")) {
				current = current.move(-3, 1);
				continue;
			}
			if (current.matches("| ") && current.move(-1, 1).matches("/ ") && current.move(-2, 2).matches("| ")) {
				current = current.move(-2, 2);
				continue;
			}
			if (current.matches("| ") && current.move(-3, 1).matches("_|/")) {
				current = current.move(-3, 1);
				continue;
			}
			if (current.matches("| ") && current.move(0, 1).matches(" \\") && current.move(1, 2).matches("/ ")) {
				current = current.move(1, 2);
				continue;
			}
			if (current.matches("| ") && current.move(0, 1).matches(" \\") && current.move(2, 2).matches("* ")) {
				current = current.move(2, 2);
				continue;
			}
			if (current.matches("\\ ") && current.move(1, 1).matches("* ")) {
				current = current.move(1, 1);
				continue;
			}
			if (current.matches(" \\")) {
				current = current.move(2, 1);
				continue;
			}
			if (current.matches("\\ ") && current.move(0, 1).matches("/ ")) {
				current = current.move(0, 1);
				continue;
			}
			if (current.matches("\\ ") && current.move(1, 1).matches("\\ ")) {
				current = current.move(1, 1);
				continue;
			}
			if (current.matches("\\ ") && current.move(1, 1).matches("|")) {
				current = current.move(1, 1);
				continue;
			}
			if (current.matches("/ ") && current.move(-1, 1).matches("/ ")) {
				current = current.move(-1, 1);
				continue;
			}
			if (current.matches("/ ") && current.move(-2, 1).matches("/| ")) {
				current = current.move(-2, 1);
				continue;
			}
			if (current.matches("/") && current.move(-1, 1).matches("* ")) {
				current = current.move(-1, 1);
				continue;
			}
			if (current.matches("/") && current.move(-1, 1).matches("|")) {
				current = current.move(-1, 1);
				continue;
			}
			System.err.println("this=" + current);
			throw new UnsupportedOperationException(current.toString());
		}
	}

}
