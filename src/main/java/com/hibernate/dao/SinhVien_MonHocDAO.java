package com.hibernate.dao;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.hibernate.util.HibernateUtil;

public class SinhVien_MonHocDAO {
    public static ArrayList<ArrayList<String>> XemBangDiem_SinhVien(String mssv) throws SQLException {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // XemDiem_SinhVien @mssv CHAR(10)
            CallableStatement xemDiem = conn.prepareCall("{Call XemDiem_SinhVien (?)}");
            xemDiem.setString(1, mssv);
            ResultSet rs = xemDiem.executeQuery();
            ArrayList<String> l1 = new ArrayList<>();
            ArrayList<String> l2 = new ArrayList<>();
            ArrayList<String> l3 = new ArrayList<>();
            ArrayList<String> l4 = new ArrayList<>();
            ArrayList<String> l5 = new ArrayList<>();
            while (rs.next()) {
                l1.add(rs.getString(1));
                l2.add(rs.getString(2));
                l3.add(rs.getString(3));
                l4.add(rs.getString(4));
                l5.add(rs.getString(5));
                // for (int i = 1; i <= 5; i++) {
                //     System.out.print(rs.getString(i) + " ");
                // }
                // System.out.println();
            }
            list.add(l1);
            list.add(l2);
            list.add(l3);
            list.add(l4);
            list.add(l5);
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm XemDiem_SinhVien file SinhVien_MonHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        // }
        return list;
    }

    public static ArrayList<ArrayList<String>> XemBangDiem_GiaoVu(String tenLop, String tenMonHoc) throws SQLException {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // XemDiem_GiaoVu @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10)
            CallableStatement xemDiem = conn.prepareCall("{Call XemDiem_GiaoVu (?, ?)}");
            xemDiem.setString(1, tenMonHoc);
            xemDiem.setString(2, tenLop);

            ResultSet rs = xemDiem.executeQuery();
            ArrayList<String> l1 = new ArrayList<>();
            ArrayList<String> l2 = new ArrayList<>();
            ArrayList<String> l3 = new ArrayList<>();
            ArrayList<String> l4 = new ArrayList<>();
            ArrayList<String> l5 = new ArrayList<>();
            ArrayList<String> l6 = new ArrayList<>();
            ArrayList<String> l7 = new ArrayList<>();
            while (rs.next()) {
                l1.add(rs.getString(1));
                l2.add(rs.getString(2));
                l3.add(rs.getString(3));
                l4.add(rs.getString(4));
                l4.add(rs.getString(5));
                l6.add(rs.getString(6));
                l7.add(rs.getString(7));
                for (int i = 1; i <= 7; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
            list.add(l1);
            list.add(l2);
            list.add(l3);
            list.add(l4);
            list.add(l5);
            list.add(l6);
            list.add(l7);
            return list;
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm XemBangDiem_GiaoVu file SinhVien_MonHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        // }
    }

    private static String[] readLineBangDiem(String line) {
        return line.split(",");
    }

    public static void ImportBangDiem(String filename) throws SQLException {
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
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm ImportBangDiem file SinhVien_MonHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        // }
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

    public static void UpdateDiem(String mssv, String tenMonHoc, float diemCC, float diemGK, float diemCK, float diemTong) throws SQLException {
        Connection conn = null;
        // try {
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
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm UpdateDiem file SinhVien_MonHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        // }
    }

    public static void DangKyMonHoc(String mssv, String tenMonHoc, String tenLop) throws SQLException {
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // DangKyMonHoc @mssv CHAR(10), @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10)
            CallableStatement dangKyMon = conn.prepareCall("{Call DangKyMonHoc(?, ?, ?)}");
            dangKyMon.setString(1, mssv);
            dangKyMon.setString(2, tenMonHoc);
            dangKyMon.setString(3, tenLop);
            dangKyMon.execute();
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm DangKyMonHoc file SinhVien_MonHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        // }
    }

    public static void HuyBoMonHoc(String mssv, String tenMonHoc) throws SQLException {
        Connection conn = null;
        // try {
            conn = HibernateUtil.getConnection();
            // HuyBoMonHoc @mssv CHAR(10), @tenMonHoc NVARCHAR(100)
            CallableStatement huyBoMon = conn.prepareCall("{Call HuyBoMonHoc(?, ?)}");
            huyBoMon.setString(1, mssv);
            huyBoMon.setString(2, tenMonHoc);
            huyBoMon.execute();
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm HuyBoMonHoc file SinhVien_MonHocDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        // }
    }

    public static void main(String[] args) {
        // XemTKB_SinhVien("18120201");
        // XemTKB_LopHoc("18CTT2");
        
        // UpdateDiem("18120201", "Cơ sở  liệu", 9, 9, 9, 9);
        // HuyBoMonHoc("18120201", "Lập trình hướng đối tượng");
        // DangKyMonHoc("18120201", "Lập trình hướng đối tượng", "18CTT2");
        // XemBangDiem_GiaoVu("18CTT1", "Lập trình hướng đối tượng");
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
            ImportBangDiem("./data/Bảng điểm/18CTT1_DB.csv");
            ImportBangDiem("./data/Bảng điểm/18CTT1_OOP.csv");
            ImportBangDiem("./data/Bảng điểm/18CTT2_OOP.csv");
            ImportBangDiem("./data/Bảng điểm/18CTT2_DB.csv");
            ImportBangDiem("./data/Bảng điểm/18CTT3_DB.csv");
            ImportBangDiem("./data/Bảng điểm/18CTT3_OOP.csv");
            
            list = XemBangDiem_SinhVien("18120201");
            // for (int i = 0; i < list.size(); i++) {
            //     for (int j = 0; j < list.get(0).size(); j++) {
            //         System.out.print(list.get(i).get(j) + "\t");
            //     }
            //     System.out.println();
            // }
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

        System.out.println("hello");
    }
}