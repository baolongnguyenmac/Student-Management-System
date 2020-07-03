package com.swing.obj;

import javax.swing.*;

public class DanhMuc {
    private String _kind;
    private JPanel _panel;
    private JLabel _label;

    public String get_kind() {
        return _kind;
    }

    public void set_kind(String _kind) {
        this._kind = _kind;
    }

    public JPanel get_panel() {
        return _panel;
    }

    public void set_panel(JPanel _panel) {
        this._panel = _panel;
    }

    public JLabel get_label() {
        return _label;
    }

    public void set_label(JLabel _label) {
        this._label = _label;
    }

    public DanhMuc(String _kind, JPanel _panel, JLabel _label) {
        this._kind = _kind;
        this._panel = _panel;
        this._label = _label;
    }

    public DanhMuc() {
    }

    
}