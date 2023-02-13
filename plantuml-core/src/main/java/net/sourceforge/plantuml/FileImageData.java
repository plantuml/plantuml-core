package net.sourceforge.plantuml;

import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.security.SFile;

public class FileImageData {
	
	public static final int ERROR = 400;
	public static final int CRASH = 503;

	private final SFile file;
	private final ImageData imageData;

	public FileImageData(SFile file, ImageData imageData) {
		this.file = file;
		this.imageData = imageData;
	}

	public SFile getFile() {
		return file;
	}

	public ImageData getImageData() {
		return imageData;
	}

	public int getStatus() {
		if (imageData == null) {
			return 0;
		}
		return imageData.getStatus();
	}

}
