package com.hibernate.entity;

import javax.persistence.*;

@Entity
@Table (name = "sinhVien_monHoc")
public class SinhVien_MonHoc {
    @Id
    @GeneratedValue
    @Column (name = "maSinhVienMonHoc")
    private long _maSinhVienMonHoc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maMonHocLopHoc")
    private long _maMonHocLopHoc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maSinhVien")
    private long _maSinhVien;

    public SinhVien_MonHoc(long _maSinhVienMonHoc, long _maMonHocLopHoc, long _maSinhVien) {
        this._maSinhVienMonHoc = _maSinhVienMonHoc;
        this._maMonHocLopHoc = _maMonHocLopHoc;
        this._maSinhVien = _maSinhVien;
    }

    public SinhVien_MonHoc() {
    }

    public long get_maSinhVienMonHoc() {
        return _maSinhVienMonHoc;
    }

    public void set_maSinhVienMonHoc(long _maSinhVienMonHoc) {
        this._maSinhVienMonHoc = _maSinhVienMonHoc;
    }

    public long get_maMonHocLopHoc() {
        return _maMonHocLopHoc;
    }

    public void set_maMonHocLopHoc(long _maMonHocLopHoc) {
        this._maMonHocLopHoc = _maMonHocLopHoc;
    }

    public long get_maSinhVien() {
        return _maSinhVien;
    }

    public void set_maSinhVien(long _maSinhVien) {
        this._maSinhVien = _maSinhVien;
    }
}