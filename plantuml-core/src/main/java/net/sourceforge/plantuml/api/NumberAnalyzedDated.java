package net.sourceforge.plantuml.api;

import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;
import java.util.prefs.Preferences;

import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.utils.Log;


public class NumberAnalyzedDated extends NumberAnalyzed {

	private final AtomicLong created = new AtomicLong();
	private final AtomicLong modified = new AtomicLong();
	private String comment;

	private NumberAnalyzedDated(String name, long nb, long sum, long min, long max, long sumOfSquare, long sliddingSum,
			long created, long modified, String comment) {
		super(name, nb, sum, min, max, sumOfSquare, sliddingSum);
		this.created.set(created);
		this.modified.set(modified);
		this.comment = comment;
	}

	@Override
	public synchronized void reset() {
		super.reset();
		resetCreatedModifiedComment();
	}

	public NumberAnalyzedDated() {
		super();
		resetCreatedModifiedComment();
	}

	public NumberAnalyzedDated(String name) {
		super(name);
		resetCreatedModifiedComment();
	}

	private void resetCreatedModifiedComment() {
		final long now = System.currentTimeMillis();
		this.created.set(now);
		this.modified.set(now);
		this.comment = " ";
	};

	@Override
	public void addValue(long v) {
		super.addValue(v);
		this.modified.set(System.currentTimeMillis());
	}

	@Override
	public void add(NumberAnalyzed other) {
		super.add(other);
		this.modified.set(System.currentTimeMillis());
	}

	@Override
	protected String getSavedSupplementatyData() {
		return longToString(created.get()) + ";" + longToString(modified.get()) + ";" + comment;
	}

	public static NumberAnalyzedDated load(String name, Preferences prefs) {
		final String value = prefs.get(name + ".saved", "");
		if (value.length() == 0) {
			Log.info("Cannot load " + name);
			return null;
		}
		try {
			final StringTokenizer st = new StringTokenizer(value, ";");
			return new NumberAnalyzedDated(name, Long.parseLong(st.nextToken(), 36),
					Long.parseLong(st.nextToken(), 36), Long.parseLong(st.nextToken(), 36), Long.parseLong(
							st.nextToken(), 36), Long.parseLong(st.nextToken(), 36),
					Long.parseLong(st.nextToken(), 36), Long.parseLong(st.nextToken(), 36), Long.parseLong(
							st.nextToken(), 36), st.nextToken());
		} catch (Exception e) {
			Logme.error(e);
			Log.info("Error reading " + value);
			return null;
		}
	}

	final public long getCreationTime() {
		return created.get();
	}

	final public long getModificationTime() {
		return modified.get();
	}

	final public synchronized String getComment() {
		return comment;
	}

	final public synchronized void setComment(String comment) {
		this.comment = comment;
	}

}
