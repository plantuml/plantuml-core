// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package com.plantuml.api.cheerpj;

import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.error.PSystemError;

import com.leaningtech.client.Global;

public class JsonResult {

	private final StringBuilder sb = new StringBuilder();

	private JsonResult(long startingTime) {
		sb.append("{");
		this.append("duration", System.currentTimeMillis() - startingTime);
	}

	private Object done() {
		sb.append("}");
		 return Global.JSString(sb.toString());
	}

	public static Object noDataFound(long startingTime) {
		final JsonResult res = new JsonResult(startingTime);
		res.append("status", "No data found");
		return res.done();
	}

	public static Object fromCrash(long startingTime, Throwable t) {
		final JsonResult res = new JsonResult(startingTime);
		res.append("status", "General failure");
		res.append("exception", t.toString());
		return res.done();
	}

	public static Object ok(long startingTime, ImageData imageData, Diagram diagram) {
		final JsonResult res = new JsonResult(startingTime);
		res.append("status", "ok");
		if (imageData != null) {
			res.append("width", imageData.getWidth());
			res.append("height", imageData.getHeight());
		}
		res.append("description", diagram.getDescription().getDescription());
		return res.done();
	}

	public static Object fromError(long startingTime, PSystemError system) {
		final JsonResult res = new JsonResult(startingTime);
		res.append("status", "Parsing error");

		final ErrorUml err = system.getErrorsUml().iterator().next();
		res.append("line", err.getPosition());
		res.append("error", err.getError());

		return res.done();
	}

	private void append(String key, String value) {
		appendKeyOnly(key);
		sb.append('\"');
		sb.append(value);
		sb.append('\"');
	}

	private void append(String key, long value) {
		appendKeyOnly(key);
		sb.append(value);
	}

	protected void appendKeyOnly(String key) {
		if (sb.length() > 1)
			sb.append(',');
		sb.append('\"');
		sb.append(key);
		sb.append('\"');
		sb.append(':');
	}

}
