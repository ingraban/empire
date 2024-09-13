package name.saak.empire.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import name.saak.empire.model.SmallCity;
import name.saak.empire.model.Gameboard;
import name.saak.empire.model.Milepost;

@EqualsAndHashCode(callSuper = true)
@Component
final class MilepostDetailPanel extends JPanel {
	private static final long serialVersionUID = 202409011650L;

	private transient Gameboard board;
	private JTextField fieldPositionX;
	private JTextField fieldPositionY;
	private JTextField fieldType;
	private JLabel lblName;
	private JTextField fieldName;
	private JTable tblLoads;

	/**
	 * @param mapFrame
	 */
	@Autowired
	MilepostDetailPanel(Gameboard board) {
		this.board = board;
		setBorder(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblCoordinate = new JLabel("Coordinate");
		GridBagConstraints gbc_lblCoordinate = new GridBagConstraints();
		gbc_lblCoordinate.insets = new Insets(5, 5, 5, 5);
		gbc_lblCoordinate.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinate.gridx = 0;
		gbc_lblCoordinate.gridy = 0;
		add(lblCoordinate, gbc_lblCoordinate);

		fieldPositionX = new JTextField();
		fieldPositionX.setEditable(false);
		fieldPositionX.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbcfieldPositionX = new GridBagConstraints();
		gbcfieldPositionX.insets = new Insets(0, 0, 5, 5);
		gbcfieldPositionX.fill = GridBagConstraints.HORIZONTAL;
		gbcfieldPositionX.gridx = 1;
		gbcfieldPositionX.gridy = 0;
		add(fieldPositionX, gbcfieldPositionX);
		fieldPositionX.setColumns(5);

		fieldPositionY = new JTextField();
		fieldPositionY.setHorizontalAlignment(SwingConstants.TRAILING);
		fieldPositionY.setEditable(false);
		GridBagConstraints gbc_fieldPositionY = new GridBagConstraints();
		gbc_fieldPositionY.insets = new Insets(0, 0, 5, 0);
		gbc_fieldPositionY.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldPositionY.gridx = 2;
		gbc_fieldPositionY.gridy = 0;
		add(fieldPositionY, gbc_fieldPositionY);
		fieldPositionY.setColumns(5);

		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(5, 5, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 1;
		add(lblType, gbc_lblType);

		fieldType = new JTextField();
		fieldType.setEditable(false);
		GridBagConstraints gbc_fieldType = new GridBagConstraints();
		gbc_fieldType.insets = new Insets(0, 0, 5, 0);
		gbc_fieldType.gridwidth = 2;
		gbc_fieldType.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldType.gridx = 1;
		gbc_fieldType.gridy = 1;
		add(fieldType, gbc_fieldType);
		fieldType.setColumns(10);

		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(5, 5, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		add(lblName, gbc_lblName);

		fieldName = new JTextField();
		fieldName.setEditable(false);
		GridBagConstraints gbc_fieldName = new GridBagConstraints();
		gbc_fieldName.insets = new Insets(0, 0, 5, 0);
		gbc_fieldName.gridwidth = 2;
		gbc_fieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldName.gridx = 1;
		gbc_fieldName.gridy = 2;
		add(fieldName, gbc_fieldName);
		fieldName.setColumns(10);

		tblLoads = new JTable();
		tblLoads.setBorder(new TitledBorder(null, "Loads", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_tblLoads = new GridBagConstraints();
		gbc_tblLoads.insets = new Insets(5, 5, 5, 0);
		gbc_tblLoads.gridwidth = 3;
		gbc_tblLoads.fill = GridBagConstraints.BOTH;
		gbc_tblLoads.gridx = 0;
		gbc_tblLoads.gridy = 3;
		add(tblLoads, gbc_tblLoads);
	}

	public void setMilepostLocation(Point mapLocation, boolean show) {
		Milepost mp = board.getMilepostAt(mapLocation);
		if (mapLocation != null && show) getTopLevelAncestor().setVisible(true);

		// Milepost data
		if (mp == null) {
			fieldPositionX.setText("");
			fieldPositionY.setText("");
			fieldType.setText("");
			fieldName.setText("");
			return;
		}
		fieldPositionX.setText(String.format(getLocale(), "%d", mp.getLocation().x));
		fieldPositionY.setText(String.format(getLocale(), "%d", mp.getLocation().y));
		fieldType.setText(mp.getClass().getSimpleName());

		// City data
		if (!(mp instanceof SmallCity)) {
			fieldName.setText("");
			return;
		}
		fieldName.setText(((SmallCity) mp).getName());
	}

}