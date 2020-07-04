package com.swing.controller;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.hibernate.dao.LopHocDAO;
import com.swing.table.TableMaker;

public class QLLopHocController {
    private JPanel _panel;
    // private JButton _button;
    // private JTextField _textField;

    public QLLopHocController(JPanel _panel) {
        this._panel = _panel;
        // this._button = _button;
        // this._textField = _textField;
    }

    public void XemDSLop(String tenLop) throws SQLException {
        ArrayList<ArrayList<String>> list = LopHocDAO.XemDanhSachLop(tenLop);
        // SELECT sv._mssv, sv._hoTen, sv._gioiTinh, sv._cmnd
        ArrayList<String> listCol = new ArrayList<>();
        listCol.add("MSSV");
        listCol.add("Họ và tên");
        listCol.add("CMND");
        listCol.add("Giới tính");

        DefaultTableModel dtm = TableMaker.getTable(list, listCol);
        JTable table = new JTable(dtm);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMinWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);

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