package name.saak.empire.util;

import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.springframework.stereotype.Component;

@Component
public class ImageRegistry {
	private HashMap<String, ImageIcon> icons = new HashMap<>();

	/**
	 * 
	 * @param location of the image to load from the resources
	 * @return
	 */
	public ImageIcon getIcon(String location) {
		if (icons.containsKey(location)) return icons.get(location);

		URL imageURL = getClass().getResource("/icons/" + location + ".iconset/icon_256x256.png");
		icons.put(location, new ImageIcon(imageURL));

		return icons.get(location);
	}
}
