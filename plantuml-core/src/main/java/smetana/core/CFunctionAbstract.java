
package smetana.core;

public abstract class CFunctionAbstract extends UnsupportedC implements CFunction {

	final private String name;

	public CFunctionAbstract(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
