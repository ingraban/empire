package name.saak.empire.mapper;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import name.saak.empire.model.Load;
import name.saak.empire.schema.XmlLoad;
import name.saak.empire.util.ImageRegistry;

@Service
public class LoadMapper {

	private ImageRegistry imageRegistry;

	@Autowired
	public LoadMapper(ImageRegistry imageRegistry) {
		this.imageRegistry = imageRegistry;
	}

	public Load mapLoad(XmlLoad xl) {
		Load l = new Load();
		ImageIcon icon = imageRegistry.getIcon("/icons/" + xl.getId() + ".iconset/icon_512x512.png");

		l.setIcon((icon == null) ? null : icon);
		l.setInitialCount(xl.getCount());
		l.setName(xl.getName());
		return l;
	}

}
