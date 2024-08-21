package name.saak.empire.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.stereotype.Component;

/**
 * This frame is used to display the game board
 */
@Component
public class MapFrame extends JFrame {

	private static final long serialVersionUID = 202408201824L;

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private float zoomFaktor = 1.5f;
	private JLabel lblHalloWelt;
	private JLabel lblImage;

	/**
	 * Create the frame.
	 */
	public MapFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ImageIcon ii = null;

		String imgLocation = "/icons/32/zoom_fit.png";
		URL imageURL = getClass().getResource(imgLocation);
		ii = new ImageIcon(imageURL);

		setIconImage(ii.getImage());
		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);

		panel = new JPanel() {
			private static final long serialVersionUID = 202408202310L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.scale(zoomFaktor, zoomFaktor);
			}

			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = (int) (d.height * zoomFaktor);
				d.width = (int) (d.width * zoomFaktor);
				return d;
			}
		};
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
//		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				Rectangle r = scrollPane.getViewport().getViewRect();
				// r.translate(e.getX()/2,e.getY()/2);

				zoomFaktor += (e.getPreciseWheelRotation() > 0) ? 0.1 : -0.1;

//				scrollPane.getViewport().setViewSize(new Dimension(WIDTH, HEIGHT));
//				;

				panel.revalidate();
				System.out.println(r);

//				scrollPane.getViewport().scrollRectToVisible(r);
			}

		});
		scrollPane.setViewportView(panel);

		lblHalloWelt = new JLabel("Hallo Welt! Ich brauche einen sehr langen Text");
		panel.add(lblHalloWelt);

		lblImage = new JLabel();
		lblImage.setIcon(ii);
		panel.add(lblImage);
	}

}
