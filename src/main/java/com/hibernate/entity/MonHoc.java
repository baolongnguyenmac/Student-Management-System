package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "MonHoc")
public class MonHoc {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "_maMonHoc")
    private long _maMonHoc;

    @Column (name = "_tenMonHoc")
    private String _tenMonHoc;

    @Column (name = "_maMonCuaTruong")
    private String _maMonCuaTruong;

    // liên kết với 1 biến bên class MonHoc_LopHoc tên là private MonHoc _monHoc
    @OneToMany (mappedBy = "_monHoc")
    private List<MonHoc_LopHoc> _listMonHoc_LopHoc = new ArrayList<MonHoc_LopHoc>();

    public MonHoc() {
    }

    public MonHoc(String _tenMonHoc, String _maMonCuaTruong) {
        this._tenMonHoc = _tenMonHoc;
        this._maMonCuaTruong = _maMonCuaTruong;
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

    public List<MonHoc_LopHoc> get_listMonHoc_LopHoc() {
        return _listMonHoc_LopHoc;
    }

    public void set_listMonHoc_LopHoc(List<MonHoc_LopHoc> _listMonHoc_LopHoc) {
        this._listMonHoc_LopHoc = _listMonHoc_LopHoc;
    }

    public void addMonHoc_LopHoc(MonHoc_LopHoc mh_lh) {
        mh_lh.set_monHoc(this);
        _listMonHoc_LopHoc.add(mh_lh);
    }

    public String get_maMonCuaTruong() {
        return _maMonCuaTruong;
    }

    public void set_maMonCuaTruong(String _maMonCuaTruong) {
        this._maMonCuaTruong = _maMonCuaTruong;
    }
}