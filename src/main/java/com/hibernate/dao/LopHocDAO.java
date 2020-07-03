package com.hibernate.dao;

import java.sql.*;
import java.util.*;

import com.hibernate.util.HibernateUtil;

public class LopHocDAO {
    public static void addLopHoc(String tenLop) throws SQLException {
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // create_lopHoc @tenLop VARCHAR(10)
            // nếu đã tồn tại thì không tạo nữa, ngược lại, tạo mới 1 lớp học
            CallableStatement createLopHoc = conn.prepareCall("{Call create_lopHoc(?)}");
            createLopHoc.setString(1, tenLop);
            createLopHoc.execute();
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm addLopHoc(String tenLop) file LopHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        //     throw new RuntimeException(se);
        // }
    }

    public static ArrayList<ArrayList<String>> XemDanhSachLop(String tenLop) throws SQLException {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // XemDanhSachLop @tenLop VARCHAR(10)
            CallableStatement xemDanhSach = conn.prepareCall("{Call XemDanhSachLop(?)}");
            xemDanhSach.setString(1, tenLop);

            ResultSet rs = xemDanhSach.executeQuery();
            ArrayList<String> l1 = new ArrayList<>();
            ArrayList<String> l2 = new ArrayList<>();
            ArrayList<String> l3 = new ArrayList<>();
            ArrayList<String> l4 = new ArrayList<>();
            while (rs.next()) {
                l1.add(rs.getString(1));
                l2.add(rs.getString(2));
                l3.add(rs.getString(3));
                l4.add(rs.getString(4));
                // System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            list.add(l1);
            list.add(l2);
            list.add(l4);
            list.add(l3);
            return list;
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm XemDanhSachLop(String tenLop) file LopHocDAO");
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
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
            list = XemDanhSachLop("18CT2");
            for (int i = 0; i < list.get(0).size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    System.out.print(list.get(j).get(i) + "\t");
                }
                System.out.println();
            }
        } 
        catch (SQLException se) {
            System.err.println(se);
        }
        // addLopHoc("18CTT2");
        // XemDanhSachLop("18CTT2");
        System.out.println("hello :)");
    }
}