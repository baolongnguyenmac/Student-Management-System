package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "LopHoc")
public class LopHoc {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "_maLop")
    private long _maLop;

    @Column (name = "_tenLop")
    private String _tenLop;

    // liên kết với 1 biến bên class MonHoc_LopHoc tên là private LopHoc _lopHoc
    @OneToMany (mappedBy = "_lopHoc")
    private List<MonHoc_LopHoc> _listMonHoc_LopHoc = new ArrayList<MonHoc_LopHoc>();

    // liên kết với 1 biến bên class SinhVien tên là private LopHoc _lopHoc
    @OneToMany(mappedBy = "_lopHoc", cascade = CascadeType.ALL)
    private List<SinhVien> _listSinhVien = new ArrayList<SinhVien>();

    public LopHoc(String _tenLop) {
        this._tenLop = _tenLop;
    }

    public LopHoc() {
    }

    public long get_maLop() {
        return _maLop;
    }

    public void set_maLop(long _maLop) {
        this._maLop = _maLop;
    }

    public String get_tenLop() {
        return _tenLop;
    }

    public void set_tenLop(String _tenLop) {
        this._tenLop = _tenLop;
    }

    public List<MonHoc_LopHoc> get_listMonHoc_LopHoc() {
        return _listMonHoc_LopHoc;
    }

    public void set_listMonHoc_LopHoc(List<MonHoc_LopHoc> _listMonHoc_LopHoc) {
        this._listMonHoc_LopHoc = _listMonHoc_LopHoc;
    }

    public List<SinhVien> get_listSinhVien() {
        return _listSinhVien;
    }

    public void set_listSinhVien(List<SinhVien> _listSinhVien) {
        this._listSinhVien = _listSinhVien;
    }

    public void addMonHoc_LopHoc(MonHoc_LopHoc mh_lh) {
        mh_lh.set_lopHoc(this);
        _listMonHoc_LopHoc.add(mh_lh);
    }

    public void addSinhVien(SinhVien sv) {
        sv.set_lopHoc(this);
        _listSinhVien.add(sv);
    }
}