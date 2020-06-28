package com.hibernate.dao;

import java.io.*;
import java.sql.*;

import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtil;

import org.hibernate.*;

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

    public static void importDanhSachLop(String filename) {
        Session session = null;
        BufferedReader br = null;
        String line = null;
        try {
            session = HibernateUtil.getSessionJavaConfigFactory().openSession();
            session.beginTransaction();

            br = new BufferedReader(new FileReader(filename));
            line = br.readLine();
            LopHoc l = new LopHoc(line);
            br.readLine();
            while ((line = br.readLine()) != null) {
                SinhVien sv = readSinhVien(line);
                l.addSinhVien(sv);
            }

            session.save(l);

            session.flush();
            session.getTransaction().commit();
            session.clear();
        }
        catch (FileNotFoundException err) {
            System.err.println("Không tìm thấy file ở hàm readStudentList(String filename) file LopHocDAO");
        }
        catch (IOException e) {
            System.err.println("Lỗi IOE ở hàm readStudentList(String filename) file LopHocDAO");
        }
        catch (HibernateException he) {
            session.getTransaction().rollback();
            System.err.println("Lỗi ở hàm importDanhSachLop(String filename) file LopHocDAO");
            // khi code đến giao diện thì phải throw 1 cái nữa để giao diện bắt được
            // sau khi bắt được, giao diện sẽ làm 1 con popup nhẹ thông báo cho ngừoi dùng
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException ex) {
                    System.err.println("Đến đây mà còn lỗi thì toang nặng\nreadStudentList(String filename) file LopHocDAO");
                }
            }
            if (session != null) {
                session.close();
            }
        }
    }

    // public static void addLopHoc(String tenLop) {
    //     Session session = null;
    //     try {
    //         session = HibernateUtil.getSessionJavaConfigFactory().openSession();
    //         session.beginTransaction();

    //         // do something here
    //         LopHoc lh = new LopHoc(tenLop);
    //         session.save(lh);

    //         session.flush();
    //         session.getTransaction().commit();
    //         session.clear();
    //     }
    //     catch (HibernateException he) {
    //         session.getTransaction().rollback();
    //         System.err.println("Lỗi ở hàm addLopHoc(String tenLop) file LopHocDAO");
    //         // khi code đến giao diện thì phải throw 1 cái nữa để giao diện bắt được
    //         // sau khi bắt được, giao diện sẽ làm 1 con popup nhẹ thông báo cho ngừoi dùng
    //     }
    //     finally {
    //         if (session != null) {
    //             session.close();
    //         }
    //     }
    // }
    public static void main(String[] args) {
        // addLopHoc("18CTT2");
        importDanhSachLop("./data/Danh sách lớp/18CTT1.csv");
    }
}