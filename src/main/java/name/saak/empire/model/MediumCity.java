package name.saak.empire.model;

import static name.saak.empire.util.MilepostLocator.DISTANCE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import lombok.Data;
import lombok.EqualsAndHashCode;
import name.saak.empire.util.MilepostLocator;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediumCity extends City {

	public MediumCity(int x, int y, String name) {
		super(x, y, name);
	}

	@Override
	public void paint(Graphics2D g) {
		Point gl = MilepostLocator.getGraphicsLocation(getLocation());

		g.setColor(Color.RED);
		g.fillRect((int) (gl.x - DISTANCE * 0.15), (int) (gl.y - DISTANCE * 0.15), (int) (DISTANCE * 0.3), (int) (DISTANCE * 0.3));
		super.paint(g);
	}
}
