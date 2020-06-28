package com.hibernate.dao;

import java.io.*;
import java.sql.*;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

public class LopHocDAO {
    public static void addSinhVien(SinhVien sv, String tenLop) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();

            // do something here
            CallableStatement statement = conn.prepareCall("{Call Import_SinhVien(?, ?, ?, ?, ?)}");
            statement.setString(1, sv.get_mssv());
            statement.setString(2, sv.get_hoTen());
            statement.setString(3, sv.getGioiTinh());
            statement.setString(4, sv.get_cmnd());
            statement.setString(5, tenLop);

            statement.execute();
        }
        catch (SQLException se) {
            try {
                conn.rollback();
            }
            catch (SQLException see) {
                System.err.println("Lỗi part 2 ở hàm addSinhVien(SinhVien sv, String tenLop) file LopHocDAO");
            }
            System.err.println("Lỗi ở hàm addSinhVien(SinhVien sv, String tenLop) file LopHocDAO");
            // sau này khi code giao diện phải throw thêm 
            // để khi lỗi xảy ra còn biết đường mà quăng popup :)
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException se) {
                    System.err.println("Đến đây mà còn lỗi thì chắc toang nặng\nHàm addSinhVien(SinhVien sv, String tenLop) file LopHocDAO");
                }
            }
        }
    }

    private static SinhVien readSinhVien(String line) {
        String[] s = line.split(",");
        SinhVien sv = new SinhVien(s[1], s[2], s[3], s[4]);
        return sv;
    }

    // import danh sách lớp sử dụng stored procedure
    public static void importDanhSachLop(String filename) {
        Connection conn = null;
        BufferedReader br = null;
        String line = null;

        try {
            conn = HibernateUtil.getConnection();
            br = new BufferedReader(new FileReader(filename));

            // do something here
            CallableStatement statement = conn.prepareCall("{Call Import_SinhVien(?, ?, ?, ?, ?)}");
            CallableStatement statement1 = conn.prepareCall("{Call create_lopHoc(?)}");
            String tenLop = br.readLine();
            // set _tenLop cho statement1
            statement1.setString(1, tenLop);
            statement1.execute();

            br.readLine();
            while ((line = br.readLine()) != null) {
                SinhVien sv = readSinhVien(line);
                statement.setString(1, sv.get_mssv());
                statement.setString(2, sv.get_hoTen());
                statement.setString(3, sv.getGioiTinh());
                statement.setString(4, sv.get_cmnd());
                statement.setString(5, tenLop);
                statement.execute();
            }
        }
        catch (SQLException se) {
            try {
                conn.rollback();
            }
            catch (SQLException see) {
                System.err.println("Lỗi part 2 ở hàm importDanhSachLop(String filename) file LopHocDAO");
            }
            System.err.println("Lỗi ở hàm importDanhSachLop(String filename) file LopHocDAO");
            // sau này khi code giao diện phải throw thêm 
            // để khi lỗi xảy ra còn biết đường mà quăng popup :)
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException se) {
                    System.err.println("Đến đây mà còn lỗi thì chắc toang nặng\nHàm importDanhSachLop(String filename) file LopHocDAO");
                }
            }
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    System.err.println("importDanhSachLop(String filename) trong LopHocDAO");
                }
            }
        }
    }

    public static void main(String[] args) {
        // addLopHoc("18CTT2");
        importDanhSachLop("./data/Danh sách lớp/18CTT1.csv");
        System.out.println("hello :)");
        // addSinhVien(new SinhVien("18120201", "Nguyễn Bảo Long", "Nam", "241845617"), "18CTT1");
    }
}