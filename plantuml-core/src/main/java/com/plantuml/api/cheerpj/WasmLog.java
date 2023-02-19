package com.plantuml.api.cheerpj;

import net.sourceforge.plantuml.utils.Log;

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
			if (StaticMemory.elementIdDebugJava == null)
				return;
			final Document document = Global.document;
			if (document == null)
				return;
			final Element messageJava = document.getElementById(Global.JSString(StaticMemory.elementIdDebugJava));
			if (messageJava == null)
				return;
			messageJava.set_textContent(Global.JSString(message));
		} catch (Throwable t) {
			System.err.print("Error " + t);
			t.printStackTrace();
		}
	}

}
