package name.saak.empire.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MapPanelTest {

	@Test
	void testSetZoomFactor() {
		// Arrange
		MapPanel panel = new MapPanel(null);

		// Act
		panel.setZoomFactor(0.5);
		double result1 = panel.getZoomFactor();

		panel.setZoomFactor(6.0);
		double result2 = panel.getZoomFactor();

		panel.setZoomFactor(0.3);
		double result3 = panel.getZoomFactor();

		// Assert
		assertEquals(0.5, result1, "Zoom factor should be set to 0.5");
		assertEquals(5.0, result2, "Zoom factor should be capped at 5.0");
		assertEquals(0.49, result3, "Zoom factor should be capped at 0.49");
	}
}
