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
            br = new BufferedReader(new FileReader(filename));

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
                // createMonHoc.setString(1, array[2]);
                // createMonHoc.setString(2, array[1]);
                // createMonHoc.execute();
                MonHocDAO.addMonHoc(array[2], array[1]);

                // tạo tkb
                importTKB.setString(1, array[2]);
                importTKB.setString(2, tenLop);
                importTKB.setString(3, array[3]);
                importTKB.execute();
            }
        }
        catch (SQLException se) {
            System.err.println("importTKB(String filename) file MonHoc_LopHocDAO");
        }
        catch (IOException ioe) {
            System.err.println("Lỗi IOE ở Hàm importTKB(String filename) file MonHoc_LopHocDAO");
        }
        finally {
            // if (conn != null) {
            //     try {
            //         conn.close();
            //     }
            //     catch (SQLException se) {
            //         System.err.println("Đến đây mà còn lỗi thì chắc toang nặng\nHàm importTKB(String filename) file MonHoc_LopHocDAO");
            //     }
            // }
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

    public static void XemBangDiem_GiaoVu(String tenLop, String tenMonHoc) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // XemDiem_GiaoVu @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10)
            CallableStatement xemDiem = conn.prepareCall("{Call XemDiem_GiaoVu (?, ?)}");
            xemDiem.setString(1, tenMonHoc);
            xemDiem.setString(2, tenLop);

            ResultSet rs = xemDiem.executeQuery();
            while (rs.next()) {
                for (int i = 1; i <= 7; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        }
        catch (SQLException se) {
            // System.err.println(se);
            System.err.println("Lỗi ở hàm XemBangDiem_GiaoVu file MonHoc_LopHocDAO");
        }
    }

    public static void main(String[] args) {
        // importTKB("./data/TKB/18CTT1.csv");
        // importTKB("./data/TKB/18CTT2.csv");
        // importTKB("./data/TKB/18CTT3.csv");
        XemBangDiem_GiaoVu("18CTT2", "Lập trình hướng đối tượng");
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