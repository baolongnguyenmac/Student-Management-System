package com.hibernate.dao;

import java.io.*;
import java.sql.*;

import com.hibernate.util.HibernateUtil;

public class MonHoc_LopHocDAO {
    private static String[] readLineTKB(String line) {
        return line.split(",");
    }

    public static void importTKB(String filename) {
        Connection conn = null;
        BufferedReader br = null;
        String line = null;

        try {
            conn = HibernateUtil.getConnection();
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf8"));

            // do something
            // Import_TKB @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10), @phongHoc VARCHAR(10)
            CallableStatement importTKB = conn.prepareCall("{Call Import_TKB(?, ?, ?)}");

            // tạo lớp học: k.tr xem có tạo trước đó chưa, nếu chưa thì tạo
            String tenLop = br.readLine();
            LopHocDAO.addLopHoc(tenLop);

            // // create_monHoc @tenMonHoc NVARCHAR(100), @maMonHoc CHAR(6)
            // CallableStatement createMonHoc = conn.prepareCall("{Call create_monHoc (?, ?)}");

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] array = readLineTKB(line);

                // tạo ra môn học: k.tr xem có tạo trước đó chưa, nếu chưa thì tạo
                MonHocDAO.addMonHoc(array[2], array[1]);

                // tạo tkb
                importTKB.setString(1, array[2]);
                importTKB.setString(2, tenLop);
                importTKB.setString(3, array[3]);
                importTKB.execute();
            }
        }
        catch (SQLException se) {
            System.err.println("lỗi ở hàm importTKB(String filename) file MonHoc_LopHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
        catch (IOException ioe) {
            System.err.println("Lỗi IOE ở Hàm importTKB(String filename) file MonHoc_LopHocDAO");
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException ioe) {
                    System.err.println("Lỗi IOE ở Hàm importTKB(String filename) file MonHoc_LopHocDAO");
                }
            }
        }
    }

    public static void XemTKB_SinhVien(String mssv) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // XemTKB_SinhVien @mssv CHAR(10)
            CallableStatement xemTKB_SinhVien = conn.prepareCall("{Call XemTKB_SinhVien(?)}");
            xemTKB_SinhVien.setString(1, mssv);

            ResultSet rs = xemTKB_SinhVien.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm XemTKB_SinhVien(String mssv) file MonHoc_LopHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void XemTKB_LopHoc(String tenLopHoc) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // XemTKB_LopHoc @tenLop VARCHAR(10)
            CallableStatement xemTKB_LopHoc = conn.prepareCall("{Call XemTKB_LopHoc(?)}");
            xemTKB_LopHoc.setString(1, tenLopHoc);

            ResultSet rs = xemTKB_LopHoc.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm XemTKB_LopHoc(String tenLopHoc) file MonHoc_LopHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void main(String[] args) {
        // importTKB("./data/TKB/18CTT1.csv");
        // importTKB("./data/TKB/18CTT2.csv");
        // importTKB("./data/TKB/18CTT3.csv");
        // XemTKB_LopHoc("18CTT2");
        XemTKB_SinhVien("18120201");
        // XemBangDiem_GiaoVu("18CTT2", "Lập trình hướng đối tượng");
        System.out.println("hello");
    }

    // public static void main(String[] args) {
    //     SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
    //     Session session = null;
    //     LopHoc lopTui = null;
    //     MonHoc monTui = null;
    //     MonHoc monTui1 = null;
    //     MonHoc_LopHoc monHoc_lopHoc = new MonHoc_LopHoc();
    //     MonHoc_LopHoc monHoc_lopHoc1 = new MonHoc_LopHoc();

    //     try {
    //         session = sessionFactory.openSession();
    //         session.beginTransaction();

    //         lopTui = session.createQuery("from LopHoc l where l._tenLop = '18CTT3'", LopHoc.class).getSingleResult();

    //         monTui = session.createQuery("from MonHoc m where m._tenMonHoc like :name", MonHoc.class).setParameter("name", "%Lập trình hướng đối tượng%").getSingleResult();
    //         lopTui.addMonHoc_LopHoc(monHoc_lopHoc);
    //         monTui.addMonHoc_LopHoc(monHoc_lopHoc);
    //         monHoc_lopHoc.set_phongHoc("E5");

    //         monTui1 = session.createQuery("from MonHoc m where m._tenMonHoc like :name", MonHoc.class).setParameter("name", "%Cơ sở dữ liệu%").getSingleResult();
    //         lopTui.addMonHoc_LopHoc(monHoc_lopHoc1);
    //         monTui1.addMonHoc_LopHoc(monHoc_lopHoc1);
    //         monHoc_lopHoc1.set_phongHoc("E6");

    //         // session.save(lopTui);
    //         // session.save(monTui);
    //         session.persist(monHoc_lopHoc);
    //         session.persist(monHoc_lopHoc1);
    //         session.getTransaction().commit();
    //     }
    //     catch (Exception e) {
    //         session.getTransaction().rollback();
    //         System.out.println("Lỗi ở hàm main file MonHoc_LopHocDAO");
    //     }
    //     finally {
    //         session.close();
    //     }
    // }
}