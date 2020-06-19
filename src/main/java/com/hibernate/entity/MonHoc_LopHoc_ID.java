package com.hibernate.entity;

import javax.persistence.*;

@Embeddable
public class MonHoc_LopHoc_ID {
    @ManyToOne (cascade = CascadeType.ALL)
    private LopHoc _lopHoc;

    @ManyToOne (cascade = CascadeType.ALL)
    private MonHoc _monHoc;

    public MonHoc_LopHoc_ID(LopHoc _lopHoc, MonHoc _monHoc) {
        this._lopHoc = _lopHoc;
        this._monHoc = _monHoc;
    }

    public MonHoc_LopHoc_ID() {}

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
}