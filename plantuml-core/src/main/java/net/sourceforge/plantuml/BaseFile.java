package net.sourceforge.plantuml;

import net.sourceforge.plantuml.security.SFile;

public class BaseFile {

	private final String basename;
	private final SFile basedir;

	public BaseFile(SFile file) {
		if (file == null) {
			this.basedir = null;
			this.basename = null;
		} else {
			this.basedir = file.getParentFile();
			this.basename = extractBasename(file.getName());
		}
	}

	private static String extractBasename(String name) {
		final int idx = name.lastIndexOf('.');
		if (idx == -1) {
			return name;
		}
		return name.substring(0, idx);
	}

	@Override
	public String toString() {
		if (basedir == null || basename == null) {
			return "(DEFAULT)";
		}
		return basedir + " " + basename;
	}

	public String getBasename() {
		return basename;
	}

	public SFile getBasedir() {
		return basedir;
	}

	public SFile getTraceFile(String tail) {
		if (basedir == null || basename == null) {
			return new SFile(tail);
		}
		return basedir.file(basename + "_" + tail);
	}

}
