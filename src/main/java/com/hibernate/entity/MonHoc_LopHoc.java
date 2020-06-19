package com.hibernate.entity;

import javax.persistence.*;

@Entity
@Table (name = "monHoc_lopHoc")
@AssociationOverrides ({
    @AssociationOverride (name = "pk.MonHoc", joinColumns = @JoinColumn(name = "maMonHoc")),
    @AssociationOverride (name = "pk.LopHoc", joinColumns = @JoinColumn(name = "maLop"))
})
public class MonHoc_LopHoc {
    @EmbeddedId
    private MonHoc_LopHoc_ID pk = new MonHoc_LopHoc_ID();

    @Column (name = "phongHoc")
    private String _phongHoc;

    public MonHoc_LopHoc() {
    }

    public MonHoc_LopHoc(MonHoc_LopHoc_ID pk, String _phongHoc) {
        this.pk = pk;
        this._phongHoc = _phongHoc;
    }

    public MonHoc_LopHoc_ID getPk() {
        return pk;
    }

    public void setPk(MonHoc_LopHoc_ID pk) {
        this.pk = pk;
    }

    public String get_phongHoc() {
        return _phongHoc;
    }

    public void set_phongHoc(String _phongHoc) {
        this._phongHoc = _phongHoc;
    }

    @Transient
    public LopHoc get_lopHoc() {
        return pk.get_lopHoc();
    }

    public void set_lopHoc(LopHoc _lopHoc) {
        pk.set_lopHoc(_lopHoc);
    }

    @Transient
    public MonHoc get_monHoc() {
        return pk.get_monHoc();
    }

    public void set_monHoc(MonHoc _monHoc) {
        pk.set_monHoc(_monHoc);
    }
}