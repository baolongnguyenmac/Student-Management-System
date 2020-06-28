package com.hibernate.dao;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.*;

public class LopHocDAO {
    public static void addLopHoc(String tenLop) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionJavaConfigFactory().openSession();
            session.beginTransaction();

            // do something here
            LopHoc lh = new LopHoc(tenLop);
            session.save(lh);

            session.flush();
            session.getTransaction().commit();
            session.clear();
        }
        catch (HibernateException he) {
            session.getTransaction().rollback();
            System.err.println("Lỗi ở hàm addLopHoc(String tenLop) file LopHocDAO");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static void main(String[] args) {
        addLopHoc("18CTT2");
    }
}