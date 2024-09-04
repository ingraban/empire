package name.saak.empire.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import name.saak.empire.model.Load;
import name.saak.empire.schema.XmlLoad;
import name.saak.empire.util.ImageRegistry;

class LoadMapperTest {

	private static ImageRegistry imageRegistry;
	private static LoadMapper loadMapper;

	@BeforeAll
	static void setup() {
		imageRegistry = new ImageRegistry();
		loadMapper = new LoadMapper(imageRegistry);
	}

	@Test
	void testSchemaToModelMapper() {
		XmlLoad xl = new XmlLoad();
		xl.setName("Öl");
		xl.setId("oil");
		xl.setIcon("oil.png");
		xl.setCount(4);

		Load ml = loadMapper.mapLoad(xl);
		assertEquals("oil", ml.getId());
		assertEquals("Öl", ml.getName());
		assertEquals(4, ml.getInitialCount());
		assertEquals(imageRegistry.getIcon("/icons/oil.iconset/icon_512x512.png"), ml.getIcon());
	}

}
