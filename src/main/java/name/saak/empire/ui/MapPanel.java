package name.saak.empire.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import name.saak.empire.model.Gameboard;

@Data
@EqualsAndHashCode(callSuper = true)
@Component
final class MapPanel extends JPanel {
	private static final long serialVersionUID = 202408202310L;
	/**
	 * 
	 */
	private double zoomFactor = 1.0f;

	private transient Gameboard board;

	/**
	 * @param mapFrame
	 */
	@Autowired
	MapPanel(Gameboard board) {
		this.board = board;
		setDoubleBuffered(true);
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

		board.getMileposts().stream().forEach(m -> m.paint(g2, board));
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = board.getDimension();

		d.height = (int) (d.height * 25 * zoomFactor) + 50;
		d.width = (int) (d.width * 50 * zoomFactor) + 25;
		return d;
	}
}