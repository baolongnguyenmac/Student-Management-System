package com.hibernate.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table (name = "MonHoc_LopHoc")
public class MonHoc_LopHoc {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "_maMonHoc_LopHoc")
    private long _maMonHoc_LopHoc;

    // liên kết với 1 biến bên class LopHoc tên là private List<MonHoc_LopHoc> _listMonHoc_LopHoc
    // mappedBy = "_lopHoc"
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name = "_maLop")
    private LopHoc _lopHoc;

    // liên kết với 1 biến bên class MonHoc tên là private List<MonHoc_LopHoc> _listMonHoc_LopHoc
    // mappedBy = "_monHoc"
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name = "_maMonHoc")
    private MonHoc _monHoc;

    @Column (name = "_phongHoc")
    private String _phongHoc;

    // liên kết với 1 biến bên class SinhVien_MonHoc tên là private MonHoc_LopHoc _monHoc_LopHoc
    @OneToMany (mappedBy = "_monHoc_LopHoc")
    private List<SinhVien_MonHoc> _listSinhVien_MonHoc = new ArrayList<SinhVien_MonHoc>();

    public MonHoc_LopHoc() {
    }

    public MonHoc_LopHoc(String _phongHoc) {
        this._phongHoc = _phongHoc;
    }

    public LopHoc get_lopHoc() {
        return _lopHoc;
    }

    public void set_lopHoc(LopHoc _lopHoc) {
        this._lopHoc = _lopHoc;
    }

    public MonHoc get_monHoc() {
        return _monHoc;
    }

    public void set_monHoc(MonHoc _monHoc) {
        this._monHoc = _monHoc;
    }

    public String get_phongHoc() {
        return _phongHoc;
    }

    public void set_phongHoc(String _phongHoc) {
        this._phongHoc = _phongHoc;
    }

    public long get_maMonHoc_LopHoc() {
        return _maMonHoc_LopHoc;
    }

    public void set_maMonHoc_LopHoc(long _maMonHoc_LopHoc) {
        this._maMonHoc_LopHoc = _maMonHoc_LopHoc;
    }

    public List<SinhVien_MonHoc> get_listSinhVien_MonHoc() {
        return _listSinhVien_MonHoc;
    }

    public void set_listSinhVien_MonHoc(List<SinhVien_MonHoc> _listSinhVien_MonHoc) {
        this._listSinhVien_MonHoc = _listSinhVien_MonHoc;
    }

    public void addSinhVien_MonHoc(SinhVien_MonHoc sv_mh) {
        sv_mh.set_monHoc_LopHoc(this);
        _listSinhVien_MonHoc.add(sv_mh);
    }
}