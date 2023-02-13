package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.json.JsonArray;
import net.sourceforge.plantuml.tim.iterator.CodePosition;

public class ExecutionContextForeach {

	private final String varname;
	private final JsonArray jsonArray;
	private final CodePosition codePosition;
	private boolean skipMe;
	private int currentIndex;

	private ExecutionContextForeach(String varname, JsonArray jsonArray, CodePosition codePosition) {
		this.varname = varname;
		this.jsonArray = jsonArray;
		this.codePosition = codePosition;
	}

	public static ExecutionContextForeach fromValue(String varname, JsonArray jsonArray, CodePosition codePosition) {
		return new ExecutionContextForeach(varname, jsonArray, codePosition);
	}

	public void skipMeNow() {
		skipMe = true;
	}

	public final boolean isSkipMe() {
		return skipMe;
	}

	public CodePosition getStartForeach() {
		return codePosition;
	}

	public final int currentIndex() {
		return currentIndex;
	}

	public final void inc() {
		this.currentIndex++;
		if (currentIndex >= jsonArray.size()) {
			this.skipMe = true;
		}
	}

	public final String getVarname() {
		return varname;
	}

	public final JsonArray getJsonArray() {
		return jsonArray;
	}

}
