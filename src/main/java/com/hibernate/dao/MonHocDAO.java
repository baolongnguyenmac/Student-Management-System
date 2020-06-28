package com.hibernate.dao;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.*;

public class MonHocDAO {
    public static void addMonHoc(String tenMonHoc) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionJavaConfigFactory().openSession();
            session.beginTransaction();

            // do something here
            MonHoc mh = new MonHoc(tenMonHoc);
            session.save(mh);

            session.flush();
            session.getTransaction().commit();
            session.clear();
        }
        catch (HibernateException he) {
            session.getTransaction().rollback();
            System.err.println("Lỗi ở hàm addMonHoc(String tenMonHoc) file MonHocDAO");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static void main(String[] args) {
        addMonHoc("Lập trình hướng đối tượng");
        addMonHoc("Cơ sở dữ liệu");
    }
}