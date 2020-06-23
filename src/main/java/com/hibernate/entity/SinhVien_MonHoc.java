package com.hibernate.entity;

import javax.persistence.*;

@Entity
@Table (name = "SinhVien_MonHoc")
public class SinhVien_MonHoc {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "_maSinhVien_MonHoc")
    private long _maSinhVien_MonHoc;

    // liên kết với 1 biến bên class SinhVien tên là private List<SinhVien_MonHoc> _listSinhVien_MonHoc
    // mappedBy = "_sinhVien"
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name = "_maSinhVien")
    private SinhVien _sinhVien;

    // liên kết với 1 biến bên class MonHoc_LopHoc tên là private List<SinhVien_MonHoc> _litSinhVien_MonHoc
    // mappedBy = "_monHocLopHoc"
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name = "_maMonHoc_LopHoc")
    private MonHoc_LopHoc _monHoc_LopHoc;

    @Column (name = "_diemCC")
    private float _diemCC;

    @Column (name = "_diemGK")
    private float _diemGK;

    @Column (name = "_diemCK")
    private float _diemCK;

    @Column (name = "_diemTong")
    private float _diemTong;

    public SinhVien_MonHoc() {
    }

    public SinhVien_MonHoc(float _diemCC, float _diemGK, float _diemCK, float _diemTong) {
        this._diemCC = _diemCC;
        this._diemGK = _diemGK;
        this._diemCK = _diemCK;
        this._diemTong = _diemTong;
    }

    public long get_maSinhVien_MonHoc() {
        return _maSinhVien_MonHoc;
    }

    public void set_maSinhVien_MonHoc(long _maSinhVien_MonHoc) {
        this._maSinhVien_MonHoc = _maSinhVien_MonHoc;
    }

    public SinhVien get_sinhVien() {
        return _sinhVien;
    }

    public void set_sinhVien(SinhVien _sinhVien) {
        this._sinhVien = _sinhVien;
    }

    public MonHoc_LopHoc get_monHoc_LopHoc() {
        return _monHoc_LopHoc;
    }

    public void set_monHoc_LopHoc(MonHoc_LopHoc _monHoc_LopHoc) {
        this._monHoc_LopHoc = _monHoc_LopHoc;
    }

    public float get_diemCC() {
        return _diemCC;
    }

    public void set_diemCC(float _diemCC) {
        this._diemCC = _diemCC;
    }

    public float get_diemGK() {
        return _diemGK;
    }

    public void set_diemGK(float _diemGK) {
        this._diemGK = _diemGK;
    }

    public float get_diemCK() {
        return _diemCK;
    }

    public void set_diemCK(float _diemCK) {
        this._diemCK = _diemCK;
    }

    public float get_diemTong() {
        return _diemTong;
    }

    public void set_diemTong(float _diemTong) {
        this._diemTong = _diemTong;
    }
}