package name.saak.empire.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

class MilepostLocatorTest {

	@Test
	void testUpperLeftCorner() {
		Point graphicsLocation = MilepostLocator.getGraphicsLocation(new Point(0, 0));
		assertEquals(25, graphicsLocation.x, "x-location");
		assertEquals(25, graphicsLocation.y, "y-location");
	}

	@Test
	void testSecondRowSecondColumn() {
		Point graphicsLocation = MilepostLocator.getGraphicsLocation(new Point(1, 1));
		assertEquals(75, graphicsLocation.x, "x-location");
		assertEquals(50, graphicsLocation.y, "y-location");
	}

	@Test
	void testThirdRowFirstColumn() {
		Point graphicsLocation = MilepostLocator.getGraphicsLocation(new Point(0, 2));
		assertEquals(25, graphicsLocation.x, "x-location");
		assertEquals(75, graphicsLocation.y, "y-location");
	}

}
