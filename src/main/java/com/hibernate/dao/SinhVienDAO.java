package com.hibernate.dao;

import java.sql.*;

import com.hibernate.util.HibernateUtil;

public class SinhVienDAO {
    public static void DangKyMonHoc(String mssv, String tenMonHoc, String tenLop) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // DangKyMonHoc @mssv CHAR(10), @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10)
            CallableStatement dangKyMon = conn.prepareCall("{Call DangKyMonHoc(?, ?, ?)}");
            dangKyMon.setString(1, mssv);
            dangKyMon.setString(2, tenMonHoc);
            dangKyMon.setString(3, tenLop);
            dangKyMon.execute();
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm DangKyMonHoc file SinhVienDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void HuyBoMonHoc(String mssv, String tenMonHoc) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // HuyBoMonHoc @mssv CHAR(10), @tenMonHoc NVARCHAR(100)
            CallableStatement huyBoMon = conn.prepareCall("{Call HuyBoMonHoc(?, ?)}");
            huyBoMon.setString(1, mssv);
            huyBoMon.setString(2, tenMonHoc);
            huyBoMon.execute();
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm HuyBoMonHoc file SinhVienDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void XemDiem_SinhVien(String mssv) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // XemDiem_SinhVien @mssv CHAR(10)
            CallableStatement xemDiem = conn.prepareCall("{Call XemDiem_SinhVien (?)}");
            xemDiem.setString(1, mssv);
            ResultSet rs = xemDiem.executeQuery();
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm XemDiem_SinhVien file SinhVienDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void main(String[] args) {
        // HuyBoMonHoc("18120201", "Lập trình hướng đối tượng");
        // HuyBoMonHoc("18120201", "Lập trình hướng đối tượng");
        // DangKyMonHoc("18120201", "Lập trình hướng đối tượng", "18CTT1");
        // DangKyMonHoc("18120201", "Lập trình hướng đối tượng", "18CTT1");
        // XemDiem_SinhVien("18120201");
        // UpdateDiem("18120201", "Lập trình hướng đối tượng", 9, 9, 9, 9);
    }
}