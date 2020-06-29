package com.hibernate.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.hibernate.util.HibernateUtil;

public class MonHocDAO {
    public static void addMonHoc(String tenMonHoc, String maMonHoc) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();

            // do something here
            // create_monHoc @tenMonHoc NVARCHAR(100), @maMonHoc CHAR(6)
            CallableStatement addMon = conn.prepareCall("{Call create_monHoc(?, ?)}");
            addMon.setString(1, tenMonHoc);
            addMon.setString(2, maMonHoc);
            addMon.execute();
        }
        catch (SQLException he) {
            System.err.println("Lỗi ở hàm addMonHoc(String tenMonHoc, String maMonHoc) file MonHocDAO");
        }
        // finally {
        //     if (conn != null) {
        //         try {
        //             conn.close();
        //         }
        //         catch (SQLException se) {
        //             System.err.println("Đến đây mà còn lỗi thì chắc toang nặng\nHàm addMonHoc(String tenMonHoc, String maMonHoc) file MonHocDAO");
        //         }
        //     }
        // }
    }
    public static void main(String[] args) {
        addMonHoc("Lập trình hướng đối tượng", "CTT001");
        addMonHoc("Cơ sở dữ liệu", "CTT002");
    }
}