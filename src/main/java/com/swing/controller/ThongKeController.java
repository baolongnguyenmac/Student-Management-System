package com.swing.controller;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.hibernate.dao.*;
import com.swing.table.TableMaker;

public class ThongKeController {
    private JPanel _quaMonPanel;
    private JPanel _khongQuaMonPanel;
    // private JButton _button;
    // private JTextField _textField;

    public ThongKeController(JPanel _quaMonPanel, JPanel _khongQuaMonPanel) {
        this._quaMonPanel = _quaMonPanel;
        this._khongQuaMonPanel = _khongQuaMonPanel;
    }

    public float ThongKeQuaMon(String tenLop, String tenMonHoc) throws SQLException {
        int count = 0;
        float tyLeQuaMon;
        ArrayList<ArrayList<String>> list = SinhVien_MonHocDAO.XemBangDiem_GiaoVu(tenLop, tenMonHoc);
        int size = list.get(0).size();
        for (int i = 0; i < list.get(5).size(); i++) {
            if (Float.parseFloat(list.get(5).get(i)) < 5) {
                for (int j = 0; j < list.size(); j++) {
                    list.get(j).remove(i);
                }
            }
            else {
                count++;
            }
        }
        tyLeQuaMon = count * 1.0f / size * 100;

        // SELECT sv._mssv, sv._hoTen, sv_mh._diemCC, sv_mh._diemGK, sv_mh._diemCK, sv_mh._diemTong
        ArrayList<String> listCol = new ArrayList<>();
        listCol.add("MSSV");
        listCol.add("Họ tên");
        listCol.add("Điểm quá trình");
        listCol.add("Điểm giữa kỳ");
        listCol.add("Điểm cuối kỳ");
        listCol.add("Điểm tổng");

        DefaultTableModel dtm = TableMaker.getTable(list, listCol);
        JTable table = new JTable(dtm);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        table.getColumnModel().getColumn(1).setMaxWidth(150);
        table.getColumnModel().getColumn(1).setMinWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        table.getColumnModel().getColumn(3).setMaxWidth(150);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);

        table.getColumnModel().getColumn(4).setMaxWidth(150);
        table.getColumnModel().getColumn(4).setMinWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);

        table.getColumnModel().getColumn(5).setMaxWidth(150);
        table.getColumnModel().getColumn(5).setMinWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        _quaMonPanel.removeAll();
        _quaMonPanel.setLayout(new BorderLayout());
        _quaMonPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        _quaMonPanel.add(table, BorderLayout.CENTER);
        // _panel.add(scroll);
        _quaMonPanel.validate();
        _quaMonPanel.repaint();

        return tyLeQuaMon;
    }

    public float ThongKeKhongQuaMon(String tenLop, String tenMonHoc) throws SQLException {
        int count = 0;
        float tyLeKhongQuaMon;
        ArrayList<ArrayList<String>> list = SinhVien_MonHocDAO.XemBangDiem_GiaoVu(tenLop, tenMonHoc);
        int size = list.get(0).size();
        for (int i = 0; i < list.get(5).size(); i++) {
            if (Float.parseFloat(list.get(5).get(i)) >= 5) {
                for (int j = 0; j < list.size(); j++) {
                    list.get(j).remove(i);
                }
                i = -1;
            }
            else {
                count++;
            }
        }
        tyLeKhongQuaMon = count * 1.0f / size * 100;

        // SELECT sv._mssv, sv._hoTen, sv_mh._diemCC, sv_mh._diemGK, sv_mh._diemCK, sv_mh._diemTong
        ArrayList<String> listCol = new ArrayList<>();
        listCol.add("MSSV");
        listCol.add("Họ tên");
        listCol.add("Điểm quá trình");
        listCol.add("Điểm giữa kỳ");
        listCol.add("Điểm cuối kỳ");
        listCol.add("Điểm tổng");

        DefaultTableModel dtm = TableMaker.getTable(list, listCol);
        JTable table = new JTable(dtm);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        table.getColumnModel().getColumn(1).setMaxWidth(150);
        table.getColumnModel().getColumn(1).setMinWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        table.getColumnModel().getColumn(3).setMaxWidth(150);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);

        table.getColumnModel().getColumn(4).setMaxWidth(150);
        table.getColumnModel().getColumn(4).setMinWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);

        table.getColumnModel().getColumn(5).setMaxWidth(150);
        table.getColumnModel().getColumn(5).setMinWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        _khongQuaMonPanel.removeAll();
        _khongQuaMonPanel.setLayout(new BorderLayout());
        _khongQuaMonPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        _khongQuaMonPanel.add(table, BorderLayout.CENTER);
        // _panel.add(scroll);
        _khongQuaMonPanel.validate();
        _khongQuaMonPanel.repaint();

        return tyLeKhongQuaMon;
    }
}