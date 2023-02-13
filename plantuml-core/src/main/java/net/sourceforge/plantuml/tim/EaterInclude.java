package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.preproc2.PreprocessorIncludeStrategy;
import net.sourceforge.plantuml.text.StringLocated;

public class EaterInclude extends Eater {

	private String location;
	private PreprocessorIncludeStrategy strategy = PreprocessorIncludeStrategy.DEFAULT;

	public EaterInclude(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!include");
		final char peekChar = peekChar();
		if (peekChar == 'u') {
			checkAndEatChar("url");
		} else if (peekChar == '_') {
			checkAndEatChar("_");
			final char peekChar2 = peekChar();
			if (peekChar2 == 'm') {
				checkAndEatChar("many");
				this.strategy = PreprocessorIncludeStrategy.MANY;
			} else {
				checkAndEatChar("once");
				this.strategy = PreprocessorIncludeStrategy.ONCE;
			}
		}
		skipSpaces();
		this.location = context.applyFunctionsAndVariables(memory, getLineLocation(), this.eatAllToEnd());
//		final TValue value = eatExpression(context, memory);
//		this.location = value.toString();

	}

	public final String getLocation() {
		return location;
	}

	public final PreprocessorIncludeStrategy getPreprocessorIncludeStrategy() {
		return strategy;
	}

}
