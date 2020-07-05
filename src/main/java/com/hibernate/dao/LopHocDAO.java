package com.hibernate.dao;

import java.sql.*;
import java.util.*;

import com.hibernate.util.HibernateUtil;

public class LopHocDAO {
    // ghi data xuống database
    public static void addLopHoc(String tenLop) throws SQLException {
        Connection conn = null;
        conn = HibernateUtil.getConnection();
        // create_lopHoc @tenLop VARCHAR(10)
        // nếu đã tồn tại thì không tạo nữa, ngược lại, tạo mới 1 lớp học
        CallableStatement createLopHoc = conn.prepareCall("{Call create_lopHoc(?)}");
        createLopHoc.setString(1, tenLop);
        createLopHoc.execute();
    }

    // hàm xem danh sách lớp
    // trả về 1 arraylist chứa nguyên cái bảng 
    public static ArrayList<ArrayList<String>> XemDanhSachLop(String tenLop) throws SQLException {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Connection conn = null;
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
        }
        list.add(l1);
        list.add(l2);
        list.add(l4);
        list.add(l3);
        return list;
    }

    // // hàm main, dùng để test 
    // public static void main(String[] args) {
    //     ArrayList<ArrayList<String>> list = new ArrayList<>();
    //     try {
    //         list = XemDanhSachLop("18CT2");
    //         for (int i = 0; i < list.get(0).size(); i++) {
    //             for (int j = 0; j < list.size(); j++) {
    //                 System.out.print(list.get(j).get(i) + "\t");
    //             }
    //             System.out.println();
    //         }
    //     } 
    //     catch (SQLException se) {
    //         System.err.println(se);
    //     }
    //     // addLopHoc("18CTT2");
    //     // XemDanhSachLop("18CTT2");
    //     System.out.println("hello :)");
    // }
}