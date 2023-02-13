package net.sourceforge.plantuml.tim;

public enum TFunctionType {

	PROCEDURE, RETURN_FUNCTION, LEGACY_DEFINE, LEGACY_DEFINELONG;

	public boolean isLegacy() {
		return this == LEGACY_DEFINE || this == LEGACY_DEFINELONG;
	}

}
