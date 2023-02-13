package net.sourceforge.plantuml.gitlog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GitTextArea {

	private final List<String> lines = new ArrayList<>();
	private final List<Commit> commits = new ArrayList<>();

	public void add(String s) {
		lines.add(s);
	}

	public List<Commit> getAllCommits() {
		if (commits.size() == 0)
			for (int y = 0; y < lines.size(); y++) {
				String s = lines.get(y);
				final String name = CursorPosition.getCommitNameInLine(s);
				final int x = s.indexOf("*");
				assert (name == null) == (x == -1);
				if (x == -1) {
					continue;
				}
				commits.add(new Commit(name, new CursorPosition(this, x, y)));
			}

		return Collections.unmodifiableList(commits);
	}

	public char charAt(int x, int y) {
		return lines.get(y).charAt(x);
	}

	public String getLine(int y) {
		if (y >= lines.size()) {
			return "";
		}
		return lines.get(y);
	}

	public Commit getCommitByName(String name) {
		for (Commit commit : getAllCommits()) {
			if (commit.getName().equals(name)) {
				return commit;
			}
		}
		return null;
	}

}
