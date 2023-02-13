package net.sourceforge.plantuml.core;

// Remove CmapData and Dimension2D
// Merge CucaDiagramFileMakerResult
/**
 * Information about a generated image for a diagram.
 * For some diagrams, there are some position information about elements
 * from the diagram. In that case, the method <code>containsCMapData()</code> returns
 * <code>true</code> and you can retrieve those information using
 * <code>getCMapData()</code> method.
 * 
 * @author Arnaud Roques
 * 
 */
public interface ImageData {

	/**
	 * Width in pixel of the image.
	 */
	public int getWidth();

	/**
	 * Height in pixel of the image.
	 */
	public int getHeight();

	/**
	 * Indicates if the image has some position information.
	 * @return <code>true</code> if the image has position information.
	 */
	public boolean containsCMapData();

	/**
	 * Return position information as a CMap formated string.
	 * For example, if you call this method with <code>nameId</code>
	 * set to "foo_map", you will get something like:
	 * 
	 * <pre>
	 * &lt;map id="foo_map" name="foo_map"&gt;
	 * &lt;area shape="rect" id="..." href="..." title="..." alt="" coords="64,68,93,148"/&gt;
	 * &lt;/map&gt;
	 * </pre>
	 * 
	 * @param nameId the id to be used in the cmap data string.
	 */
	public String getCMapData(String nameId);
	
	public String getWarningOrError();
	
	public int getStatus();


}
