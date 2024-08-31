package name.saak.empire.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class City extends Milepost {

	protected static final int CITY_RADIUS = (int) (DISTANCE * 0.15);

	private String name;

	private List<Load> loads = new ArrayList<>();

	public City(int x, int y, String name) {
		super(x, y);
		setName(name);
	}

	@Override
	public void paint(Graphics2D g) {
		Point gl = getGraphicsLocation();

		g.setColor(Color.RED);
		g.fillOval(gl.x - CITY_RADIUS, gl.y - CITY_RADIUS, CITY_RADIUS * 2, CITY_RADIUS * 2);
		g.setColor(Color.BLACK);
		g.drawString(getName(), gl.x - g.getFontMetrics().stringWidth(getName()) / 2, gl.y + DISTANCE / 4);
		super.paint(g);
	}
}
