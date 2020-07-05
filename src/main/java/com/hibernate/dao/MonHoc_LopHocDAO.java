package com.hibernate.dao;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.hibernate.util.HibernateUtil;

public class MonHoc_LopHocDAO {
    private static String[] readLineTKB(String line) {
        return line.split(",");
    }

    public static String importTKB(String filename) throws SQLException {
        Connection conn = null;
        BufferedReader br = null;
        String line = null;
        String tenLop = null;

        try {
            conn = HibernateUtil.getConnection();
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf8"));

            // do something
            // Import_TKB @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10), @phongHoc VARCHAR(10)
            CallableStatement importTKB = conn.prepareCall("{Call Import_TKB(?, ?, ?)}");

            // tạo lớp học: k.tr xem có tạo trước đó chưa, nếu chưa thì tạo
            tenLop = br.readLine();
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
        return tenLop;
    }

    public static ArrayList<ArrayList<String>> XemTKB_SinhVien(String mssv) throws SQLException {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // XemTKB_SinhVien @mssv CHAR(10)
            CallableStatement xemTKB_SinhVien = conn.prepareCall("{Call XemTKB_SinhVien(?)}");
            xemTKB_SinhVien.setString(1, mssv);

            ResultSet rs = xemTKB_SinhVien.executeQuery();
            ArrayList<String> l1 = new ArrayList<>();
            ArrayList<String> l2 = new ArrayList<>();
            ArrayList<String> l3 = new ArrayList<>();
            while (rs.next()) {
                l1.add(rs.getString(1));
                l2.add(rs.getString(2));
                l3.add(rs.getString(3));
                // System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            list.add(l1);
            list.add(l2);
            list.add(l3);
            return list;
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

    public static ArrayList<ArrayList<String>> XemTKB_LopHoc(String tenLopHoc) throws SQLException {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // XemTKB_LopHoc @tenLop VARCHAR(10)
            CallableStatement xemTKB_LopHoc = conn.prepareCall("{Call XemTKB_LopHoc(?)}");
            xemTKB_LopHoc.setString(1, tenLopHoc);

            ResultSet rs = xemTKB_LopHoc.executeQuery();
            ArrayList<String> l1 = new ArrayList<>();
            ArrayList<String> l2 = new ArrayList<>();
            ArrayList<String> l3 = new ArrayList<>();
            while (rs.next()) {
                l1.add(rs.getString(1));
                l2.add(rs.getString(2));
                l3.add(rs.getString(3));
                // System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            list.add(l1);
            list.add(l2);
            list.add(l3);
            return list;
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
        // ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {

            importTKB("./data/TKB/18CTT1.csv");
            importTKB("./data/TKB/18CTT2.csv");
            importTKB("./data/TKB/18CTT3.csv");
            // XemTKB_LopHoc("18CTT2");
            // XemTKB_SinhVien("18120201");
            // list = XemTKB_LopHoc("18CTT2");
            // for (int i = 0; i < list.get(0).size(); i++) {
            //     for (int j = 0; j < list.size(); j++) {
            //         System.out.print(list.get(j).get(i) + "\t");
            //     }
            //     System.out.println();
            // }
            // // XemBangDiem_GiaoVu("18CTT2", "Lập trình hướng đối tượng");
            // System.out.println("hello");
        }
        catch (Exception e) {
            System.err.println(e);
            // System.out.println(e.getLocalizedMessage());
        }
    }
}