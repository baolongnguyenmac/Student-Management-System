package com.swing.table;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class TableMaker {
    public static DefaultTableModel getTable(ArrayList<ArrayList<String>> list, ArrayList<String> listCol) {
        ArrayList<String> stt = new ArrayList<>();
        for (int i = 0; i < list.get(0).size(); i++) {
            stt.add(Integer.toString(i + 1));
        }
        list.add(0, stt);
        listCol.add(0, "STT");
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dtm.setColumnIdentifiers(listCol.toArray());
        int col = listCol.size();
        Object[] obj = null;
        int row = list.get(0).size();
        if (row > 0) {
            for (int i = 0; i < row; i++) {
                obj = new Object[col];
                for (int j = 0; j < col; j++) {
                    obj[j] = list.get(j).get(i);
                }
                dtm.addRow(obj);
            }
        }

        return dtm;
    }
}