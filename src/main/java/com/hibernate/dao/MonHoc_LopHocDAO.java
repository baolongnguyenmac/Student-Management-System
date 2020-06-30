package com.hibernate.dao;

import java.io.*;
import java.sql.*;

import com.hibernate.util.HibernateUtil;

public class MonHoc_LopHocDAO {
    private static String[] readLineTKB(String line) {
        return line.split(",");
    }

    public static void importTKB(String filename) throws SQLException {
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
        // catch (SQLException se) {
        //     System.err.println("lỗi ở hàm importTKB(String filename) file MonHoc_LopHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        //     throw new RuntimeException(se);
        // }
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

    public static void XemTKB_SinhVien(String mssv) throws SQLException {
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // XemTKB_SinhVien @mssv CHAR(10)
            CallableStatement xemTKB_SinhVien = conn.prepareCall("{Call XemTKB_SinhVien(?)}");
            xemTKB_SinhVien.setString(1, mssv);

            ResultSet rs = xemTKB_SinhVien.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm XemTKB_SinhVien(String mssv) file MonHoc_LopHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        //     throw new RuntimeException(se);
        // }
    }

    public static void XemTKB_LopHoc(String tenLopHoc) throws SQLException {
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // XemTKB_LopHoc @tenLop VARCHAR(10)
            CallableStatement xemTKB_LopHoc = conn.prepareCall("{Call XemTKB_LopHoc(?)}");
            xemTKB_LopHoc.setString(1, tenLopHoc);

            ResultSet rs = xemTKB_LopHoc.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm XemTKB_LopHoc(String tenLopHoc) file MonHoc_LopHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        //     throw new RuntimeException(se);
        // }
    }

    public static void main(String[] args) {
        try {

            // importTKB("./data/TKB/18CTT1.csv");
            // importTKB("./data/TKB/18CTT2.csv");
            // importTKB("./data/TKB/18CTT3.csv");
            // XemTKB_LopHoc("18CTT2");
            XemTKB_SinhVien("1811");
            // XemBangDiem_GiaoVu("18CTT2", "Lập trình hướng đối tượng");
            System.out.println("hello");
        }
        catch (Exception e) {
            System.err.println(e);
            // System.out.println(e.getLocalizedMessage());
        }
    }
}