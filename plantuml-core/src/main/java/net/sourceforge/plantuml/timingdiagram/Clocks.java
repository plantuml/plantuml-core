package net.sourceforge.plantuml.timingdiagram;

public interface Clocks {

	public TimeTick getNow();

	public TimeTick getClockValue(String clockName, int nb);

	public TimeTick getCodeValue(String code);

	public TimingFormat getTimingFormatDate();

}
