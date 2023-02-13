package net.sourceforge.plantuml.nwdiag.next;

public interface Staged {

	public NStage getStart();

	public NStage getEnd();

	public int getNWidth();

	public boolean contains(NStage stage);

}
