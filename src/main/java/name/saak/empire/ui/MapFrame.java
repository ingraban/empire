package name.saak.empire.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import name.saak.empire.util.MilepostLocator;

/**
 * This frame is used to display the game board
 */
@Component
public class MapFrame extends JFrame {

	private static final long serialVersionUID = 202408201824L;

	private transient Logger logger = LoggerFactory.getLogger(MapFrame.class);

	private JPanel contentPane;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	@Autowired
	public MapFrame(MapPanel mapPanel, MilepostDetailFrame milepostDetailFrame) {
		setTitle("Map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);

		FlowLayout flowLayout = (FlowLayout) mapPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
//		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPane.setViewportView(mapPanel);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(50);
		scrollPane.getVerticalScrollBar().setUnitIncrement(25);

		scrollPane.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				logger.trace("{} & {} = {} ({}) / rotation = {}", e.getModifiersEx(), InputEvent.ALT_DOWN_MASK, e.getModifiersEx() & InputEvent.ALT_DOWN_MASK,
						"" + e.isShiftDown(), e.getPreciseWheelRotation());

				if ((e.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != InputEvent.ALT_DOWN_MASK) return;

				mapPanel.setZoomFactor(mapPanel.getZoomFactor() + ((e.getPreciseWheelRotation() > 0) ? 0.1 : -0.1));
				mapPanel.revalidate();
			}

		});

		mapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double zoomFactor = mapPanel.getZoomFactor();
				Point pz = e.getPoint();
				Point pc = MilepostLocator.getMapLocation(new Point((int) (pz.x / zoomFactor), (int) (pz.y / zoomFactor)));

				if (pc == null) return;

				logger.trace("Mouse click Zoom({}, {}) with factor{} -> Coordinate({}, {})", pz.x, pz.y, zoomFactor, pc.x, pc.y);
				milepostDetailFrame.setMilepostLocation(pc, true);
			}
		});
		mapPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				double zoomFactor = mapPanel.getZoomFactor();
				Point pz = e.getPoint();
				Point pc = MilepostLocator.getMapLocation(new Point((int) (pz.x / zoomFactor), (int) (pz.y / zoomFactor)));

				if (pc == null) return;

				logger.trace("Mouse click Zoom({}, {}) with factor{} -> Coordinate({}, {})", pz.x, pz.y, zoomFactor, pc.x, pc.y);
				milepostDetailFrame.setMilepostLocation(pc, false);
			}
		});
	}

}
