package net.sourceforge.plantuml.api;

import java.util.concurrent.atomic.AtomicBoolean;

import net.sourceforge.plantuml.log.Logme;

public final class TimeoutExecutor {

	private final long ms;

	public TimeoutExecutor(long ms) {
		this.ms = ms;
	}

	public boolean executeNow(MyRunnable task) {
		final MyThread mainThread = new MyThread(task);
		boolean done = false;
		try {
			mainThread.start();
			mainThread.join(ms);
		} catch (InterruptedException e) {
			System.err.println("TimeoutExecutorA " + e);
			Logme.error(e);
			return false;
		} finally {
			done = mainThread.done.get();
			if (done == false) {
				task.cancelJob();
				mainThread.interrupt();
			}
		}
		return done;
	}

	class MyThread extends Thread {
		private final MyRunnable task;
		private final AtomicBoolean done = new AtomicBoolean(false);

		private MyThread(MyRunnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			try {
				task.runJob();
				done.set(true);
			} catch (InterruptedException e) {
				System.err.println("TimeoutExecutorB " + e);
				Logme.error(e);
			}
		}

	}
}
