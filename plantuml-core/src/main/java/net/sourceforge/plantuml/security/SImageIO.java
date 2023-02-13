package net.sourceforge.plantuml.security;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class SImageIO {


	public static void write(RenderedImage image, String format, OutputStream os) throws IOException {
		javax.imageio.ImageIO.write(image, format, os);
	}


	public static BufferedImage read(InputStream is) throws IOException {
		return javax.imageio.ImageIO.read(is);
	}

	public static BufferedImage read(byte[] bytes) throws IOException {
		return javax.imageio.ImageIO.read(new ByteArrayInputStream(bytes));
	}


}
