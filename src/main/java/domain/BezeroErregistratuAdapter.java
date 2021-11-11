package domain;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class BezeroErregistratuAdapter extends AbstractTableModel{
	private Vector<Apustua> apus;
	
	public BezeroErregistratuAdapter(Vector<Apustua> p) {
		apus=p;
	}
	
	
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return apus.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Apustua ap=apus.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return ap.getIdentifikadorea();
		case 1:
			return ap.getKuotaTotala();
		case 2:
			return ap.getPronostikoKop();
		case 3:
			return ap.getAsmatutakoKop();
			default:
				return null;
		}
	}
};