package net.sourceforge.plantuml.tim;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class TFunctionSignature {

	private final String functionName;
	private final int nbArg;
	private final Set<String> namedArguments;

	public TFunctionSignature(String functionName, int nbArg) {
		this(functionName, nbArg, Collections.<String>emptySet());
	}

	public TFunctionSignature(String functionName, int nbArg, Set<String> namedArguments) {
		this.functionName = Objects.requireNonNull(functionName);
		this.nbArg = nbArg;
		this.namedArguments = namedArguments;
	}

	public boolean sameFunctionNameAs(TFunctionSignature other) {
		return getFunctionName().equals(other.getFunctionName());
	}

	@Override
	public String toString() {
		return functionName + "/" + nbArg + " " + namedArguments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + functionName.hashCode();
		result = prime * result + nbArg;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		final TFunctionSignature other = (TFunctionSignature) obj;
		return functionName.equals(other.functionName) && nbArg == other.nbArg;
	}

	public final String getFunctionName() {
		return functionName;
	}

	public final int getNbArg() {
		return nbArg;
	}

	public final Set<String> getNamedArguments() {
		return namedArguments;
	}
}
