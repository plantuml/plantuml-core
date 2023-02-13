
package smetana.core;

public interface __ptr__  {

	public boolean isSameThan(__ptr__ other);

	public __ptr__ castTo(Class dest);
	
	public Object getTheField(OFFSET bytes);
	
	public __ptr__ unsupported();
	

}
