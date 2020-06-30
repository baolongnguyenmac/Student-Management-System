package com.hibernate.dao;

import java.sql.*;

import com.hibernate.util.HibernateUtil;

public class LopHocDAO {
    public static void addLopHoc(String tenLop) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // create_lopHoc @tenLop VARCHAR(10)
            // nếu đã tồn tại thì không tạo nữa, ngược lại, tạo mới 1 lớp học
            CallableStatement createLopHoc = conn.prepareCall("{Call create_lopHoc(?)}");
            createLopHoc.setString(1, tenLop);
            createLopHoc.execute();
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm addLopHoc(String tenLop) file LopHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void XemDanhSachLop(String tenLop) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();
            // XemDanhSachLop @tenLop VARCHAR(10)
            CallableStatement xemDanhSach = conn.prepareCall("{Call XemDanhSachLop(?)}");
            xemDanhSach.setString(1, tenLop);

            ResultSet rs = xemDanhSach.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm XemDanhSachLop(String tenLop) file LopHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    public static void main(String[] args) {
        // addLopHoc("18CTT2");
        // importDanhSachLop("./data/Danh sách lớp/18CTT1.csv");
        // importDanhSachLop("./data/Danh sách lớp/18CTT2.csv");
        // importDanhSachLop("./data/Danh sách lớp/18CTT3.csv");
        // XemDanhSachLop("18CTT2");
        // addSinhVien(new SinhVien("00000000", "Sinh Viên phụ", "Nam", "000000000"), "18CTT1");
        System.out.println("hello :)");
        // addSinhVien(new SinhVien("18120201", "Nguyễn Bảo Long", "Nam", "241845617"), "18CTT1");
    }
}