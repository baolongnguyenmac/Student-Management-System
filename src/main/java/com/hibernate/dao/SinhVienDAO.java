package com.hibernate.dao;

import java.io.*;
import java.sql.*;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

public class SinhVienDAO {
    public static void addSinhVien(SinhVien sv, String tenLop) throws SQLException {
        Connection conn = null;
        // try {
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
                throw new RuntimeException();
            }
        // }
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm addSinhVien(SinhVien sv, String tenLop) file SinhVienDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        //     // sau này khi code giao diện phải throw thêm 
        //     // để khi lỗi xảy ra còn biết đường mà quăng popup :)
        // }
    }

    private static SinhVien readSinhVien(String line) {
        String[] s = line.split(",");
        SinhVien sv = new SinhVien(s[1], s[2], s[3], s[4]);
        return sv;
    }

    // import danh sách lớp sử dụng stored procedure
    public static void importDanhSachLop(String filename) throws SQLException {
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

            // nếu không tồn tại lớp học thì add lớp học vào 
            String tenLop = br.readLine();
            LopHocDAO.addLopHoc(tenLop);

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
        // catch (SQLException se) {
        //     System.err.println("Lỗi ở hàm importDanhSachLop(String filename) file SinhVienDAO");
        //     do {
        //         System.out.println("MESSAGE: " + se.getMessage());
        //         System.out.println();
        //         se = se.getNextException();
        //     }
        //     while (se != null);
        //     // sau này khi code giao diện phải throw thêm 
        //     // để khi lỗi xảy ra còn biết đường mà quăng popup :)
        // }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    System.err.println("importDanhSachLop(String filename) trong SinhVienDAO");
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            importDanhSachLop("./data/Danh sách lớp/18CTT1.csv");
            importDanhSachLop("./data/Danh sách lớp/18CTT2.csv");
            importDanhSachLop("./data/Danh sách lớp/18CTT3.csv");
            // addSinhVien(new SinhVien("00000000", "Sinh Viên phụ", "Nam", "000000000"), "18CTT1");
            // HuyBoMonHoc("18120201", "Lập trình hướng đối tượng");
            // HuyBoMonHoc("18120201", "Lập trình hướng đối tượng");
            // DangKyMonHoc("18120201", "Lập trình hướng đối tượng", "18CTT1");
            // DangKyMonHoc("18120201", "Lập trình hướng đối tượng", "18CTT1");
            // XemDiem_SinhVien("18120201");
            // UpdateDiem("18120201", "Lập trình hướng đối tượng", 9, 9, 9, 9);
        }
        catch (SQLException se) {
            System.err.println(se);
        }
    }
}