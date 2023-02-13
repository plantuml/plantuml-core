package net.sourceforge.plantuml.api;

public interface MyRunnable {

	public void runJob() throws InterruptedException;

	public void cancelJob();
}
