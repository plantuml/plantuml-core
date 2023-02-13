package net.sourceforge.plantuml;

import java.io.File;

public interface GeneratedImage extends Comparable<GeneratedImage> {

	public File getPngFile();

	public String getDescription();

	public int lineErrorRaw();

	public int getStatus();

}
