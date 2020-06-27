package com.hibernate.dao;

import java.sql.*;
import java.util.List;

import javax.persistence.StoredProcedureQuery;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.*;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class SinhVienDAO {
    // private static SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
    // private static Session session = sessionFactory.openSession();

    public static void addSinhVien(SinhVien s) {
        // session.beginTransaction();
        // session.save(s);
        // try {
        //     Connection connection = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
        //     CallableStatement callableStatement = connection.prepareCall("EXEC Import_SinhVien '18120473', N'Bảo', 'Nam', '123456778', '18CTT2'");
        //     callableStatement.execute();
        //     // callableStatement.close();
        //     session.getTransaction().commit();
        // }
        // catch (SQLException se) {
        //     System.out.println("Lỗi ở hàm addSinhVien file SinhVienDAO");
        //     // System.err.println(se);
        //     // session.getTransaction().rollback();
        // }
        // @mssv CHAR(10), @hoTen NVARCHAR(100), 
        // @gioiTinh NVARCHAR(3), @cmnd CHAR(9), @tenLop VARCHAR(10)
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLSV", "sa", "Long123ohio");
            CallableStatement statement = null;
            ResultSet rs = null;
            // // demo import SinhVien
            // CallableStatement statement = conn.prepareCall("{call Import_SinhVien(?, ?, ?, ?, ?)}");
            // statement.setString(1, "1938734");
            // statement.setString(2, "Bảo Bảo");
            // statement.setString(3, "Nam");
            // statement.setString(4, "54442345");
            // statement.setString(5, "18CTT2");

            // // demo XemDanhSachLop
            // CallableStatement statement = conn.prepareCall("{call XemDanhSachLop(?)}");
            // statement.setString(1, "18CTT2");
            // rs = statement.executeQuery();
            // while (rs.next()) {
            //     System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            // }

            // // demo XemTKB
            // statement = conn.prepareCall("{call XemTKB_SinhVien(?)}");
            // statement.setString(1, "18120201");
            // rs = statement.executeQuery();
            // while (rs.next()) {
            //     System.out.println(rs.getString(1) + rs.getString(2));
            // }



            statement.close();
        }
        catch (SQLException se) {
            System.err.println("Lỗi hàm addSinhVien file SinhVienDAO");
            // System.err.println(se);
        }
    }

    public static void main(String[] args) {
        // List<SinhVien> list = layDanhSachSinhVien();
        // for (SinhVien sv: list) {
        //     System.out.println(sv.get_hoTen() + sv.get_lopHoc().get_tenLop());
        // }
        addSinhVien(new SinhVien());
    }

    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> ds = null;
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        try {
            // String hql = "select sv from SinhVien sv";
            // Query query = session.createQuery(hql);
            // ds = query.list();
        } 
        catch (HibernateException ex) { 
            // Log the exception System.err.println(ex);
        } 
        finally {
            session.close();
        }
        return ds;
    }
}