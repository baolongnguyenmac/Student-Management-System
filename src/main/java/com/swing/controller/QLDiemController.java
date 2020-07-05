package com.swing.controller;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.hibernate.dao.*;
import com.swing.table.TableMaker;

public class QLDiemController {
    private JPanel _panel;

    public QLDiemController(JPanel _panel) {
        this._panel = _panel;
    }

/*  -- Lỡ viết nhưng thầy không yêu cầu :), xoá đi tiếc lắm 
    public void XemDiemSinhVien(String mssv) throws SQLException {
        ArrayList<ArrayList<String>> list = SinhVien_MonHocDAO.XemBangDiem_SinhVien(mssv);
        // SELECT mh._tenMonHoc, sv_mh._diemCC, sv_mh._diemGK, sv_mh._diemCK, sv_mh._diemTong
        ArrayList<String> listCol = new ArrayList<>();
        listCol.add("Tên môn học");
        listCol.add("Điểm quá trình");
        listCol.add("Điểm giữa kỳ");
        listCol.add("Điểm cuối kỳ");
        listCol.add("Điểm tổng");

        DefaultTableModel dtm = TableMaker.getTable(list, listCol);
        JTable table = new JTable(dtm);
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setMaxWidth(150);
        table.getColumnModel().getColumn(0).setMinWidth(150);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);

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
*/

    // setup data vào cái table để hiển thị 
    public void XemDiemLop(String tenLop, String tenMonHoc) throws SQLException {
        ArrayList<ArrayList<String>> list = SinhVien_MonHocDAO.XemBangDiem_GiaoVu(tenLop, tenMonHoc);
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

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        table.getColumnModel().getColumn(3).setMaxWidth(120);
        table.getColumnModel().getColumn(3).setMinWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);

        table.getColumnModel().getColumn(4).setMaxWidth(120);
        table.getColumnModel().getColumn(4).setMinWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);

        table.getColumnModel().getColumn(5).setMaxWidth(120);
        table.getColumnModel().getColumn(5).setMinWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);

        table.getColumnModel().getColumn(6).setMaxWidth(150);
        table.getColumnModel().getColumn(6).setMinWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);

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
        // _panel.add(scroll, BorderLayout.LINE_END);
        _panel.validate();
        _panel.repaint();
    }
}