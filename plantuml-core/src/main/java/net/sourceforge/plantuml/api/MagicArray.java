// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.api;

public final class MagicArray {

	private final int data[];
	private final int size;
	private long lastUpdatedKey = -1;
	private int lastUpdatedValue;
	private long sum;
	private long maxSum;

	public MagicArray(int size) {
		this.data = new int[size];
		this.size = size;
	}

	synchronized public void incKey(long key) {
		incKey(key, 1);
	}

	synchronized public void incKey(long key, int delta) {
		if (key < lastUpdatedKey) {
			return;
		}
		if (key != lastUpdatedKey) {
			if (lastUpdatedKey != -1) {
				setValue(lastUpdatedKey, lastUpdatedValue);
				for (long i = lastUpdatedKey + 1; i < key; i++) {
					setValue(i, 0);
				}
			}
			lastUpdatedValue = 0;
			lastUpdatedKey = key;
		}
		lastUpdatedValue += delta;
	}

	private void setValue(long key, int value) {
		final int i = (int) (key % size);
		sum += value - data[i];
		if (sum > maxSum) {
			maxSum = sum;
		}
		data[i] = value;
	}

	synchronized public long getSum() {
		return sum;
	}

	synchronized public long getMaxSum() {
		return maxSum;
	}

	private long getSumSlow() {
		long tmp = 0;
		for (int d : data) {
			tmp += d;
		}
		return tmp;
	}

}
