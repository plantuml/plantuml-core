package net.sourceforge.plantuml.sequencediagram;

public interface EventWithDeactivate extends Event {

	public void setPosYendLevel(double posYendLevel);

	public double getPosYendLevel();

	public boolean addLifeEvent(LifeEvent lifeEvent);

}
