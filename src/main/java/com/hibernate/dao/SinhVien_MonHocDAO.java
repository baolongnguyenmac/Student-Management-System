package com.hibernate.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            // System.err.println(se);
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
            // System.err.println(se);
        }
    }

    public static void main(String[] args) {
        // XemTKB_SinhVien("18120201");
        XemTKB_LopHoc("18CTT2");
    }
}