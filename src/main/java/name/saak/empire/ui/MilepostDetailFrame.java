package name.saak.empire.ui;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This frame is used to display the game board
 */
@Component
public class MilepostDetailFrame extends JFrame {

	private static final long serialVersionUID = 202409011710L;
	private MilepostDetailPanel detailPanel;

	/**
	 * Create the frame.
	 */
	@Autowired
	public MilepostDetailFrame(MilepostDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
		setTitle("Milepost");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);

		scrollPane.setViewportView(detailPanel);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(50);
		scrollPane.getVerticalScrollBar().setUnitIncrement(25);
	}

	public void setMilepostLocation(Point pc, boolean show) {
		detailPanel.setMilepostLocation(pc, show);
	}
}
