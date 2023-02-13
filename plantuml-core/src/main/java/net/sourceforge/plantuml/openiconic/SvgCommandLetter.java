package net.sourceforge.plantuml.openiconic;

import net.sourceforge.plantuml.StringUtils;

public class SvgCommandLetter implements SvgCommand {

	final private char letter;

	public SvgCommandLetter(String letter) {
		if (letter.matches("[mlhvzsacqtMLHVZSACQT]") == false) 
			throw new IllegalArgumentException(letter);
		
		this.letter = letter.charAt(0);
	}

	@Override
	public String toString() {
		return " " + letter;
	}

	public String toSvg() {
		return "" + letter;
	}

	public int argumentNumber() {
		switch (StringUtils.goLowerCase(letter)) {
		case 'm':
		case 'l':
		case 't':
			return 2;
		case 'h':
		case 'v':
			return 1;
		case 'z':
			return 0;
		case 'c':
			return 6;
		case 'q':
		case 's':
			return 4;
		case 'a':
			return 7;
		}
		throw new UnsupportedOperationException("" + letter);
	}

	public boolean isUpperCase() {
		return Character.isUpperCase(letter);
	}

	public boolean is(char c) {
		return this.letter == c;
	}

	public char getLetter() {
		return letter;
	}

	public SvgCommandLetter implicit() {
		// https://stackoverflow.com/questions/29251389/svg-path-spec-moveto-and-implicit-lineto
		if (letter == 'm')
			return new SvgCommandLetter("l");

		if (letter == 'M')
			return new SvgCommandLetter("L");

		return this;
	}
}
