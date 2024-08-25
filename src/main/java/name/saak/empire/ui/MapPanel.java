package name.saak.empire.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.xml.bind.JAXBElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import name.saak.empire.model.Gameboard;
import name.saak.empire.schema.Row;
import name.saak.empire.schema.RowMilepost;

@Data
@EqualsAndHashCode(callSuper = true)
@Component
final class MapPanel extends JPanel {
	private static final long serialVersionUID = 202408202310L;
	/**
	 * 
	 */
	private double zoomFactor = 1.0f;

	private Gameboard board;
	private ImageIcon ii;

	/**
	 * @param mapFrame
	 */
	@Autowired
	MapPanel(Gameboard board) {
		this.board = board;
		setDoubleBuffered(true);

		String imgLocation = "/icons/cattle.iconset/icon_128x128.png";
		URL imageURL = getClass().getResource(imgLocation);
		ii = new ImageIcon(imageURL);
	}

	public void setZoomFactor(double zoomfactor) {
		if (zoomfactor > 5.0) zoomfactor = 5;
		if (zoomfactor < 0.49) zoomfactor = 0.49;
		this.zoomFactor = zoomfactor;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.scale(zoomFactor, zoomFactor);

		int x = 25;
		int y = 25;

		for (Row r : board.getRows()) {
			for (JAXBElement<RowMilepost> rm : r.getClearOrMountain()) {
				String name = (rm.getName() != null) ? rm.getName().toString() : "";
				if (rm.getValue() == null) continue;
				RowMilepost v = rm.getValue();
				if (v.getStart() != null) x += v.getStart() * 50;
				x += v.getOffset() * 2 * 50;
				for (int i = 0; i < v.getLength(); i++) {
					// Vancoucer
					if ((x == 325) && (y == 150)) {
						g2.setColor(Color.RED);
						g2.fillOval(x - 15, y - 15, 30, 30);
						g2.setColor(Color.BLACK);
						g2.drawString("Vancouver", x + 15 + 2, y + 4);
						g2.drawImage(ii.getImage(), x + 12, y + 12, x + 36, y + 36, 0, 0, ii.getImage().getWidth(null), ii.getImage().getHeight(null), null);
					}
					if ("mountain".equals(name)) {
//						g2.drawOval(x - 5, y - 5, 10, 10);
						g2.fillPolygon(new int[] { x, x + 10, x - 10 }, new int[] { y - 8, y + 8, y + 8 }, 3);

					} else {
//						System.out.println(name);
						g2.setColor(Color.BLACK);
						g2.fillOval(x - 5, y - 5, 10, 10);
					}
					x += 100;
				}
			}
			y += 25;
			x = 25;
		}
	}

	public Dimension getPreferredSize() {
		Dimension d = board.getDimension();

//		Dimension d = super.getPreferredSize();
		d.height = (int) (d.height * 25 * zoomFactor) + 50;
		d.width = (int) (d.width * 50 * zoomFactor) + 25;
		return d;
	}
}