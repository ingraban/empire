package name.saak.empire.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import name.saak.empire.model.Milepost;
import name.saak.empire.schema.XmlSmallCity;

class CityMapperTest {

	@Test
	void testSmallCity() {
		Map<Point, Milepost> mileposts = new HashMap<>();
		CityMapper cm = new CityMapper();
		XmlSmallCity c = new XmlSmallCity();
		c.setName("SmallCity");
		c.setPositionX(0);
		c.setPositionY(0);
		c.setId("smallcity");
		cm.mapCity(mileposts, c);
		assertEquals(1, mileposts.size(), "Size of mileposts");
		assertEquals("City", mileposts.get(new Point(0, 0)).getClass().getSimpleName(), "Type of SmallCity");
	}

}
