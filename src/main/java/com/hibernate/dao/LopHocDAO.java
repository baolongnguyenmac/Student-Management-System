package com.hibernate.dao;

import com.hibernate.entity.LopHoc;
import com.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LopHocDAO {
    public static void addLopHoc(LopHoc l) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(l);
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        LopHoc l = new LopHoc();
        l.set_tenLop("18CTT2");
        LopHocDAO.addLopHoc(l);
    }
}