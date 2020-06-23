package com.hibernate.dao;

import com.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SinhVien_MonHocDAO {
    public static void TKBSinhVien() {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();;
        Session session = null;
        

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();



            // session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("ăn lồn ở file SinhVien_MonHocDAO");
        }
    }
    public static void main(String[] args) {
        
    }
}