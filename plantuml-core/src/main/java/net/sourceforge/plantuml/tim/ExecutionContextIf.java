package net.sourceforge.plantuml.tim;

public class ExecutionContextIf {

	private boolean isTrue;
	private boolean hasBeenBurn;

	private ExecutionContextIf(boolean isTrue) {
		this.isTrue = isTrue;
		if (this.isTrue) {
			hasBeenBurn = true;
		}
	}

	public static ExecutionContextIf fromValue(boolean isTrue) {
		return new ExecutionContextIf(isTrue);
	}

	public boolean conditionIsOkHere() {
		return isTrue;
	}

	public void enteringElseIf() {
		this.isTrue = false;
	}

	public void nowInElse() {
		this.isTrue = !hasBeenBurn;
	}

	public void nowInSomeElseIf() {
		this.isTrue = true;
		this.hasBeenBurn = true;
	}

	public final boolean hasBeenBurn() {
		return hasBeenBurn;
	}

	public final void setHasBeenBurn(boolean hasBeenBurn) {
		this.hasBeenBurn = hasBeenBurn;
	}

}
