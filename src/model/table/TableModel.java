package model.table;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import model.PostObject;

public class TableModel extends AbstractTableModel {

    private static String[] columnNames;
    private final LinkedList<PostObject> list;

    private TableModel() {
        list = new LinkedList<PostObject>();
    }
    
    public TableModel(LinkedList<PostObject> posts){
    	list = posts;
    }

    @Override
    public String getColumnName(int columnIndex){
    	return columnNames[columnIndex];
    }
    
    public void setColumnName(int i, String name) {
        columnNames[i] = name;
        fireTableStructureChanged();
    }
    
    public void setColumnName(String[] names){
    	columnNames = names;
    }
    
    public void addElement(PostObject e) {
        // Adds the element in the last position in the list
        list.add(e);
        fireTableRowsInserted(list.size()-1, list.size()-1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return list.get(rowIndex).getId();
            case 1: return list.get(rowIndex).getTitle();
            case 2: return list.get(rowIndex).getAuthor();
            case 3: return list.get(rowIndex).getCategory();
            case 4: return list.get(rowIndex).getDate();
            case 5: return list.get(rowIndex).getUrl();
            case 6: return list.get(rowIndex).getDeleteUrl();
            case 7: return list.get(rowIndex).getWebUrl();

        }
        return null;
    }

}