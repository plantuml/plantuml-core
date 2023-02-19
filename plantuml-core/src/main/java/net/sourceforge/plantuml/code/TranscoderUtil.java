package net.sourceforge.plantuml.code;

public class TranscoderUtil {
	public static Transcoder getDefaultTranscoder() {
		return new TranscoderSmart();
	}


}
