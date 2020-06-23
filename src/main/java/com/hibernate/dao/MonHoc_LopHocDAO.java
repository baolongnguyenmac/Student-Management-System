package com.hibernate.dao;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MonHoc_LopHocDAO {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = null;
        LopHoc lopTui = null;
        MonHoc monTui = null;
        MonHoc_LopHoc monHoc_lopHoc = new MonHoc_LopHoc();

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            lopTui = session.createQuery("from LopHoc l where l._tenLop = '18CTT1'", LopHoc.class).getSingleResult();
            monTui = session.createQuery("from MonHoc m where m._tenMonHoc like :name", MonHoc.class).setParameter("name", "%Lập trình hướng đối tượng%").getSingleResult();

            lopTui.addMonHoc_LopHoc(monHoc_lopHoc);
            monTui.addMonHoc_LopHoc(monHoc_lopHoc);
            monHoc_lopHoc.set_phongHoc("E1");

            session.save(lopTui);
            session.save(monTui);
            session.save(monHoc_lopHoc);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ăn lồn ở dòng 36 file MonHoc_LopHocDAO");
        }
        finally {
            session.close();
        }
    }
}