package name.saak.empire.model;

import javax.swing.ImageIcon;

import lombok.Data;

@Data
public class Load {
	/**
	 * internal id
	 */
	private String id;
	/**
	 * Name in UI
	 */
	private String name;
	/**
	 * Maximum number of loads of this type (id)
	 */
	private int initialCount;
	/**
	 * Description for the icon
	 */
	private ImageIcon icon;
}
