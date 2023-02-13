package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FrontierStackImpl implements FrontierStack {

	class Stack {
		final private FrontierComplex current;
		final private FrontierComplex envelop;

		Stack(FrontierComplex current) {
			this(current, null);
		}

		private Stack(FrontierComplex current, FrontierComplex envelop) {
			this.current = current;
			this.envelop = envelop;
		}

		Stack addEnvelop(FrontierComplex env) {
			if (this.envelop == null) {
				return new Stack(this.current, env);
			}
			return new Stack(this.current, this.envelop.mergeMax(env));
		}
	}

	final private List<Stack> all;

	public FrontierStackImpl(double freeY, int rangeEnd) {
		final Stack s = new Stack(FrontierComplex.create(freeY, rangeEnd));
		all = Collections.singletonList(s);
	}

	private FrontierStackImpl(List<Stack> all) {
		this.all = Collections.unmodifiableList(all);
	}

	private FrontierComplex getLast() {
		return all.get(all.size() - 1).current;
	}

	public double getFreeY(ParticipantRange range) {
		return getLast().getFreeY(range);
	}

	public FrontierStackImpl add(double delta, ParticipantRange range) {
		final List<Stack> result = new ArrayList<>(all);
		final Stack s = new Stack(getLast().add(delta, range));
		result.set(result.size() - 1, s);
		return new FrontierStackImpl(result);
	}

	public FrontierStack openBar() {
		final List<Stack> result = new ArrayList<>(all);
		final Stack s = new Stack(getLast().copy());
		result.add(s);
		return new FrontierStackImpl(result);
	}

	public FrontierStack restore() {
		final List<Stack> result = new ArrayList<>(all);
		final Stack openedBar = result.get(result.size() - 2);
		final Stack lastStack = result.get(result.size() - 1);
		result.set(result.size() - 2, openedBar.addEnvelop(lastStack.current));
		result.remove(result.size() - 1);
		final Stack s = new Stack(openedBar.current.copy());
		result.add(s);
		return new FrontierStackImpl(result);
	}

	public FrontierStack closeBar() {
		final List<Stack> result = new ArrayList<>(all);
		final Stack openedBar = result.get(result.size() - 2);
		final Stack lastStack = result.get(result.size() - 1);
		final Stack merge = openedBar.addEnvelop(lastStack.current);
		result.set(result.size() - 2, new Stack(merge.envelop));
		result.remove(result.size() - 1);
		return new FrontierStackImpl(result);
	}

	public FrontierStackImpl copy() {
		// return new FrontierStackImpl(all);
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "nb=" + all.size() + " " + getLast().toString();
	}

}
