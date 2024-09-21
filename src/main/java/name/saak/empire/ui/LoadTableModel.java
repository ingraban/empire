package name.saak.empire.ui;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class LoadTableModel implements TableModel {

	private String[] loads;

	public LoadTableModel(List<String> loads) {
		this.loads = loads.toArray(String[]::new);
	}

	@Override
	public int getRowCount() {
		return loads.length;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return "Load";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return loads[rowIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
	}

}
