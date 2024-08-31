package name.saak.empire.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediumCity extends City {

	public MediumCity(int x, int y, String name) {
		super(x, y, name);
	}

	@Override
	public void paint(Graphics2D g) {
		Point gl = getGraphicsLocation();

		g.setColor(Color.RED);
		g.fillRect(gl.x - 15, gl.y - 15, 30, 30);
		super.paint(g);
	}
}
