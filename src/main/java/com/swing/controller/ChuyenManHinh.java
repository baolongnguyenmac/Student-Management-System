package com.swing.controller;

import com.swing.obj.*;

import javax.swing.*;

import com.swing.view.*;

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

    // hàm này dùng để set các tab mặc định cho 1 panel/frame
    // cách làm việc: đổi màu tab mặc định và hiển thị nội dung tab đó
    // truyền vào panel chứa cái tab đó và nhãn trong cái panel và kind of tab 
    public void setView(JPanel panel, JLabel label, String kind) {
        _selectedKind = kind;

        panel.setBackground(new Color(96, 100, 191));
        label.setBackground(new Color(96, 100, 191));

        _rootPanel.removeAll();
        _rootPanel.setLayout(new BorderLayout());
        // _rootPanel.add(new TrangChu());

        switch (kind) {
            case "TrangChu": {
                _rootPanel.add(new TrangChu());
            } break;

            case "AddDiemLopHoc": {
                _rootPanel.add(new AddDiemLopHoc());
            } break;

            case "AddLopHoc": {
                _rootPanel.add(new AddLopHoc());
            } break;

            case "DangKyMonHoc": {
                _rootPanel.add(new DangKyMonHoc());
            } break;

            case "AddTKB": {
                _rootPanel.add(new AddTKB());
            } break;
        }

        _rootPanel.validate();
        _rootPanel.repaint();
    }

    // add event cho các item trong _listDanhMuc 
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

                case "ThongKeDuLieu": {
                    node = new ThongKeDuLieu();
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

    // cop trên mạng, chả biết để làm gì luôn :)
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