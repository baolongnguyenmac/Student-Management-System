package com.hibernate.dao;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LopHocDAO {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            LopHoc lopHoc1 = new LopHoc("18CTT1");
            LopHoc lopHoc2 = new LopHoc("18CTT2");
            LopHoc lopHoc3 = new LopHoc("18CTT3");
            LopHoc lopHoc4 = new LopHoc("18CTT4");
            LopHoc lopHoc5 = new LopHoc("18CTT5");
            lopHoc1.addSinhVien(new SinhVien("18120201", "Nguyễn Bảo Long", "Nam", "241845617"));
            lopHoc2.addSinhVien(new SinhVien("18120211", "Võ Thế Minh", "Nam", "241845618"));
            lopHoc3.addSinhVien(new SinhVien("18120227", "Phạm Văn Minh Phương", "Nam", "241845619"));
            lopHoc4.addSinhVien(new SinhVien("18120662", "Trà Anh Toàn", "Nam", "241845610"));
            lopHoc5.addSinhVien(new SinhVien("10000000", "Đặng Thị Hồng Suyên", "Nữ", "241845600"));

            session.save(lopHoc1);
            session.save(lopHoc2);
            session.save(lopHoc3);
            session.save(lopHoc4);
            session.save(lopHoc5);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("ăn lồn r nhé :) LopHocDAO dòng 37");
        }

        if (session != null) {
            session.close();
        }
    }
}