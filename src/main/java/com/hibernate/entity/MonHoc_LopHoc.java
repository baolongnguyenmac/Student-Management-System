package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "monHoc_lopHoc")
public class MonHoc_LopHoc {
    @Id
    @GeneratedValue
    @Column (name = "maMonHocLopHoc")
    private long _maMonHocLopHoc;

    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maMonHoc")
    private MonHoc _monHoc;

    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maLopHoc")
    private LopHoc _lopHoc;

    @Column (name = "phongHoc")
    private String _phongHoc;

    @OneToMany (mappedBy = "list_sinhVien_monHoc")
    private Set<SinhVien_MonHoc> _sinhVien_monHoc = new HashSet<SinhVien_MonHoc>();

    public MonHoc_LopHoc() {
    }

    public MonHoc_LopHoc(long _maMonHocLopHoc, MonHoc _monHoc, LopHoc _lopHoc, String _phongHoc,
            Set<SinhVien_MonHoc> _sinhVien_monHoc) {
        this._maMonHocLopHoc = _maMonHocLopHoc;
        this._monHoc = _monHoc;
        this._lopHoc = _lopHoc;
        this._phongHoc = _phongHoc;
        this._sinhVien_monHoc = _sinhVien_monHoc;
    }

    public String get_phongHoc() {
        return _phongHoc;
    }

    public void set_phongHoc(String _phongHoc) {
        this._phongHoc = _phongHoc;
    }

    public MonHoc get_monHoc() {
        return _monHoc;
    }

    public void set_monHoc(MonHoc _monHoc) {
        this._monHoc = _monHoc;
    }

    public LopHoc get_lopHoc() {
        return _lopHoc;
    }

    public void set_lopHoc(LopHoc _lopHoc) {
        this._lopHoc = _lopHoc;
    }

    public long get_maMonHocLopHoc() {
        return _maMonHocLopHoc;
    }

    public void set_maMonHocLopHoc(long _maMonHocLopHoc) {
        this._maMonHocLopHoc = _maMonHocLopHoc;
    }

    public Set<SinhVien_MonHoc> get_sinhVien_monHoc() {
        return _sinhVien_monHoc;
    }

    public void set_sinhVien_monHoc(Set<SinhVien_MonHoc> _sinhVien_monHoc) {
        this._sinhVien_monHoc = _sinhVien_monHoc;
    }
}