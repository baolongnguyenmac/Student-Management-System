package com.hibernate.dao;

import java.io.*;
import java.sql.*;

import com.hibernate.entity.*;
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

    public static void addSinhVien(SinhVien sv, String tenLop) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();

            // do something here
            // check if lopHoc exists
            // check_exists_lopHoc @tenLop VARCHAR(10)
            CallableStatement checkLopHocExists = conn.prepareCall("{? = Call check_exists_lopHoc(?)}");
            checkLopHocExists.registerOutParameter(1, Types.INTEGER);
            checkLopHocExists.setString(2, tenLop);
            checkLopHocExists.execute();
            if (checkLopHocExists.getInt(1) == 1) {
                // Import_SinhVien @mssv CHAR(10), @hoTen NVARCHAR(100), @gioiTinh NVARCHAR(3), @cmnd CHAR(9), @tenLop VARCHAR(10)
                CallableStatement statement = conn.prepareCall("{Call Import_SinhVien(?, ?, ?, ?, ?)}");
                statement.setString(1, sv.get_mssv());
                statement.setString(2, sv.get_hoTen());
                statement.setString(3, sv.getGioiTinh());
                statement.setString(4, sv.get_cmnd());
                statement.setString(5, tenLop);

                // sẽ quăng lỗi nếu sinh viên add vào bị trùng lặp 
                statement.execute();
            }
            else {
                // không có tenLop trong hệ thống 
                // throw gì đó để giao diện biết được và hiển thị popup
            }
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm addSinhVien(SinhVien sv, String tenLop) file LopHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
            // sau này khi code giao diện phải throw thêm 
            // để khi lỗi xảy ra còn biết đường mà quăng popup :)
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
            // br = new BufferedReader(new FileReader(filename));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf8"));

            // do something here
            // Import_SinhVien @mssv CHAR(10), @hoTen NVARCHAR(100), @gioiTinh NVARCHAR(3), @cmnd CHAR(9), @tenLop VARCHAR(10)
            CallableStatement importSinhVien = conn.prepareCall("{Call Import_SinhVien(?, ?, ?, ?, ?)}");

            // create_lopHoc @tenLop VARCHAR(10)
            String tenLop = br.readLine();
            CallableStatement createLopHoc = conn.prepareCall("{Call create_lopHoc(?)}");
            // set _tenLop cho statement1
            createLopHoc.setString(1, tenLop);
            createLopHoc.execute();

            br.readLine();
            while ((line = br.readLine()) != null) {
                SinhVien sv = readSinhVien(line);
                importSinhVien.setString(1, sv.get_mssv());
                importSinhVien.setString(2, sv.get_hoTen());
                importSinhVien.setString(3, sv.getGioiTinh());
                importSinhVien.setString(4, sv.get_cmnd());
                importSinhVien.setString(5, tenLop);
                importSinhVien.execute();
            }
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm importDanhSachLop(String filename) file LopHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
            // sau này khi code giao diện phải throw thêm 
            // để khi lỗi xảy ra còn biết đường mà quăng popup :)
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
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
        importDanhSachLop("./data/Danh sách lớp/18CTT1.csv");
        importDanhSachLop("./data/Danh sách lớp/18CTT2.csv");
        importDanhSachLop("./data/Danh sách lớp/18CTT3.csv");
        // XemDanhSachLop("18CTT2");
        // addSinhVien(new SinhVien("00000000", "Sinh Viên phụ", "Nam", "000000000"), "18CTT1");
        System.out.println("hello :)");
        // addSinhVien(new SinhVien("18120201", "Nguyễn Bảo Long", "Nam", "241845617"), "18CTT1");
    }
}