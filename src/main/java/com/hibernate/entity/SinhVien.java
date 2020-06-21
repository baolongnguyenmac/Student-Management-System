package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "sinh_vien")
public class SinhVien {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLop")
    private LopHoc _lopHoc;

    @OneToMany (mappedBy = "_sinhVien", fetch = FetchType.LAZY)
    private List<SinhVien_MonHoc> _sinhVien_monHoc = new ArrayList<SinhVien_MonHoc>();

    public SinhVien() {
    }

    public SinhVien(String _mssv, String _hoTen, String gioiTinh, String _cmnd, LopHoc _lopHoc) {
        this._mssv = _mssv;
        this._hoTen = _hoTen;
        this.gioiTinh = gioiTinh;
        this._cmnd = _cmnd;
        this._lopHoc = _lopHoc;
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

    public List<SinhVien_MonHoc> get_sinhVien_monHoc() {
        return _sinhVien_monHoc;
    }

    public void set_sinhVien_monHoc(ArrayList<SinhVien_MonHoc> _sinhVien_monHoc) {
        this._sinhVien_monHoc = _sinhVien_monHoc;
    }

    public LopHoc get_lopHoc() {
        return _lopHoc;
    }

    public void set_lopHoc(LopHoc _lopHoc) {
        this._lopHoc = _lopHoc;
    }

    public SinhVien(String _mssv, String _hoTen, String gioiTinh, String _cmnd) {
        this._mssv = _mssv;
        this._hoTen = _hoTen;
        this.gioiTinh = gioiTinh;
        this._cmnd = _cmnd;
    }
}