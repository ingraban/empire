package name.saak.empire.mapper;

import java.awt.Point;
import java.util.Map;

import org.springframework.stereotype.Service;

import name.saak.empire.model.MajorCity;
import name.saak.empire.model.MediumCity;
import name.saak.empire.model.Milepost;
import name.saak.empire.model.SmallCity;
import name.saak.empire.schema.XmlLoad;
import name.saak.empire.schema.XmlSmallCity;

@Service
public class CityMapper {

	public void mapCity(Map<Point, Milepost> mileposts, XmlSmallCity xc) {
		Point location = new Point(xc.getPositionX(), xc.getPositionY());
		String name = xc.getName();
		String className = xc.getClass().getSimpleName();
		SmallCity mc; // Modell City

		switch (className) {
		case "XmlSmallCity":
			mc = new SmallCity(location.x, location.y, name);
			mileposts.put(location, mc);
			break;
		case "XmlMediumCity":
			mc = new MediumCity(location.x, location.y, name);
			mileposts.put(location, mc);
			break;
		default:
			mc = new MajorCity(location.x, location.y, name);
			mileposts.put(location, mc);
			// Major citoy consists of 7 Mileposts
			mileposts.put(new Point(location.x + 0, location.y - 2), mc); // top
			mileposts.put(new Point(location.x + 1, location.y - 1), mc); // top right
			mileposts.put(new Point(location.x + 1, location.y + 1), mc); // bottom right
			mileposts.put(new Point(location.x + 0, location.y + 2), mc); // bottom
			mileposts.put(new Point(location.x + 1, location.y + 1), mc); // bottom left
			mileposts.put(new Point(location.x - 1, location.y - 1), mc); // top left
			break;
		}

		xc.getLoad().forEach(l -> {
			String loadName = ((XmlLoad) l.getValue()).getId();
			mc.addLoad(loadName);
		});
	}
}
