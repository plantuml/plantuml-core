package net.sourceforge.plantuml.security;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.FileUtils;

/**
 * Secure replacement for java.net.URL.
 * <p>
 * This class should be used instead of java.net.URL.
 * <p>
 * This class does some control access and manages access-tokens via URL. If a
 * URL contains a access-token, similar to a user prefix, SURL loads the
 * authorization config for this user-token and passes the credentials to the
 * host.
 * <p>
 * Example:<br/>
 * 
 * <pre>
 *     SURL url = SURL.create ("https://jenkins-access@jenkins.mycompany.com/api/json")
 * </pre>
 * 
 * The {@code jenkins-access} will checked against the Security context access
 * token configuration. If a configuration exists for this token name, the token
 * will be removed from the URL and the credentials will be added to the
 * headers. If the token is not found, the URL remains as it is and no separate
 * authentication will be performed.
 * <p>
 * TODO: Some methods should be moved to a HttpClient implementation, because
 * SURL is not the valid class to manage it. <br/>
 * TODO: BAD_HOSTS implementation should be reviewed and moved to HttpClient
 * implementation with a circuit-breaker. <br/>
 * TODO: Token expiration with refresh should be implemented in future. <br/>
 */
public class SURL {

	/**
	 * Indicates, that we have no authentication to access the URL.
	 */
	public static final String WITHOUT_AUTHENTICATION = SecurityUtils.NO_CREDENTIALS;

	/**
	 * Internal URL, maybe cleaned from user-token.
	 */
	private final URL internal;

	/**
	 * Assigned credentials to this URL.
	 */
	private final String securityIdentifier;

	private SURL(URL url, String securityIdentifier) {
		this.internal = Objects.requireNonNull(url);
		this.securityIdentifier = Objects.requireNonNull(securityIdentifier);
	}

	/**
	 * Create a secure URL from a String.
	 * <p>
	 * The url must be http or https. Return null in case of error or if
	 * <code>url</code> is null
	 * 
	 * @param url plain url starting by http:// or https//
	 * @return the secure URL or null
	 */
	public static SURL create(String url) {
		if (url == null)
			return null;

		if (url.startsWith("http://") || url.startsWith("https://"))
			try {
				return create(new URL(url));
			} catch (MalformedURLException e) {
				Logme.error(e);
			}
		return null;
	}

	/**
	 * Create a secure URL from a <code>java.net.URL</code> object.
	 * <p>
	 * It takes into account credentials.
	 * 
	 * @param url
	 * @return the secure URL
	 * @throws MalformedURLException if <code>url</code> is null
	 */
	public static SURL create(URL url) throws MalformedURLException {
		if (url == null)
			throw new MalformedURLException("URL cannot be null");

			return new SURL(url, WITHOUT_AUTHENTICATION);
	}

	public InputStream openStream() {
	try {
		return internal.openStream();
	} catch (IOException e) {
		System.err.println("SURL::openStream " + e);
		return null;
	}
}
public byte[] getBytes() {
	final InputStream is = openStream();
	if (is != null)
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FileUtils.copyInternal(is, baos, true);
			return baos.toByteArray();
		} catch (IOException e) {
			System.err.println("SURL::getBytes " + e);
		}
	return null;
}

	public BufferedImage readRasterImageFromURL() {
		if (isUrlOk())
			try {
				final byte[] bytes = getBytes();
				if (bytes == null || bytes.length == 0)
					return null;
				final ImageIcon tmp = new ImageIcon(bytes);
				return SecurityUtils.readRasterImage(tmp);
			} catch (Exception e) {
				Logme.error(e);
			}
		return null;
	}

	/**
	 * Check SecurityProfile to see if this URL can be opened.
	 */
	private boolean isUrlOk() {
			return true;
	}

}
