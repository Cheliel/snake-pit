package GUI.solo;


import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.database.History;

public class IndexScoreBoard extends AbstractTableModel {
	
	private static final long serialVersionUID = -5987190428679267731L;
	public List<History> histories;
	
	public IndexScoreBoard(List<History> histories) {
		this.histories = histories;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return histories.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(rowIndex == 0 && columnIndex == 0) {
			return "Pseudo";
		}
		
		if(rowIndex == 0 && columnIndex == 1) {
			return "Score";
		}
		
		History history = histories.get(rowIndex);
		if(columnIndex == 0) { return history.getPseudo();}
		else if (columnIndex == 1) {return history.getBerries();}
		else { return "smth vent rong"; }
	}

}
