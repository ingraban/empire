package name.saak.empire.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MilepostLocatorTest {
	@BeforeEach
	void setEven() {
		MilepostLocator.setOdd(false);
	}

	@Test
	void testGraphicsLocationUpperLeftCorner() {
		Point graphicsLocation = MilepostLocator.getGraphicsLocation(new Point(0, 0));
		assertEquals(25, graphicsLocation.x, "x-location");
		assertEquals(25, graphicsLocation.y, "y-location");
	}

	@Test
	void testGraphicsLocationSecondRowSecondColumn() {
		Point graphicsLocation = MilepostLocator.getGraphicsLocation(new Point(1, 1));
		assertEquals(75, graphicsLocation.x, "x-location");
		assertEquals(50, graphicsLocation.y, "y-location");
	}

	@Test
	void testGraphicsLocationThirdRowFirstColumn() {
		Point graphicsLocation = MilepostLocator.getGraphicsLocation(new Point(0, 2));
		assertEquals(25, graphicsLocation.x, "x-location");
		assertEquals(75, graphicsLocation.y, "y-location");
	}

	///// MapLocation

	@Test
	void testMapLocationUpperLeftCornerOdd() {
		MilepostLocator.setOdd(true);
		Point mapLocation = MilepostLocator.getMapLocation(new Point(25, 25));
		assertNull(mapLocation);

		mapLocation = MilepostLocator.getMapLocation(new Point(75, 25));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(0, mapLocation.y, "y-location");
	}

	@Test
	void testMapLocationUpperLeftCorner() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(25, 25));
		assertEquals(0, mapLocation.x, "x-location");
		assertEquals(0, mapLocation.y, "y-location");
	}

	@Test
	void testMapLocationSecondRowSecondColumn() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(75, 50));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");
	}

	@Test
	void testMapLocationThirdRowFirstColumn() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(25, 75));
		assertEquals(0, mapLocation.x, "x-location");
		assertEquals(2, mapLocation.y, "y-location");
	}

	@Test
	void testMapLocationEnvironmentLeft() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(51, 50));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(50, 50));
		assertNull(mapLocation);
	}

	@Test
	void testMapLocationEnvironmentRight() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(100, 50));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(101, 50));
		assertNull(mapLocation);
	}

	@Test
	void testMapLocationEnvironmentTop() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(75, 38));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(75, 37));
		assertNull(mapLocation);
	}

	@Test
	void testMapLocationEnvironmentBottom() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(75, 62));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(75, 63));
		assertNull(mapLocation);
	}

	@Test
	void testMapLocationEnvironmentTopLeft() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(51, 38));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(50, 37));
		assertEquals(0, mapLocation.x, "x-location");
		assertEquals(0, mapLocation.y, "y-location");
	}

	@Test
	void testMapLocationEnvironmentTopRight() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(100, 38));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(101, 37));
		assertEquals(2, mapLocation.x, "x-location");
		assertEquals(0, mapLocation.y, "y-location");
	}

	@Test
	void testMapLocationEnvironmentBottomLeft() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(51, 62));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(50, 63));
		assertEquals(0, mapLocation.x, "x-location");
		assertEquals(2, mapLocation.y, "y-location");
	}

	@Test
	void testMapLocationEnvironmentBottomRight() {
		Point mapLocation = MilepostLocator.getMapLocation(new Point(100, 62));
		assertEquals(1, mapLocation.x, "x-location");
		assertEquals(1, mapLocation.y, "y-location");

		mapLocation = MilepostLocator.getMapLocation(new Point(101, 63));
		assertEquals(2, mapLocation.x, "x-location");
		assertEquals(2, mapLocation.y, "y-location");
	}
}
