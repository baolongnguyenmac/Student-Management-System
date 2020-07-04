package com.swing.controller;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.hibernate.dao.MonHoc_LopHocDAO;
import com.swing.table.TableMaker;

public class QLTKBController {
    private JPanel _panel;
    // private JButton _button;
    // private JTextField _textField;

    public QLTKBController(JPanel _panel) {
        this._panel = _panel;
        // this._button = _button;
        // this._textField = _textField;
    }

    public void XemTKBSinhVien(String mssv) throws SQLException {
        ArrayList<ArrayList<String>> list = MonHoc_LopHocDAO.XemTKB_SinhVien(mssv);
        // SELECT mh._maMonCuaTruong, mh._tenMonHoc, mh_lh._phongHoc
        ArrayList<String> listCol = new ArrayList<>();
        listCol.add("Mã môn học");
        listCol.add("Tên môn học");
        listCol.add("Phòng học");

        DefaultTableModel dtm = TableMaker.getTable(list, listCol);
        JTable table = new JTable(dtm);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);

        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        _panel.removeAll();
        _panel.setLayout(new BorderLayout());
        _panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        _panel.add(table, BorderLayout.CENTER);
        _panel.validate();
        _panel.repaint();
    }

    public void XemTKBLop(String tenLop) throws SQLException {
        ArrayList<ArrayList<String>> list = MonHoc_LopHocDAO.XemTKB_LopHoc(tenLop);
        // SELECT mh._maMonCuaTruong, mh._tenMonHoc, mh_lh._phongHoc
        ArrayList<String> listCol = new ArrayList<>();
        listCol.add("Mã môn học");
        listCol.add("Tên môn học");
        listCol.add("Phòng học");

        DefaultTableModel dtm = TableMaker.getTable(list, listCol);
        JTable table = new JTable(dtm);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);

        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        _panel.removeAll();
        _panel.setLayout(new BorderLayout());
        _panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        _panel.add(table, BorderLayout.CENTER);
        // _panel.add(scroll);
        _panel.validate();
        _panel.repaint();
    }
}