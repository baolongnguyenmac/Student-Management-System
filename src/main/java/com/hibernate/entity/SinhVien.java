package com.hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "SinhVien")
public class SinhVien {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "_maSinhVien")
    private long _maSinhVien;

    @Column (name = "_mssv")
    private String _mssv;

    @Column (name = "_hoTen")
    private String _hoTen;

    @Column (name = "_gioiTinh")
    private String gioiTinh;

    @Column (name = "_cmnd")
    private String _cmnd;

    // liên kết với 1 biến bên class LopHoc tên là private List<SinhVien> _listSinhVien
    // mappedBy = "_lopHoc"
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "_maLop")
    private LopHoc _lopHoc;

    // liên kết với 1 biến bên class SinhVien_MonHoc tên là private SinhVien _sinhVien
    @OneToMany (mappedBy = "_sinhVien")
    private List<SinhVien_MonHoc> _listSinhVien_MonHoc = new ArrayList<SinhVien_MonHoc>();

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

    public LopHoc get_lopHoc() {
        return _lopHoc;
    }

    public void set_lopHoc(LopHoc _lopHoc) {
        this._lopHoc = _lopHoc;
    }

    public SinhVien() {
    }

    public SinhVien(String _mssv, String _hoTen, String gioiTinh, String _cmnd) {
        this._mssv = _mssv;
        this._hoTen = _hoTen;
        this.gioiTinh = gioiTinh;
        this._cmnd = _cmnd;
    }

    public List<SinhVien_MonHoc> get_listSinhVien_MonHoc() {
        return _listSinhVien_MonHoc;
    }

    public void set_listSinhVien_MonHoc(List<SinhVien_MonHoc> _listSinhVien_MonHoc) {
        this._listSinhVien_MonHoc = _listSinhVien_MonHoc;
    }

    public void addSinhVien_MonHoc(SinhVien_MonHoc sv_mh) {
        sv_mh.set_sinhVien(this);
        _listSinhVien_MonHoc.add(sv_mh);
    }
}