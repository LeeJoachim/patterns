import java.net.*;
import javax.swing.*;
import java.util.*;

public class ImageProxyTestDriver {
	ImageComponent imageComponent;
	JFrame frame = new JFrame("Album Cover Viewer");
	JMenuBar menuBar;
	JMenu menu;
	Hashtable<String, String> albums = new Hashtable<String, String>(); // not ordered

	public static void main (String[] args) throws Exception {
		ImageProxyTestDriver testDriver = new ImageProxyTestDriver();
	}

	public ImageProxyTestDriver() throws Exception {
		albums.put("A","https://upload.wikimedia.org/wikipedia/en/e/eb/Windows_95_at_first_run.png");
		albums.put("B","https://upload.wikimedia.org/wikipedia/en/e/eb/Windows_95_at_first_run.png");
		albums.put("C","https://upload.wikimedia.org/wikipedia/en/e/eb/Windows_95_at_first_run.png");

		URL initialURL = new URL((String)albums.get("B")); // default image value
		menuBar = new JMenuBar();
		menu = new JMenu("Photos");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		for (Enumeration<String> e = albums.keys(); e.hasMoreElements();) { // Enum with Hashtable
			String name = e.nextElement();
			JMenuItem menuItem = new JMenuItem(name);
			menu.add(menuItem);
			
			menuItem.addActionListener(event -> {
				imageComponent.setIcon(new ImageProxy(getAlbumUrl(event.getActionCommand())));
				frame.repaint();
			});
		}

		// set up frame and menus

		Icon icon = new ImageProxy(initialURL);
		imageComponent = new ImageComponent(icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setVisible(true);
 
	}

	URL getAlbumUrl(String name) {
		try {
			return new URL(albums.get(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
