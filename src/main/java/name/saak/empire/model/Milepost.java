package name.saak.empire.model;

import java.awt.Dimension;
import java.awt.Point;

public class Milepost {

	private Point location;
	private Dimension size;

	public Milepost(int x, int y) {
		location = new Point(x, y);
		size = new Dimension(10, 10);
//
//		LineBorder lb = new LineBorder();
//		setBorder(lb);
	}

}