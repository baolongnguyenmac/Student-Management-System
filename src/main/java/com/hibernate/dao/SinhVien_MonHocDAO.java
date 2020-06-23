package com.hibernate.dao;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SinhVien_MonHocDAO {
    public static void add() {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = null;
        MonHoc_LopHoc mh_lh = null;
        SinhVien sv = null;
        SinhVien_MonHoc sv_mh = new SinhVien_MonHoc(10.0f, 0.1f, 5.0f, 4.0f);

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            sv = session.createQuery("from SinhVien sv where sv._mssv = '18120201'", SinhVien.class).getSingleResult();
            mh_lh = session.createQuery("from MonHoc_LopHoc", MonHoc_LopHoc.class).getResultList().get(0);

            sv.addSinhVien_MonHoc(sv_mh);
            mh_lh.addSinhVien_MonHoc(sv_mh);

            session.save(sv_mh);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("ăn lồn ở file SinhVien_MonHocDAO");
        }
    }
    public static void main(String[] args) {
        add();
    }
}