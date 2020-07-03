package com.swing.controller;

import com.swing.obj.*;

import javax.swing.*;

import com.swing.view.AddDiemLopHoc;
import com.swing.view.AddLopHoc;
import com.swing.view.AddSinhVien;
import com.swing.view.AddTKB;
import com.swing.view.DangKyMonHoc;
import com.swing.view.HuyMonHoc;
import com.swing.view.QLDiem;
import com.swing.view.QLLopHoc;
import com.swing.view.QLMonHoc;
import com.swing.view.QLTKB;
import com.swing.view.TrangChu;
import com.swing.view.UpdateDiemSV;
import com.swing.view.XemDSLop;
import com.swing.view.XemDiemLopHoc;
import com.swing.view.XemTKBLop;
import com.swing.view.XemTKBSinhVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

import java.util.List;

public class ChuyenManHinh {
    private JPanel _rootPanel;
    private String _selectedKind = "";
    List<DanhMuc> _listDanhMuc = null;

    public ChuyenManHinh(JPanel _rootPanel) {
        this._rootPanel = _rootPanel;
    }

    public void setView(JPanel panel, JLabel label) {
        _selectedKind = "TrangChu";

        panel.setBackground(new Color(96, 100, 191));
        label.setBackground(new Color(96, 100, 191));

        _rootPanel.removeAll();
        _rootPanel.setLayout(new BorderLayout());
        _rootPanel.add(new TrangChu());
        _rootPanel.validate();
        _rootPanel.repaint();
    }

    public void setEvent(List<DanhMuc> _listDanhMuc) {
        this._listDanhMuc = _listDanhMuc;
        for (DanhMuc item : _listDanhMuc) {
            item.get_label().addMouseListener(new LabelEvent(item.get_kind(), item.get_panel(), item.get_label()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;

        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu": {
                    node = new TrangChu();
                } break;

                case "QLLopHoc": {
                    node = new QLLopHoc();
                } break;

                case "QLTKB": {
                    node = new QLTKB();
                } break;

                case "QLDiem": {
                    node = new QLDiem();
                } break;

                case "QLMonHoc": {
                    node = new QLMonHoc();
                } break;

                case "AddSinhVien": {
                    node = new AddSinhVien();
                } break;

                case "AddLopHoc": {
                    node = new AddLopHoc();
                } break;

                case "XemDSLop": {
                    node = new XemDSLop();
                } break;

                case "DangKyMonHoc": {
                    node = new DangKyMonHoc();
                } break;

                case "HuyMonHoc": {
                    node = new HuyMonHoc();
                } break;

                case "AddTKB": {
                    node = new AddTKB();
                } break;

                case "XemTKBSinhVien": {
                    node = new XemTKBSinhVien();
                } break;

                case "XemTKBLop": {
                    node = new XemTKBLop();
                } break;

                case "AddDiemLopHoc": {
                    node = new AddDiemLopHoc();
                } break;

                case "XemDiemLopHoc": {
                    node = new XemDiemLopHoc();
                } break;

                case "UpdateDiemSV": {
                    node = new UpdateDiemSV();
                } break;

                default: {
                    node = new TrangChu();
                } break;
            }
            _rootPanel.removeAll();
            _rootPanel.setLayout(new BorderLayout());
            _rootPanel.add(node);
            _rootPanel.validate();
            _rootPanel.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            _selectedKind = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!_selectedKind.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(0, 204, 204));
                jlbItem.setBackground(new Color(0, 204, 204));
            }
        }
    }

    private void setChangeBackground(String kind) {
        for (DanhMuc dm: _listDanhMuc) {
            if (dm.get_kind().equalsIgnoreCase(kind)) {
                dm.get_panel().setBackground(new Color(96, 100, 191));
                dm.get_label().setBackground(new Color(96, 100, 191));
            }
            else {
                dm.get_panel().setBackground(new Color(0, 204, 204));
                dm.get_label().setBackground(new Color(0, 204, 204));
            }
        }
    }
}