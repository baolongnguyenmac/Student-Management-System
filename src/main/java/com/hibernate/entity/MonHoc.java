package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "mon_hoc")
public class MonHoc {
    @Id
    @GeneratedValue
    @Column (name = "maMonHoc")
    private long _maMonHoc;

    @Column (name = "tenMonHoc")
    private String _tenMonHoc;

    @OneToMany (mappedBy = "list_monHoc_lopHoc")
    private Set<MonHoc_LopHoc> _monHoc_lopHoc = new HashSet<MonHoc_LopHoc>();

    public MonHoc() {
    }

    public MonHoc(long _maMonHoc, String _tenMonHoc, Set<MonHoc_LopHoc> _monHoc_lopHoc) {
        this._maMonHoc = _maMonHoc;
        this._tenMonHoc = _tenMonHoc;
        this._monHoc_lopHoc = _monHoc_lopHoc;
    }

    public long get_maMonHoc() {
        return _maMonHoc;
    }

    public void set_maMonHoc(long _maMonHoc) {
        this._maMonHoc = _maMonHoc;
    }

    public String get_tenMonHoc() {
        return _tenMonHoc;
    }

    public void set_tenMonHoc(String _tenMonHoc) {
        this._tenMonHoc = _tenMonHoc;
    }

    public Set<MonHoc_LopHoc> get_monHoc_lopHoc() {
        return _monHoc_lopHoc;
    }

    public void set_monHoc_lopHoc(Set<MonHoc_LopHoc> _monHoc_lopHoc) {
        this._monHoc_lopHoc = _monHoc_lopHoc;
    }
}