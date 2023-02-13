package net.sourceforge.plantuml.preproc;

class NumericCompare {

	private final String operator;

	public NumericCompare(String operator) {
		this.operator = operator;
	}

	public boolean isCompareOk(int value1, int value2) {
		if (operator.equals("<")) {
			return value1 < value2;
		}
		if (operator.equals("<=")) {
			return value1 <= value2;
		}
		if (operator.equals(">")) {
			return value1 > value2;
		}
		if (operator.equals(">=")) {
			return value1 >= value2;
		}
		if (operator.equals("=") || operator.equals("==")) {
			return value1 == value2;
		}
		if (operator.equals("!=") || operator.equals("<>")) {
			return value1 != value2;
		}
		throw new IllegalStateException();
	}

}
