package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "lop_hoc")
public class LopHoc {
    @Id
    @GeneratedValue
    @Column (name = "maLop")
    private long _maLopHoc;

    @Column (name = "tenLop")
    private String _tenLop;

    @OneToMany (mappedBy = "pk.LopHoc", cascade = CascadeType.ALL)
    private Set<MonHoc_LopHoc> _monHoc_lopHoc = new HashSet<MonHoc_LopHoc>();

    public LopHoc() {}

    public LopHoc(long _maLopHoc, String _tenLop, Set<MonHoc_LopHoc> _monHoc_lopHoc) {
        this._maLopHoc = _maLopHoc;
        this._tenLop = _tenLop;
        this._monHoc_lopHoc = _monHoc_lopHoc;
    }

    public long get_maLopHoc() {
        return _maLopHoc;
    }

    public void set_maLopHoc(long _maLopHoc) {
        this._maLopHoc = _maLopHoc;
    }

    public String get_tenLop() {
        return _tenLop;
    }

    public void set_tenLop(String _tenLop) {
        this._tenLop = _tenLop;
    }

    public Set<MonHoc_LopHoc> get_monHoc_lopHoc() {
        return _monHoc_lopHoc;
    }

    public void set_monHoc_lopHoc(Set<MonHoc_LopHoc> _monHoc_lopHoc) {
        this._monHoc_lopHoc = _monHoc_lopHoc;
    }
}