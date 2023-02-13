package net.sourceforge.plantuml.tim;

public class StringEater extends Eater {

	public StringEater(String s) {
		super(s, null);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		throw new UnsupportedOperationException();
	}

}
