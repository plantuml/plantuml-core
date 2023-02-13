package com.plantuml.wasm;

import com.leaningtech.client.Document;
import com.leaningtech.client.Element;
import com.leaningtech.client.Global;

public class WasmLog {

	public static long start;

	public static void log(String message) {
		try {
		if (start > 0) {
			final long duration = System.currentTimeMillis() - start;
			message = "(" + duration + " ms) " + message;
		}
		System.err.print(message);
		final Document document = Global.document;
		if (document == null)
			return;
		final Element messageJava = document.getElementById(Global.JSString("debugjava"));
		if (messageJava == null)
			return;
		messageJava.set_textContent(Global.JSString(message));
	} catch (Throwable t) {
	}
	}

}
