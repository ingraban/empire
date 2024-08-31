package name.saak.empire.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MajorCity extends City {

	public MajorCity(int x, int y, String name) {
		super(x, y, name);
	}

	@Override
	public void paint(Graphics2D g) {
		Point gl = getGraphicsLocation();

		g.setColor(Color.RED);
		g.fillPolygon(//
				new int[] { //
						gl.x, // Mitte oben
						gl.x + DISTANCE / 2, // Rechts oben
						gl.x + DISTANCE / 2, // Rechts unten
						gl.x, // Mitte unten
						gl.x - DISTANCE / 2, // Links unten
						gl.x - DISTANCE / 2 // Links oben
				}, //
				new int[] { //
						gl.y - DISTANCE / 2, //
						gl.y - DISTANCE / 4, //
						gl.y + DISTANCE / 4, //
						gl.y + DISTANCE / 2, //
						gl.y + DISTANCE / 4, //
						gl.y - DISTANCE / 4 //
				}, //
				6);
		g.setColor(Color.BLACK);
		g.drawString(getName(), gl.x - g.getFontMetrics().stringWidth(getName()) / 2, gl.y + DISTANCE / 4);
		g.fillOval(gl.x - 5, gl.y - 5, 10, 10); // Center
		g.fillOval(gl.x - 5, gl.y - 5 - DISTANCE / 2, 10, 10); // Mitte oben
		g.fillOval(gl.x - 5 + DISTANCE / 2, gl.y - 5 - DISTANCE / 4, 10, 10); // Rechts oben
		g.fillOval(gl.x - 5 + DISTANCE / 2, gl.y - 5 + DISTANCE / 4, 10, 10); // Rechts unten
		g.fillOval(gl.x - 5, gl.y - 5 + DISTANCE / 2, 10, 10); // Mitte unten
		g.fillOval(gl.x - 5 - DISTANCE / 2, gl.y - 5 - DISTANCE / 4, 10, 10); // Links unten
		g.fillOval(gl.x - 5 - DISTANCE / 2, gl.y - 5 + DISTANCE / 4, 10, 10); // Links oben
	}
}
