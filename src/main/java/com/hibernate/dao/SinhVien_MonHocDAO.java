package com.hibernate.dao;

import java.io.*;
import java.sql.*;

import com.hibernate.util.HibernateUtil;

public class SinhVien_MonHocDAO {
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
            System.err.println("Lỗi ở hàm XemTKB_SinhVien(String mssv) file SinhVien_MonHocDAO");
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
            System.err.println("Lỗi ở hàm XemTKB_LopHoc(String tenLopHoc) file SinhVien_MonHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    private static String[] readLineBangDiem(String line) {
        return line.split(",");
    }

    public static void ImportBangDiem(String filename) {
        Connection conn = null;
        BufferedReader br = null;
        String line = null;

        try {
            // br = new BufferedReader(new FileReader(filename));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf8"));
            conn = HibernateUtil.getConnection();
            // ImportBangDiem @tenLop VARCHAR(10), @tenMonHoc NVARCHAR(100), @mssv CHAR(10), @diemCC FLOAT, @diemGK FLOAT, @diemCK FLOAT, @diemTong FLOAT
            CallableStatement importDiem = conn.prepareCall("{Call ImportBangDiem(?, ?, ?, ?, ? ,?, ?)}");

            String tenLop = br.readLine();
            String tenMonHoc = br.readLine();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String array[] = readLineBangDiem(line);
                importDiem.setString(1, tenLop);
                importDiem.setString(2, tenMonHoc);
                importDiem.setString(3, array[1]);
                importDiem.setFloat(4, Float.parseFloat(array[3]));
                importDiem.setFloat(5, Float.parseFloat(array[4]));
                importDiem.setFloat(6, Float.parseFloat(array[5]));
                importDiem.setFloat(7, Float.parseFloat(array[6]));

                importDiem.execute();
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm ImportBangDiem file SinhVien_MonHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void UpdateDiem(String mssv, String tenMonHoc, float diemCC, float diemGK, float diemCK, float diemTong) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // UpdateDiemSinhVien @mssv CHAR(10), @tenMonHoc NVARCHAR(100), @diemCC FLOAT, @diemGK FLOAT, @diemCK FLOAT, @diemTong FLOAT
            CallableStatement updateDiem = conn.prepareCall("{Call UpdateDiemSinhVien(?, ?, ?, ?, ? ,?)}");
            updateDiem.setString(1, mssv);
            updateDiem.setString(2, tenMonHoc);
            updateDiem.setFloat(3, diemCC);
            updateDiem.setFloat(4, diemGK);
            updateDiem.setFloat(5, diemCK);
            updateDiem.setFloat(6, diemTong);

            updateDiem.execute();
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm UpdateDiem file SinhVienDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void main(String[] args) {
        // XemTKB_SinhVien("18120201");
        // XemTKB_LopHoc("18CTT2");
        // ImportBangDiem("./data/Bảng điểm/18CTT1_DB.csv");
        // ImportBangDiem("./data/Bảng điểm/18CTT1_OOP.csv");
        // ImportBangDiem("./data/Bảng điểm/18CTT2_OOP.csv");
        // ImportBangDiem("./data/Bảng điểm/18CTT2_DB.csv");
        // ImportBangDiem("./data/Bảng điểm/18CTT3_DB.csv");
        // ImportBangDiem("./data/Bảng điểm/18CTT3_OOP.csv");

        UpdateDiem("18120201", "Cơ sở  liệu", 9, 9, 9, 9);

        System.out.println("hello");
    }
}