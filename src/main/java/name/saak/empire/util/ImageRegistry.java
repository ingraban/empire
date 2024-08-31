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

		URL imageURL = getClass().getResource(location);
		icons.put(location, new ImageIcon(imageURL));

		return icons.get(location);
	}
}
