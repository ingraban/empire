package name.saak.empire.model;

import static name.saak.empire.util.MilepostLocator.COLUMN_DISTANCE;
import static name.saak.empire.util.MilepostLocator.DISTANCE;
import static name.saak.empire.util.MilepostLocator.MILEPOST_RADIUS;
import static name.saak.empire.util.MilepostLocator.ROW_DISTANCE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import lombok.Data;
import lombok.EqualsAndHashCode;
import name.saak.empire.util.MilepostLocator;

@Data
@EqualsAndHashCode(callSuper = true)
public class MajorCity extends SmallCity {

	public MajorCity(int x, int y, String name) {
		super(x, y, name);
	}

	@Override
	public void paint(Graphics2D g, Gameboard board) {
		Point gl = MilepostLocator.getGraphicsLocation(getLocation());

		g.setColor(Color.RED);
		g.fillPolygon(//
				new int[] { //
						gl.x, // Mitte oben
						gl.x + COLUMN_DISTANCE, // Rechts oben
						gl.x + COLUMN_DISTANCE, // Rechts unten
						gl.x, // Mitte unten
						gl.x - COLUMN_DISTANCE, // Links unten
						gl.x - COLUMN_DISTANCE // Links oben
				}, //
				new int[] { //
						gl.y - ROW_DISTANCE * 2, //
						gl.y - ROW_DISTANCE, //
						gl.y + ROW_DISTANCE, //
						gl.y + ROW_DISTANCE * 2, //
						gl.y + ROW_DISTANCE, //
						gl.y - ROW_DISTANCE //
				}, //
				6);
		g.setColor(Color.BLACK);
		g.drawString(getName(), gl.x - g.getFontMetrics().stringWidth(getName()) / 2, gl.y + ROW_DISTANCE);
		g.fillOval(gl.x - MILEPOST_RADIUS, gl.y - MILEPOST_RADIUS, MILEPOST_RADIUS * 2, MILEPOST_RADIUS * 2); // Center
		g.fillOval(gl.x - MILEPOST_RADIUS, gl.y - 5 - ROW_DISTANCE * 2, MILEPOST_RADIUS * 2, MILEPOST_RADIUS * 2); // Mitte oben
		g.fillOval(gl.x - MILEPOST_RADIUS + DISTANCE / 2, gl.y - MILEPOST_RADIUS - DISTANCE / 4, MILEPOST_RADIUS * 2, MILEPOST_RADIUS * 2); // Rechts oben
		g.fillOval(gl.x - MILEPOST_RADIUS + DISTANCE / 2, gl.y - MILEPOST_RADIUS + DISTANCE / 4, MILEPOST_RADIUS * 2, MILEPOST_RADIUS * 2); // Rechts unten
		g.fillOval(gl.x - MILEPOST_RADIUS, gl.y - 5 + ROW_DISTANCE * 2, MILEPOST_RADIUS * 2, MILEPOST_RADIUS * 2); // Mitte unten
		g.fillOval(gl.x - MILEPOST_RADIUS - DISTANCE / 2, gl.y - MILEPOST_RADIUS - DISTANCE / 4, MILEPOST_RADIUS * 2, MILEPOST_RADIUS * 2); // Links unten
		g.fillOval(gl.x - MILEPOST_RADIUS - DISTANCE / 2, gl.y - MILEPOST_RADIUS + DISTANCE / 4, MILEPOST_RADIUS * 2, MILEPOST_RADIUS * 2); // Links oben
	}
}
