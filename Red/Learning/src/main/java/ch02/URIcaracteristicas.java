package ch02;

import java.net.URI;
import java.net.URISyntaxException;

public class URIcaracteristicas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URI uri = new URI("https://www.packtpub.com/books/content/support");
			displayURI(uri);
		} catch (URISyntaxException ex) {
			// Handle exceptions
		}

	}

	private static void displayURI(URI uri) {
		System.out.println("getAuthority: " + uri.getAuthority());
		System.out.println("getScheme: " + uri.getScheme());
		System.out.println("getSchemeSpecificPart: " + uri.getSchemeSpecificPart());
		System.out.println("getHost: " + uri.getHost());
		System.out.println("getPath: " + uri.getPath());
		System.out.println("getQuery: " + uri.getQuery());
		System.out.println("getFragment: " + uri.getFragment());
		System.out.println("getUserInfo: " + uri.getUserInfo());
		System.out.println("normalize: " + uri.normalize());
	}
}
