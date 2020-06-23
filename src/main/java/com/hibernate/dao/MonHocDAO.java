package com.hibernate.dao;

import com.hibernate.entity.MonHoc;
import com.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MonHocDAO {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            MonHoc m = new MonHoc("Lập trình hướng đối tượng");
            MonHoc m1 = new MonHoc("Cơ sở dữ liệu");

            session.save(m);
            session.save(m1);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("ăn lồn ở dòng 27 file MonHocDAO");
        }

        if (session != null) {
            session.close();
        }
    }
}