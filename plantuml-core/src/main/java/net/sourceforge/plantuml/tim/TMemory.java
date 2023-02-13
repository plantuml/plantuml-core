package net.sourceforge.plantuml.tim;

import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.tim.expression.TValue;

public interface TMemory {

	public TValue getVariable(String varname);

	public void putVariable(String varname, TValue value, TVariableScope scope) throws EaterException;

	public void removeVariable(String varname);

	public boolean isEmpty();

	public Set<String> variablesNames();

	public Trie variablesNames3();

	public TMemory forkFromGlobal(Map<String, TValue> input);

	public ExecutionContextIf peekIf();

	public boolean areAllIfOk(TContext context, TMemory memory) throws EaterException;

	public void addIf(ExecutionContextIf context);

	public void addWhile(ExecutionContextWhile value);

	public void addForeach(ExecutionContextForeach value);

	public ExecutionContextIf pollIf();

	public ExecutionContextWhile pollWhile();

	public ExecutionContextWhile peekWhile();

	public ExecutionContextForeach pollForeach();

	public ExecutionContextForeach peekForeach();

	public void dumpDebug(String message);

}
