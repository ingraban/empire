package name.saak.empire.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import name.saak.empire.model.Milepost;
import name.saak.empire.model.SmallCity;
import name.saak.empire.schema.Game;
import name.saak.empire.schema.XmlSmallCity;

class CityMapperTest {

	private static final String BUFFALO = """
			<game xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="eurorails.xsd">
			<name>Empire Builder</name>
			<map>
				<row>
					<mountain start="3" length="6"/>
					<clear length="22"/>
					<mountain length="2"/>
				</row>
			</map>
				<loads>
					<load count="4" icon="machinery.png" id="machinery" name="Maschinen"/>
				</loads>
			<cities>
				<small-city id="buffalo" name="Buffalo" position-x="50" position-y="21">
					<load>machinery</load>
				</small-city>
			</cities>
				<cards>
					<demand-card id="3">
						<demand money="23" city="calgary" load="sugar"/>
						<demand money="11" city="winnipeg" load="copper"/>
						<demand money="53" city="calgary" load="wood"/>
					</demand-card>
				</cards>
			</game>""";

	private static Unmarshaller jaxbUnmarshaller;

	@BeforeEach
	void setUp() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlSmallCity.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	}

	@Test
	void testSmallCity() throws JAXBException {
		Game game = (Game) jaxbUnmarshaller.unmarshal(new StringReader(BUFFALO));

		Map<Point, Milepost> mileposts = new HashMap<>();
		CityMapper cm = new CityMapper();
		cm.mapCity(mileposts, game.getCities().getSmallCityOrMediumCityOrMajorCity().get(0));
		assertEquals(1, mileposts.size(), "Size of mileposts");
		assertEquals("SmallCity", mileposts.get(new Point(50, 21)).getClass().getSimpleName(), "Type of SmallCity");
	}

	@Test
	void testLoad() throws JAXBException {
		Game game = (Game) jaxbUnmarshaller.unmarshal(new StringReader(BUFFALO));

		Map<Point, Milepost> mileposts = new HashMap<>();
		CityMapper cm = new CityMapper();
		cm.mapCity(mileposts, game.getCities().getSmallCityOrMediumCityOrMajorCity().get(0));
		SmallCity sc = (SmallCity) mileposts.get(new Point(50, 21));
		assertEquals(1, sc.getLoads().size(), "Size of loads");
	}
}
