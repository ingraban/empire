package name.saak.empire.mapper;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBElement;
import name.saak.empire.model.Milepost;
import name.saak.empire.model.MountainMilepost;
import name.saak.empire.schema.XmlRow;
import name.saak.empire.schema.XmlRowMilepost;
import name.saak.empire.util.MilepostLocator;

@Service
public class RowMapper {

	public void mapRow(Map<Point, Milepost> mileposts, XmlRow r, int rowIndex, boolean firstRow) {
		List<JAXBElement<XmlRowMilepost>> clearOrMountain = r.getClearOrMountain();
		int column = 0;
		for (JAXBElement<XmlRowMilepost> rm : clearOrMountain) {
			column += Optional.ofNullable(rm.getValue().getStart()).orElse(0);
			if (firstRow) MilepostLocator.setOdd(column % 2 != 0);
			column += rm.getValue().getOffset() * 2;
			if ("mountain".equals(rm.getName().toString())) {
				for (int i = 0; i < rm.getValue().getLength(); i++) {
					Point l = new Point(column, rowIndex);
					mileposts.put(l, new MountainMilepost(l.x, l.y));
					column += 2;
				}
			} else {
				for (int i = 0; i < rm.getValue().getLength(); i++) {
					Point l = new Point(column, rowIndex);
					mileposts.put(l, new Milepost(l.x, l.y));
					column += 2;
				}
			}

		}
	}
}
