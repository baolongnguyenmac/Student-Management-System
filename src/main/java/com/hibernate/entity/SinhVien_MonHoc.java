package com.hibernate.entity;

import javax.persistence.*;

@Entity
@Table (name = "sinhVien_monHoc")
public class SinhVien_MonHoc {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column (name = "maSinhVienMonHoc")
    private long _maSinhVienMonHoc;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JoinColumn(name = "maMonHocLopHoc")
    private MonHoc_LopHoc _monHocLopHoc;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JoinColumn(name = "maSinhVien")
    private SinhVien _sinhVien;

    public SinhVien_MonHoc() {
    }

    public SinhVien_MonHoc(MonHoc_LopHoc _monHocLopHoc, SinhVien _sinhVien) {
        this._monHocLopHoc = _monHocLopHoc;
        this._sinhVien = _sinhVien;
    }

    public long get_maSinhVienMonHoc() {
        return _maSinhVienMonHoc;
    }

    public void set_maSinhVienMonHoc(long _maSinhVienMonHoc) {
        this._maSinhVienMonHoc = _maSinhVienMonHoc;
    }

    public MonHoc_LopHoc get_monHocLopHoc() {
        return _monHocLopHoc;
    }

    public void set_monHocLopHoc(MonHoc_LopHoc _monHocLopHoc) {
        this._monHocLopHoc = _monHocLopHoc;
    }

    public SinhVien get_sinhVien() {
        return _sinhVien;
    }

    public void set_sinhVien(SinhVien _sinhVien) {
        this._sinhVien = _sinhVien;
    }
}