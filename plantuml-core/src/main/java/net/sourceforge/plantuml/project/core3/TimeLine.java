package net.sourceforge.plantuml.project.core3;

public interface TimeLine {

	public static final Long MAX_TIME = 1000L * Integer.MAX_VALUE;

	public long getNext(long moment);

	public long getPrevious(long moment);

}
