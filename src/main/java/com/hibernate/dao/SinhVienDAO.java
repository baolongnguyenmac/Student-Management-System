package com.hibernate.dao;

import java.util.List;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.*;

public class SinhVienDAO {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
    private static Session session = sessionFactory.openSession();

    public static void addSinhVien(SinhVien s) {
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        List<SinhVien> list = layDanhSachSinhVien();
        for (SinhVien sv: list) {
            System.out.println(sv.get_hoTen() + sv.get_lopHoc().get_tenLop());
        }
    }

    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> ds = null;
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        try {
            // String hql = "select sv from SinhVien sv";
            // Query query = session.createQuery(hql);
            // ds = query.list();
        } 
        catch (HibernateException ex) { // Log the exception System.err.println(ex);
        } 
        finally {
            session.close();
        }
        return ds;
    }
}