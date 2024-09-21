package name.saak.empire.model;

import static name.saak.empire.util.MilepostLocator.DISTANCE;
import static name.saak.empire.util.MilepostLocator.ROW_DISTANCE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import name.saak.empire.util.MilepostLocator;

@Data
@EqualsAndHashCode(callSuper = true)
public class SmallCity extends Milepost {

	protected static final int CITY_RADIUS = (int) (DISTANCE * 0.15);

	private String name;

	private List<String> loads = new ArrayList<>();

	public SmallCity(int x, int y, String name) {
		super(x, y);
		setName(name);
	}

	@Override
	public void paint(Graphics2D g, Gameboard board) {
		Point gl = MilepostLocator.getGraphicsLocation(getLocation());

		g.setColor(Color.RED);
		g.fillOval(gl.x - CITY_RADIUS, gl.y - CITY_RADIUS, CITY_RADIUS * 2, CITY_RADIUS * 2);
		g.setColor(Color.BLACK);
		g.drawString(getName(), gl.x - g.getFontMetrics().stringWidth(getName()) / 2, gl.y + ROW_DISTANCE);
		paintLoads(g, board);
		super.paint(g, board);
	}

	protected void paintLoads(Graphics2D g, Gameboard board) {
		Point gl = MilepostLocator.getGraphicsLocation(getLocation());
		int y = gl.y + ROW_DISTANCE * 2;
		for (String load : loads) {
			g.drawString(load, gl.x - g.getFontMetrics().stringWidth(load) / 2, y);
			y += ROW_DISTANCE;
		}
	}

	public void addLoad(String name) {
		loads.add(name);
	}
}
