package name.saak.empire.mapper;

import java.util.Map;

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
		ImageIcon icon = imageRegistry.getIcon(xl.getId());

		l.setId(xl.getId());
		l.setIcon((icon == null) ? null : icon);
		l.setInitialCount(xl.getCount());
		l.setName(xl.getName());
		return l;
	}

	public void mapLoad(Map<String, Load> loads, XmlLoad xmlload) {
		Load l = mapLoad(xmlload);
		loads.put(l.getId(), l);
	}

}
