package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "sinh_vien")
public class SinhVien {
    @Id
    @GeneratedValue
    @Column (name = "maSinhVien")
    private long _maSinhVien;

    @Column (name = "mssv")
    private String _mssv;

    @Column (name = "hoTen")
    private String _hoTen;

    @Column (name = "gioiTinh")
    private String gioiTinh;

    @Column (name = "cmnd")
    private String _cmnd;

    @ManyToOne
    @JoinColumn(name = "maLop")
    private long _maLop;

    @OneToMany (mappedBy = "list_sinhVien_monHoc")
    private Set<SinhVien_MonHoc> _sinhVien_monHoc = new HashSet<SinhVien_MonHoc>();

    public SinhVien() {
    }

    public SinhVien(long _maSinhVien, String _mssv, String _hoTen, String gioiTinh, String _cmnd, long _maLop,
            Set<SinhVien_MonHoc> _sinhVien_monHoc) {
        this._maSinhVien = _maSinhVien;
        this._mssv = _mssv;
        this._hoTen = _hoTen;
        this.gioiTinh = gioiTinh;
        this._cmnd = _cmnd;
        this._maLop = _maLop;
        this._sinhVien_monHoc = _sinhVien_monHoc;
    }

    public long get_maSinhVien() {
        return _maSinhVien;
    }

    public void set_maSinhVien(long _maSinhVien) {
        this._maSinhVien = _maSinhVien;
    }

    public String get_mssv() {
        return _mssv;
    }

    public void set_mssv(String _mssv) {
        this._mssv = _mssv;
    }

    public String get_hoTen() {
        return _hoTen;
    }

    public void set_hoTen(String _hoTen) {
        this._hoTen = _hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String get_cmnd() {
        return _cmnd;
    }

    public void set_cmnd(String _cmnd) {
        this._cmnd = _cmnd;
    }

    public long get_maLop() {
        return _maLop;
    }

    public void set_maLop(long _maLop) {
        this._maLop = _maLop;
    }

    public Set<SinhVien_MonHoc> get_sinhVien_monHoc() {
        return _sinhVien_monHoc;
    }

    public void set_sinhVien_monHoc(Set<SinhVien_MonHoc> _sinhVien_monHoc) {
        this._sinhVien_monHoc = _sinhVien_monHoc;
    }
}