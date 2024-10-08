package name.saak.empire.model;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import name.saak.empire.schema.Load;
import name.saak.empire.util.ImageRegistry;

/**
 * Diese Klasse speichert die Informationen zu Beladungen
 * 
 * @author saak
 */
@Component
public class LoadsStore {

	private ImageRegistry imageRegistry;

	private HashMap<String, Load> loads = new HashMap<String, Load>();

	@Autowired
	public LoadsStore(ImageRegistry imageRegistry) {
		this.imageRegistry = imageRegistry;
	}

	/**
	 * eine neue Beladung hinzufügen
	 * 
	 * @param load
	 */
	public void add(Load load) {
		String id = load.getId();
		loads.computeIfAbsent(id, k -> load);
	}

	/**
	 * Liefert eine Beladung
	 * 
	 * @param id
	 * @return null, wenn die Beladung nicht existiert
	 */
	public Load getLoad(String id) {
		return loads.get(id);
	}

	public Image getImage(Load load) {
		load = loads.get(load.getId());
		if (load == null) return null;

		ImageIcon icon = imageRegistry.getIcon("/icons/" + load.getId() + ".iconset/icon_512x512.png");
		return (icon == null) ? null : icon.getImage();
	}
}
